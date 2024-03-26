package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.History;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping("addProductHistory")
    public ResponseMessage addProductHistory(History history){
        ResponseMessage responseMessage = historyService.addProductHistory(history);
        return responseMessage;
    }

    @RequestMapping("cleanHistory")
    public ResponseMessage cleanHistory(History history){
        ResponseMessage responseMessage = historyService.cleanHistory(history.getUserId());
        return responseMessage;
    }
}