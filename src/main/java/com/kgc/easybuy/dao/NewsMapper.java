package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface NewsMapper {
    public List<News> getNewsList();
}
