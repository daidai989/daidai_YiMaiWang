package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.*;
import com.kgc.easybuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
        ResponseMessage responseMessage = productService.getProductList(currentPageNo, pageSize);
        return responseMessage;
    }

    @RequestMapping("getProductByCategoryId")
    @ResponseBody
    public Object getProductByCategoryId(){
        ResponseMessage responseMessage = productService.getProductByCategoryId();
        return responseMessage;
    }

    @RequestMapping("getHotProduct")
    @ResponseBody
    public Object getHotProduct(){
        ResponseMessage responseMessage = productService.getHotProduct();
        return responseMessage;
    }


    @RequestMapping("getImage")
    @ResponseBody
    public void disPlayImage(HttpServletResponse response,String filePath){
        ServletOutputStream os = null;
        InputStream is = null;
        try {
          String fileName = URLDecoder.decode(filePath,"utf-8");
            is = new FileInputStream(fileName);
            os = response.getOutputStream();
            int length;
            byte[] bytes = new byte[1024];
            while ((length = is.read(bytes)) != -1){
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
    @ResponseBody
    public Object getProductById(Product product){
        ResponseMessage responseMessage = productService.getProductById(product.getId());
        return  responseMessage;
    }

    @RequestMapping("getRecommendProduct")
    @ResponseBody
    public Object getRecommendProduct(Product product){
        ResponseMessage responseMessage = productService.getRecommendProduct(product);
        return  responseMessage;
    }

    @RequestMapping("setProductTes")
    @ResponseBody
    public Object setProductTes(Product product){
        ResponseMessage responseMessage = productService.setProductTes();
        return  responseMessage;
    }

    @RequestMapping("getProFromEs")
    @ResponseBody
    public Object getProFromEs(EsSelect esSelect){
        ResponseMessage responseMessage = productService.getProFromEs(esSelect);
        return  responseMessage;
    }

    @RequestMapping("checkProductExitsByCategoryId")
    @ResponseBody
    public Object checkProductExitsByCategoryId(Category category){
        ResponseMessage responseMessage = productService.checkProductExitsByCategoryId(category);
        return responseMessage;
    }

}
