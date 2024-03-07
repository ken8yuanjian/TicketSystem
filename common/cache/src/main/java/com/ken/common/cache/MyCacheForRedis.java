package com.ken.common.cache;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyCacheForRedis implements MyCache{
    private volatile RedisTemplate redisTemplate;
    public MyCacheForRedis(){
    }
    public MyCacheForRedis(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void delete(Object key) {
        redisTemplate.delete(key);
    }

    @Override
    public Boolean expire(Object key, long seconds) {
        return redisTemplate.expire(key,seconds, TimeUnit.SECONDS);
    }

    @Override
    public long getExpire(Object key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public void setForValue(Object key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public <V> V getForValue(Object key) {
        return (V) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <K,V> void  setForHash(Object key, Map<K, V> map) {
        redisTemplate.opsForHash().putAll(key,map);
    }

    @Override
    public <K,V> Map<K, V> getForHash(Object key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void putForHash(Object key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key,hashKey,value);
    }

    @Override
    public <V> V getForHash(Object key, Object hashKey) {
        return (V) redisTemplate.opsForHash().get(key,hashKey);
    }
}
