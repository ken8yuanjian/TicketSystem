package com.ken.ticket.dao;

import com.ken.common.entity.Film;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/*
问题1：
如何捕获dao异常?包括数据库连接级（数据库没启动等）以及表级（表不存在、字段不存在等）
答
在上层调用（一般是service层用try 捕获异常
 */
@Mapper
public interface FilmDao {
    //注解方式变更为配置文件方式，见classpath:mybits/*
    //@Select("select * from film where id=#{id}")
    //启用缓存
    @Cacheable(cacheNames = "filmcache",key = "#id" )
    public Film byId(Integer id);

    //@Select("select * from film")
    public List<Film> list(Film film);

    //@Options(useGeneratedKeys = true,keyProperty = "id")
    //@Insert("insert into film(name,descr) values(#{name},#{descr})")
    public Integer insert(Film film);
}
