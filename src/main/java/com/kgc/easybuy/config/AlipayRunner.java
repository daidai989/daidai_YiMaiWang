package com.kgc.easybuy.config;


import com.kgc.easybuy.pojo.AlipayDBPojo;
import com.kgc.easybuy.service.impl.AlipayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayRunner implements Runnable{
    @Autowired
    private AlipayServiceImpl alipayServiceImpl;
    @Autowired
    private AlipayDBPojo alipayDBPojo;

    public AlipayDBPojo getAlipayDBPojo() {
        return alipayDBPojo;
    }

    public void setAlipayDBPojo(AlipayDBPojo alipayDBPojo) {
        this.alipayDBPojo = alipayDBPojo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            alipayServiceImpl.updAlipay(alipayDBPojo);
        }
    }
}
