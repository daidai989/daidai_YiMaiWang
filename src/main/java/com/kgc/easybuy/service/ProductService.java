package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;

import java.util.List;

public interface ProductService {
    public Object getProductList(int currentPageNo,int pageSize);

    public Object getProductByCategoryId();
}
