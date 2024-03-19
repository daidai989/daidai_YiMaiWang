package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("getProductList")
    @ResponseBody
    public Object getProductList(int currentPageNo,int pageSize){
        Object page = productService.getProductList(currentPageNo, pageSize);
        return page;
    }

    @RequestMapping("getProductByCategoryId")
    @ResponseBody
    public Object getProductByCategoryId(){
        Object list = productService.getProductByCategoryId();
        return list;
    }

    @RequestMapping("getHotProduct")
    @ResponseBody
    public Object getHotProduct(){
        List<Product> hotProduct = productService.getHotProduct();
        return hotProduct;
    }


    @RequestMapping("getImage")
    public void disPlayImage(HttpServletResponse response,String filePath){
        ServletOutputStream os = null;
        InputStream is = null;
        try {
          String fileName = URLDecoder.decode(filePath,"utf-8");
            is = new FileInputStream(filePath);
            os = response.getOutputStream();
            int length;
            byte[] bytes = new byte[1024];
            while ((length = is.read(bytes)) == -1){
                os.write(bytes,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("getProductById")
    public Object getProductById(int id){
        Product product = productService.getProductById(id);
        return  product;
    }
}
