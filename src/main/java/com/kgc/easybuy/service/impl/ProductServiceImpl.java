package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public Object getProductList(int currentPageNo,int pageSize) {
        PageHelper.startPage(currentPageNo,pageSize);
        List<Product> productList = productMapper.getProductList();
        PageInfo PageInfo = new PageInfo(productList);
        return PageInfo;
    }
}
