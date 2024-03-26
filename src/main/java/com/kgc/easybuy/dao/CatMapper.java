package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Cat;
import com.kgc.easybuy.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface CatMapper {
    public List<Product> getProductsById(Integer userId);
    public boolean addProduct( Cat cat );
    public boolean deleteProduct( Integer id);
    public Product getProductsByProId( Cat cat);
    public boolean updateProduct(Cat cat);
    public boolean delProductList(@Param("ids") List ids);

    public boolean updateCat(Cat cat);
    public List<Cat> getProductListByLst(@Param("ids")List ids);


}
