package com.kgc.easybuy.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;


public class RedisConfig {
    @Bean
    @ConditionalOnMissingBean(name = {"redisTemplate"},value = RedisConfig.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException
    {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
