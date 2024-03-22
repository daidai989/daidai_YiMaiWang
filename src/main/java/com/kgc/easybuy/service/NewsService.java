package com.kgc.easybuy.service;

import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;

import java.awt.image.RescaleOp;

/**
 * @author daidai
 */
public interface NewsService {
    public ResponseMessage getNewsList();
    public PageInfo<News> getNewsListByPage(String title, Page page);
    public ResponseMessage delNews(Integer id);
    public ResponseMessage getNewsByTitle(String title);
    public ResponseMessage getNewsById(Integer id);
    public ResponseMessage addNews(News news);
    public ResponseMessage updateNews(News news);

}
