package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daidai
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseMessage getFirstCategories() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        return ResponseMessage.success(firstCategories);
    }

    @Override
    public ResponseMessage getSecondCategories(int id) {
        List<Category> secondCategories = categoryMapper.getSecondCategories(id);
        return ResponseMessage.success(secondCategories);
    }

    @Override
    public ResponseMessage getThirdCategories(int id) {
        List<Category> thirdCategories = categoryMapper.getThirdCategories(id);
        return ResponseMessage.success(thirdCategories);
    }

    @Override
    public ResponseMessage getSecondAllCategories() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        List<List> productList = new ArrayList<>();
        for (Category category: firstCategories) {
            List<Category> secondCategories = categoryMapper.getSecondCategories(category.getId());
            productList.add(secondCategories);
        }
        return ResponseMessage.success(productList);
    }

    @Override
    public ResponseMessage getAllCategory() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        for (Category category: firstCategories) {
            List<Category> secondCategories = categoryMapper.getSecondCategories(category.getId());
            for (Category cg :secondCategories) {
                List<Category> thirdCategories = categoryMapper.getThirdCategories(cg.getId());
                cg.setChildCategory(thirdCategories);
            }
            category.setChildCategory(secondCategories);
        }
        if (firstCategories != null){
            return ResponseMessage.success(firstCategories);
        }
        return ResponseMessage.error("没有分类");
    }

    @Override
    public ResponseMessage getAllCategories(Page page, Category category) {
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Category> allCategories = categoryMapper.getAllCategories(category);
        PageInfo pageInfo= new PageInfo(allCategories);
        if (pageInfo.getList() != null){
            return ResponseMessage.success("请求成功",pageInfo);
        }
        return ResponseMessage.error("没有数据！");
    }

    @Override
    public ResponseMessage checkCategoryName(String name) {
        String str = categoryMapper.checkCategoryName(name);
        if (str != null){
            return ResponseMessage.error("重名了！");
        }
        return ResponseMessage.success("不重名");
    }

    public ResponseMessage addCategory(Category category) {
        int count = categoryMapper.addCategory(category);
        if (count > 0){
            return ResponseMessage.success("添加成功！");
        }
        return ResponseMessage.error("添加失败！");
    }

    @Override
    public ResponseMessage deleteCategory(int id) {
        int count = categoryMapper.deleteCategory(id);
        if (count > 0){
            return ResponseMessage.success("成功！");
        }
        return ResponseMessage.error("删除失败!");
    }

    @Override
    public ResponseMessage getCategory(int id) {
        Category category = categoryMapper.getCategory(id);
        if (category != null){
            return ResponseMessage.success("成功",category);
        }
        return ResponseMessage.error("没有数据");
    }

    @Override
    public ResponseMessage updateCategory(Category category) {
        int count = categoryMapper.updateCategory(category);
        if (count > 0){
            return ResponseMessage.success("成功！");
        }
        return ResponseMessage.error("删除失败!");
    }
}
