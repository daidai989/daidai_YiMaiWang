package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("getBrand")
    @ResponseBody
    public Object getBrand(){
        ResponseMessage brand = brandService.getBrand();
        return brand;
    }
}