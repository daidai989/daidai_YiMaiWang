package com.kgc.easybuy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseMessage setStrToRedis(String key,Object obj) {
        String value = JSON.toJSONString(obj);
        stringRedisTemplate.opsForValue().set(key,value,30, TimeUnit.MINUTES);
        return ResponseMessage.success();
    }


    public ResponseMessage getStrByRedis(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        Object obj= JSONObject.parseObject(value, Object.class);
        if (obj != null){
            return  ResponseMessage.success(obj);
        }
        return ResponseMessage.error("获取失败");
    }

    public ResponseMessage delStrByRedis(String key) {
        Boolean delete = stringRedisTemplate.delete(key);
        if (delete){
            return  ResponseMessage.success(delete);
        }
        return ResponseMessage.error("失败",delete);
    }
}
