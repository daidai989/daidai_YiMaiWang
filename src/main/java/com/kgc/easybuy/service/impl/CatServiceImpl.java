package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.CatMapper;
import com.kgc.easybuy.pojo.Cat;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CatService;
import com.kgc.easybuy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author daidai
 */
@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatMapper catMapper;
    @Override
    public ResponseMessage getProductsById(String token) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Integer userId = (Integer) map.get("id");

        List<Product> productsById = catMapper.getProductsById(userId);
        return ResponseMessage.success(productsById);
    }

    @Override
    public ResponseMessage addProduct(Cat cat) {
        Product productsByProId = catMapper.getProductsByProId(cat);
        if (productsByProId!=null){
            cat.setCount(productsByProId.getCount()+cat.getCount());
            boolean isFlag  = catMapper.updateProduct(cat);
            if (isFlag) {
                return ResponseMessage.success("商品数量更新成功", isFlag);
            } else {
                return ResponseMessage.error("更新商品数量失败", isFlag);
            }
        }else {
            boolean isFlag = catMapper.addProduct(cat);

            if (isFlag){
                return ResponseMessage.error("添加商品成功",isFlag);
            }
            return ResponseMessage.error("添加商品失败",isFlag);
        }

    }

    @Override
    public ResponseMessage delProduct(Integer id) {
        boolean isFlag = catMapper.deleteProduct(id);
        if (isFlag){
            return ResponseMessage.success("删除商品成功",isFlag);
        }
        return ResponseMessage.error("删除商品失败",isFlag);
    }
    public ResponseMessage delProductList(List ids){
        boolean isFlag = catMapper.delProductList(ids);
        if (isFlag){
            return ResponseMessage.success("遍历删除商品成功",isFlag);
        }
        return ResponseMessage.error("遍历删除商品失败",isFlag);
    }
}
