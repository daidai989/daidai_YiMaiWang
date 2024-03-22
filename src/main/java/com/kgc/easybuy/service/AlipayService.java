package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.AlipayDBPojo;
import com.kgc.easybuy.pojo.ResponseMessage;


import java.util.Map;

public interface AlipayService {
    public ResponseMessage createOrder(Integer price,String shopName);

    public ResponseMessage addAlipay(AlipayDBPojo alipayDB);

    public ResponseMessage updAlipay(AlipayDBPojo alipayDB);

    public ResponseMessage alipayNotify(Map<String,String> params,AlipayDBPojo alipayDBPojo);
}
