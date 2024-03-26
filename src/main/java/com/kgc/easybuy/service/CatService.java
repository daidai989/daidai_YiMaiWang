package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.Cat;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author daidai
 */
public interface CatService {

    public ResponseMessage getProductsById(String token);
    public ResponseMessage addProduct(Cat cat);
    public ResponseMessage delProduct(Integer id );
    public ResponseMessage delProductList(List ids);
    public ResponseMessage updateProducts(List<Cat> ids);
    public ResponseMessage getProductListByLst(List ids);

}
