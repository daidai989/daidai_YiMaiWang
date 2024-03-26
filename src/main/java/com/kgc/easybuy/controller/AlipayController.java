package com.kgc.easybuy.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.kgc.easybuy.pojo.AlipayDBPojo;
import com.kgc.easybuy.pojo.AlipayPojo;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.impl.AlipayServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin("*")

public class AlipayController {
    @Autowired
    private AlipayServiceImpl alipayServiceImpl;
    @Autowired
    private AlipayPojo alipayPojo;
    @Autowired
    private AlipayDBPojo alipayDBPojo;

    @RequestMapping("createOrder")
    public void createOrder(HttpServletResponse resp,Integer price,String shopName,String serialNumber){
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = null;
        try {
            pw  = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ResponseMessage msg = alipayServiceImpl.createOrder(price,shopName,serialNumber);
        if(msg.getCode()==200){
            pw.print(msg.getData());
            return;
        }
        pw.print(false);
    }


    @RequestMapping("alipayNotify")
    @ResponseBody
    public void alipayNotify(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html;charset=utf-8");
        Map<String, String[]> values = req.getParameterMap();
        Map<String,String> params=new HashMap<>();
        for (Map.Entry entry:values.entrySet()){
            String[] value = (String[])entry.getValue();
            String key = (String)entry.getKey();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < value.length; i++) {
                if(i==value.length-1){
                    sb.append(value[i]);
                }else{
                    sb.append(value[i]+",");
                }
            }
            params.put(key,sb.toString());
            System.out.println("params:"+params);
            params.remove("sign_type");
        }
        alipayDBPojo.setOut_trade_no(params.get("out_trade_no"));
        alipayDBPojo.setTotal_amount(Integer.parseInt(params.get("total_amount")));
        ResponseMessage msg = alipayServiceImpl.alipayNotify(params,alipayDBPojo);
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(msg.getCode()==200){
            pw.print("success");
        } else {
            pw.print("fail");
        }
    }

}
