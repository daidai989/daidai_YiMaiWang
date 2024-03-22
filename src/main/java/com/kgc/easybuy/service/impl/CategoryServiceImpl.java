package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.pojo.Category;
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
}
