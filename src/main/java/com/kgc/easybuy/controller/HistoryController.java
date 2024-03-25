//package com.kgc.easybuy.controller;
//
//import com.kgc.easybuy.pojo.History;
//import com.kgc.easybuy.pojo.ResponseMessage;
//import com.kgc.easybuy.service.BrandService;
//import com.kgc.easybuy.service.HistoryService123;
//import org.springframework.aop.framework.AopContext;
//import org.springframework.aop.framework.AopProxy;
//import org.springframework.aop.framework.AopProxyFactory;
//import org.springframework.aop.framework.AopProxyUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HistoryController {
//
//    @Autowired
//    private HistoryService123 historyService;
//
//    @RequestMapping("addProductHistory")
//    private ResponseMessage addProductHistory(History history){
////        HistoryService123 historyService123= (HistoryService123) AopContext.currentProxy();
//        ResponseMessage responseMessage = historyService.addProductHistory(history);
//        return responseMessage;
//    }
//}
