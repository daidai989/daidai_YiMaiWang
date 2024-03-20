package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.*;

import java.util.List;

public interface ProductService {
    public ResponseMessage getProductList(int currentPageNo, int pageSize);

    public ResponseMessage getHotProduct();

    public ResponseMessage getProductByCategoryId();

    public ResponseMessage getProductById(int id);

    public ResponseMessage getRecommendProduct(Product product);

    public ResponseMessage setProductTes();

    public ResponseMessage getProFromEs(EsSelect esSelect);
}
