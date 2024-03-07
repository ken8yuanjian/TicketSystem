package com.ken.ticket.service;



import com.ken.common.entity.Film;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;
import java.util.List;

public interface FilmService {
    /*
    获取电影列表
    film 筛选参数
     */
    public ResultBase<ArrayList<Film>> list(Film film);

    /*
    根据id获取电影列表
    id 电影id
     */
    public ResultBase< Film>  byId(Integer id);

    /*
    插入一个电影记录
    film    电影记录
     */
    public ResultBase<Integer> insert(Film film);
}
