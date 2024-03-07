package com.ken.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

//定义使用redis作为缓存
public class MyCacheConfig {
    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public MyCache myCache(){
        return new MyCacheForRedis(redisTemplate);
    }
}
