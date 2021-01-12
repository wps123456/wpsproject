package com.wps.studyredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Title RedisTemplateConfig
 * @Description Redis序列化，解决保存乱码的问题
 * @auther wps
 * @Date 2021/1/1219:15
 */
@Configuration
public class RedisTemplateConfig {
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate redisTemplateInit(){
        RedisSerializer stringSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return this.redisTemplate;
    }
}
