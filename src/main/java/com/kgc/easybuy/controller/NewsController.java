package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CategoryService;
import com.kgc.easybuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daidai
 */
@RestController
@CrossOrigin(origins = "*")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @RequestMapping("getNewsList")
    public ResponseMessage getNewsList() {
        ResponseMessage responeseMsg = newsService.getNewsList();
        return responeseMsg;
    }
}
