package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
