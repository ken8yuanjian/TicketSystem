package com.ken.common.cache;

import java.util.Map;

public interface MyCache {
    //删除一级键值
    public void delete(Object key);
    //设置键有效期（秒）
    public Boolean expire(Object key, long seconds);
    public long getExpire(Object key);

    //Value操作
    public void setForValue(Object key,Object value);
    public <V> V getForValue(Object key);

    //Hash操作
    public <K,V>  void setForHash(Object key, Map<K,V> map);
    public <K,V> Map<K,V> getForHash(Object key);

    public void putForHash(Object key, Object hashKey, Object value);
    public <V> V getForHash(Object key, Object hashKey);

}
