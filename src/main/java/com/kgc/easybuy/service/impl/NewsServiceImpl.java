package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.NewsMapper;
import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo<News> getNewsListByPage(String title, Page page) {

        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<News> userList = newsMapper.getNewsListByPage(title);
        PageInfo<News> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public ResponseMessage delNews(Integer id) {
        boolean b = newsMapper.delNews(id);
        if (b) {
            return ResponseMessage.success(b);
        }
        return ResponseMessage.error("删除失败");
    }

    @Override
    public ResponseMessage getNewsByTitle(String title) {
        News newsByTitle = newsMapper.getNewsByTitle(title);
        if (newsByTitle!=null){
            return ResponseMessage.error("标题重复",newsByTitle);
        }
        return ResponseMessage.success(newsByTitle);
    }

    @Override
    public ResponseMessage getNewsById(Integer id) {
        News newsByTitle = newsMapper.getNewsById(id);
        return ResponseMessage.success(newsByTitle);
    }

    @Override
    public ResponseMessage addNews(News news) {
        boolean b = newsMapper.addNews(news);
        if (b) {
            return ResponseMessage.success(b);
        }
        return ResponseMessage.error("添加失败");
    }

    @Override
    public ResponseMessage updateNews(News news) {
        boolean b = newsMapper.updateNews(news);
        if (b) {
            return ResponseMessage.success(b);
        }
        return ResponseMessage.error("修改失败");
    }
}
