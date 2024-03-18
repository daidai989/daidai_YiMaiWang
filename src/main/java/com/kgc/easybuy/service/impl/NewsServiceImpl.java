package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.NewsMapper;
import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daidai
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public ResponseMessage getNewsList() {
        List<News> newsList = newsMapper.getNewsList();
        return ResponseMessage.success(newsList);
    }
}
