package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
