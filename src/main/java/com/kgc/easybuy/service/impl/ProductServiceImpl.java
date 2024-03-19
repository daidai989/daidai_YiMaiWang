package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
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
    public ResponseMessage getProductList(int currentPageNo, int pageSize) {
        PageHelper.startPage(currentPageNo,pageSize);
        List<Product> productList = productMapper.getProductList();
        List<Product> encodingPro = EncodingUtil.encoding(productList);
        PageInfo PageInfo = new PageInfo(encodingPro);
        return ResponseMessage.success(PageInfo);
    }

    @Override
    public ResponseMessage getHotProduct() {
        List<Product> productList = productMapper.getHotProduct();
        List<Product> encodingPro = EncodingUtil.encoding(productList);
        return ResponseMessage.success(encodingPro);
    }

    @Override
    public ResponseMessage getProductByCategoryId() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        List<List> productList = new ArrayList<>();
        for (Category category: firstCategories) {
            PageHelper.startPage(1,6);
            List<Product> products = productMapper.getProductByCategoryId(category.getId());
            List<Product> encodingPro = EncodingUtil.encoding(products);
            PageInfo pageInfo = new PageInfo(encodingPro);
            productList.add(pageInfo.getList());
        }
        return ResponseMessage.success(productList);
    }

    @Override
    public ResponseMessage getProductById(int id) {
        Product product = productMapper.getProductById(id);
        try {
            product.setFilePath(URLEncoder.encode(product.getFilePath(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ResponseMessage.success(product);
    }

    @Override
    public ResponseMessage getRecommendProduct(Product product) {
        List<Product> recommendProduct = productMapper.getRecommendProduct(product);
        List<Product> productList = EncodingUtil.encoding(recommendProduct);
        return ResponseMessage.success(productList);
    }
}
