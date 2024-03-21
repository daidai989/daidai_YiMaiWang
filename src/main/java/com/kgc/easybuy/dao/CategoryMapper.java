package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.ResponseMessage;
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
    public List<Category> getAllCategories(Category category);
    public String checkCategoryName(String name);
    public int addCategory(Category category);
    public int deleteCategory(int id);
    public Category getCategory(int id);
    public int updateCategory(Category category);
}
