package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.service.ProductService;
import com.kgc.easybuy.util.EncodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        List<Product> encodingPro = EncodingUtil.encoding(productList);
        PageInfo PageInfo = new PageInfo(encodingPro);
        return PageInfo;
    }

    @Override
    public List<Product> getHotProduct() {
        List<Product> productList = productMapper.getHotProduct();
        List<Product> encodingPro = EncodingUtil.encoding(productList);
        return encodingPro;
    }

    @Override
    public Object getProductByCategoryId() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        List<List> productList = new ArrayList<>();
        for (Category category: firstCategories) {
            PageHelper.startPage(1,6);
            List<Product> products = productMapper.getProductByCategoryId(category.getId());
            List<Product> encodingPro = EncodingUtil.encoding(products);
            PageInfo pageInfo = new PageInfo(encodingPro);
            productList.add(pageInfo.getList());
        }
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        Product product = productMapper.getProductById(id);
        try {
            product.setFilePath(URLEncoder.encode(product.getFilePath(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return product;
    }
}
