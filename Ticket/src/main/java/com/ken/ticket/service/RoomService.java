package com.ken.ticket.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.common.entity.Room;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;
import java.util.List;

/*
使用mybatis-plus 通用服务接口
 */
public interface RoomService extends IService<Room> {
    /*
    获得放映间列表
    room 筛选参数
     */
    public ResultBase<ArrayList<Room>> list(Room room);
    /*
    根据id获得放映间
     */
    public ResultBase<Room> byId(Integer id);
}
