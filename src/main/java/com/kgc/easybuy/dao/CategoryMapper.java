package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Category;

import java.util.List;

/**
 * @author daidai
 */
public interface CategoryMapper {
    public List<Category> getFirstCategories();
    public List<Category> getSecondCategories(int parentId);
    public List<Category> getThirdCategories(int parentId);
}
