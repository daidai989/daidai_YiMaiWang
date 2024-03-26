package com.kgc.easybuy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.kgc.easybuy.dao.AlipayDao;
import com.kgc.easybuy.dao.OrderMapper;
import com.kgc.easybuy.pojo.AlipayDBPojo;
import com.kgc.easybuy.pojo.AlipayPojo;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.AlipayService;

import okhttp3.internal.Internal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private AlipayPojo alipayPojo;
    @Autowired
    private AlipayDao alipayDao;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseMessage createOrder(Integer price, String shopName,String serialNumber) {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayPojo.getGateway(), alipayPojo.getAppId(), alipayPojo.getPrivateKey(), "json", "utf-8", alipayPojo.getAlipayPublicKey(), "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl(alipayPojo.getNotifyUrl());
        //同步跳转地址，仅支持http/https
        request.setReturnUrl(alipayPojo.getReturnUrl());
        /******必传参数******/
        JSONObject bizContent = new JSONObject();
        //商户订单号，商家自定义，保持唯一性
        if (serialNumber==null||"".equals(serialNumber)){
            String uuid = UUID.randomUUID().toString();
            bizContent.put("out_trade_no", uuid);
        }else {
            bizContent.put("out_trade_no", serialNumber);
        }
        //支付金额，最小值0.01元
        bizContent.put("total_amount", price);
        //订单标题，不可使用特殊符号
        bizContent.put("subject", shopName);
        //电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        /******可选参数******/
        //bizContent.put("time_expire", "2022-08-01 22:00:00");

        //// 商品明细信息，按需传入
        //JSONArray goodsDetail = new JSONArray();
        //JSONObject goods1 = new JSONObject();
        //goods1.put("goods_id", "goodsNo1");
        //goods1.put("goods_name", "子商品1");
        //goods1.put("quantity", 1);
        //goods1.put("price", 0.01);
        //goodsDetail.add(goods1);
        //bizContent.put("goods_detail", goodsDetail);

        //// 扩展信息，按需传入
        //JSONObject extendParams = new JSONObject();
        //extendParams.put("sys_service_provider_id", "2088511833207846");
        //bizContent.put("extend_params", extendParams);

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request, "POST");
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        // 如果需要返回GET请求，请使用
        // AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
        String pageRedirectionData = response.getBody();

        if (response.isSuccess()) {
            return  ResponseMessage.success(pageRedirectionData);
        } else {
            return ResponseMessage.error("调用失败");
        }
    }

    @Override
    public ResponseMessage addAlipay(AlipayDBPojo alipayDB) {
        int count = alipayDao.addAlipay(alipayDB);
        if(count==1){
            return  ResponseMessage.success();
        }
        return ResponseMessage.error("添加失败");
    }
    @Override
    public ResponseMessage updAlipay(AlipayDBPojo alipayDB){
        int count = alipayDao.updAlipay(alipayDB);
        if(count==1){
            return  ResponseMessage.success();
        }
        return ResponseMessage.error("修改失败");
    }

    @Override
    public ResponseMessage alipayNotify(Map<String, String> params, AlipayDBPojo alipayDBPojo) {
        this.addAlipay(alipayDBPojo);
        try {
            //验签
            boolean rsa2= AlipaySignature.rsaCheckV1(params,alipayPojo.getAlipayPublicKey(),"utf-8", "RSA2");
            if(rsa2){
                String tradeStatus = params.get("trade_status");
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    this.updAlipay(alipayDBPojo);
                    boolean flag = orderMapper.updateOrderStatusWithPay(alipayDBPojo.getOut_trade_no());

                    if (flag) {
                        return ResponseMessage.error("没有支付");
                    }
                    return  ResponseMessage.success("支付成功");
                }else {
                    return  ResponseMessage.error("支付失败");
                }

            }else{
                return  ResponseMessage.error("没有通过验证");

            }
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }

    }
}
