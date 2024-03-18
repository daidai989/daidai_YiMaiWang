package com.kgc.easybuy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseMessage setStrToRedis(String key,Object obj) {
        String value = JSON.toJSONString(obj);
        stringRedisTemplate.opsForValue().set(key,value);
        return new ResponseMessage(200,null,null);
    }


    public ResponseMessage getStrByRedis(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        Object obj= JSONObject.parseObject(value, Object.class);
        if (obj != null){
            return new ResponseMessage(200,"成功",obj);
        }
        return new ResponseMessage(201,"失败",null);
    }

    public ResponseMessage delStrByRedis(String key) {
        Boolean delete = stringRedisTemplate.delete(key);
        if (delete){
            return new ResponseMessage(200,"成功",delete);
        }
        return new ResponseMessage(201,"失败",delete);
    }
}
