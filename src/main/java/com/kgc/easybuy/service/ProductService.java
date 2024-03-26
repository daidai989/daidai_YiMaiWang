package com.kgc.easybuy.service;


import com.kgc.easybuy.pojo.*;
import org.apache.ibatis.annotations.Param;

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
    public ResponseMessage setProductTes();
    public ResponseMessage getProFromEs(EsSelect esSelect);
    public ResponseMessage getCollectProduct(Collect collect,Page page);
    public ResponseMessage getRecommendProduct(Product product);
    public ResponseMessage getHistoryProduct(int userId);
    public ResponseMessage getProductListByproductList(List ids);
    public ResponseMessage checkProductExitsByCategoryId(Category category);
}
