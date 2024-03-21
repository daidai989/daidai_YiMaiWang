package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;

import java.util.List;

/**
 * @author daidai
 */
public interface ProductMapper {
    public List<Product> getProductList();
    public List<Product> getProductByFirstCategoryId(int id);
    public List<Product> getHotProduct();
    public Product getProductById(int id);
    public List<Product> getRecommendProduct(Product product);
    public List<Product> setProductTes();
    public List<Product> getProductBySecondCategoryId(int id);
    public List<Product> getProductByThreeCategoryId(int id);
}
