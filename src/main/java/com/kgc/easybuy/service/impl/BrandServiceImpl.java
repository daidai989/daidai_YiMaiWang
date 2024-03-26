package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.BrandMapper;
import com.kgc.easybuy.pojo.Brand;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public ResponseMessage getBrand() {
        List<Brand> brand = brandMapper.getBrand();
        if (brand == null){
            return ResponseMessage.error("数据为null");
        }
        return ResponseMessage.success(brand);
    }

}