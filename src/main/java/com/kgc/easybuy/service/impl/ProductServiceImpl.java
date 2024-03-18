package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Object getProductList(int currentPageNo,int pageSize) {
        PageHelper.startPage(currentPageNo,pageSize);
        List<Product> productList = productMapper.getProductList();
        PageInfo PageInfo = new PageInfo(productList);
        return PageInfo;
    }

    @Override
    public Object getProductByCategoryId() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        List<List> productList = new ArrayList<>();
        for (Category category: firstCategories) {
            PageHelper.startPage(1,6);
            List<Product> products = productMapper.getProductByCategoryId(category.getId());
            PageInfo pageInfo = new PageInfo(products);
            productList.add(pageInfo.getList());
        }
        return productList;
    }
}
