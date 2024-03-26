package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;

/**
 * @author daidai
 */
public interface CategoryService {

    public ResponseMessage getFirstCategories();
    public ResponseMessage getSecondCategories(int id);
    public ResponseMessage getThirdCategories(int id);
    public ResponseMessage getSecondAllCategories();
    public ResponseMessage getAllCategory();
    public ResponseMessage getAllCategories(Page page, Category category);
    public ResponseMessage checkCategoryName(String name);
    public ResponseMessage addCategory(Category category);
    public ResponseMessage deleteCategory(int id);
    public ResponseMessage getCategory(int id);
    public ResponseMessage updateCategory(Category category);

}
