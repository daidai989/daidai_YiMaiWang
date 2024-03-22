package com.kgc.easybuy.service;


import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    public ResponseMessage getProducts();
    public Object getProductList(int currentPageNo,int pageSize);

    public List<Product> getHotProduct();

    public Object getProductByCategoryId();

    public Product getProductById(int id);
    public ResponseMessage viewProductsList(String currentPageNo,String name);
    public ResponseMessage delProById( Integer id);
    public ResponseMessage addProduct(Product product);
    public ResponseMessage updateProduct(Product products);
    public ResponseMessage getProductByLogin(String loginName);
}
