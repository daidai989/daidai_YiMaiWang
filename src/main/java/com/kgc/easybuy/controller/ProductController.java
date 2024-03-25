package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.*;
import com.kgc.easybuy.service.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*")
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
    public ResponseMessage getProductByCategoryId(){
        ResponseMessage productByCategoryId = productService.getProductByCategoryId();
        return productByCategoryId;
    }

    @RequestMapping("getHotProduct")
    @ResponseBody
    public ResponseMessage getHotProduct(){
        ResponseMessage hotProduct = productService.getHotProduct();
        return hotProduct;
    }


    @RequestMapping("getImage")
    public void disPlayImage(HttpServletResponse response,String filePath){
        ServletOutputStream os = null;
        InputStream is = null;
        try {
           filePath = URLDecoder.decode(filePath,"utf-8");
            is = new FileInputStream(filePath);
            os = response.getOutputStream();
            int len;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
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
    public Object getProductById(int id){
        Product product = productService.getProductById(id);
        return  product;
    }
    @RequestMapping("getproducts")
    @ResponseBody
    public ResponseMessage getProducts(){
        ResponseMessage products = productService.getProducts();
        return products;
    }
    @RequestMapping("viewProductsList")
    @ResponseBody
    public ResponseMessage viewProductsList(String currentNo,String name){
        ResponseMessage responseMessage = productService.viewProductsList(currentNo,name);
        return responseMessage;
    }
    @RequestMapping("delProduct")
    @ResponseBody
    public ResponseMessage logout(Integer id) {
        ResponseMessage responeseMsg = productService.delProById(id);
        return responeseMsg;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseMessage upload(@RequestParam(value = "filePath") MultipartFile filePath) {
        String extsion = null;
        String picPath = null;
        if (filePath != null) {
            if (!filePath.isEmpty()) {
                String originalFilename = filePath.getOriginalFilename();
                extsion = FilenameUtils.getExtension(originalFilename);
                picPath = "D:\\IdeaProject\\study\\pic" + File.separator + UUID.randomUUID() + "." + extsion;
            }
            if (!extsion.equalsIgnoreCase("jpg") && !extsion.equalsIgnoreCase("png")) {
                return ResponseMessage.error("文件格式有误只能上传jpg或者png");
            }
            if (filePath.getSize() > 5 * 1024 * 1024) {
                return ResponseMessage.error("文件大小不能超过5MB");
            }

            try {
                filePath.transferTo(new File(picPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return ResponseMessage.success(picPath);

    }
    @RequestMapping("addProduct")
    @ResponseBody
    public ResponseMessage addProduct(@RequestBody Product products){
        ResponseMessage responseMessage = productService.addProduct(products);
        return responseMessage;
    }
    @RequestMapping("updateProduct")
    @ResponseBody

    public ResponseMessage updateProduct(@RequestBody Product products){
        ResponseMessage responseMessage = productService.updateProduct(products);
        return responseMessage;
    }
    @RequestMapping("getProductByLogin")
    @ResponseBody

    public ResponseMessage getProductByLogin(String name){
        ResponseMessage responseMessage = productService.getProductByLogin(name);
        return responseMessage;
    }
    @RequestMapping("setProductTes")
    @ResponseBody
    public ResponseMessage setProductTes(){
        ResponseMessage responseMessage = productService.setProductTes();
        return  responseMessage;
    }
    @RequestMapping("getProFromEs")
    @ResponseBody
    public ResponseMessage getProFromEs(@RequestBody EsSelect esSelect){
        ResponseMessage responseMessage = productService.getProFromEs(esSelect);
        return  responseMessage;
    }

    @RequestMapping("getCollectProduct")
    @ResponseBody
    public ResponseMessage getCollectProduct(Collect collect,Page page){
        ResponseMessage responseMessage = productService.getCollectProduct(collect,page);
        return  responseMessage;
    }

    @RequestMapping("getRecommendProduct")
    @ResponseBody
    public ResponseMessage getRecommendProduct(Product product){
        ResponseMessage responseMessage = productService.getRecommendProduct(product.getParentId(),product.getId());
        return  responseMessage;
    }

    @RequestMapping("getHistoryProduct")
    @ResponseBody
    public ResponseMessage getHistoryProduct(int userId){
        ResponseMessage responseMessage = productService.getHistoryProduct(userId);
        return  responseMessage;
    }
}
