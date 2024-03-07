package com.ken.ticket.service;

import com.ken.common.entity.Film;
import com.ken.common.entity.Scheduling;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;
import java.util.List;

public interface SchedulingService {
    /*
    获得电影排期列表
    scheduling 过滤参数
     */
    public ResultBase<ArrayList<Scheduling>> list(Scheduling scheduling);
    /*
    根据id获取电影排期的详情
    id  排期id
     */
    public ResultBase<Scheduling> byId(Integer id);
    /*
    新增一个电影排期
     */
    public ResultBase<Integer> insert(Scheduling scheduling);
}
