package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface NewsMapper {
    public List<News> getNewsList();
    public List<News> getNewsListByPage(@Param("title") String title);
    public News getNewsByTitle(String title);
    public News getNewsById(Integer id);
    public boolean delNews(Integer id);

    public boolean addNews(News news);
    public boolean updateNews(News news);
}
