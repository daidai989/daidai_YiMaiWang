package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface CategoryMapper {

    public List<Category> getFirstCategories();

    public List<Category> getSecondCategories(Integer id);
    public List<Category> getThirdCategories(Integer id);
}
