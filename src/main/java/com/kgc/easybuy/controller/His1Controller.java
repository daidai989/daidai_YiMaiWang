package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.History;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.HistoryService123;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class His1Controller {
    @Autowired
    private HistoryService123 historyService;

    @RequestMapping("addProductHistory")
    private ResponseMessage addProductHistory(History history){
//        HistoryService123 historyService123= (HistoryService123) AopContext.currentProxy();
        ResponseMessage responseMessage = historyService.addProductHistory(history);
        return responseMessage;
    }
}
