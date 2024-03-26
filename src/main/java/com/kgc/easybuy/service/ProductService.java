package com.kgc.easybuy.service;


import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;

import java.util.List;

public interface ProductService {

    public ResponseMessage getProducts();
    public Object getProductList(int currentPageNo,int pageSize);

    public ResponseMessage getHotProduct();
    public ResponseMessage getProductByCategoryId();

    public ResponseMessage getProductById(int id);
    public ResponseMessage viewProductsList(String currentPageNo,String name);
    public ResponseMessage delProById( Integer id);
    public ResponseMessage addProduct(Product product);
    public ResponseMessage updateProduct(Product products);
    public ResponseMessage getProductByLogin(String loginName);
    public ResponseMessage getRecommendProduct(int parentId,int id);

    public ResponseMessage getProductListByproductList(List ids);

}
