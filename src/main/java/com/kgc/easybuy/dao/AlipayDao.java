package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.AlipayDBPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlipayDao {

    //添加
    public int addAlipay(AlipayDBPojo alipayDB);
    //修改
    public int updAlipay(AlipayDBPojo alipayDB);
}
