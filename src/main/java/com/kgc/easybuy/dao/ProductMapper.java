package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Collect;
import com.kgc.easybuy.pojo.Cat;
import com.kgc.easybuy.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface ProductMapper {
    public List<Product> getProducts();
    public List<Product> getProductList();
    public List<Product> getProductByCategoryId(int parentId);
    public List<Product> getHotProduct();
    public Product getProductById(int id);
    public boolean delProById(@Param("id") Integer id);
    public boolean addProduct(Product product);
    public boolean updateProduct(Product products);
    public boolean updateFileId(@Param("fileId")Integer fileId,@Param("id")Integer id);
    public Product getProductByLogin(String loginName);
    public List<Product> setProductTes();
    public List<Product> getCollectProduct(Collect collect);
    public List<Product> getRecommendProduct(Product product);
    public List<Product> getHistoryProduct(int userId);
    public boolean updateStock(@Param("id") Integer id, @Param("stock") Integer stock);
    public List<Product> getProductListByproductList(@Param("ids")List ids);
    public List<Product> getProductByFirstCategoryId(int id);
    public List<Product> getProductBySecondCategoryId(int id);
    public List<Product> getProductByThreeCategoryId(int id);
}
