package com.ken.ticket.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.common.entity.http.ResultBase;
import com.ken.ticket.dao.RoomDao;
import com.ken.common.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
使用mybatis-plus 通用服务接口
 */

@Service
public class RoomServiceImpl extends ServiceImpl<RoomDao,Room> implements RoomService{
    @Autowired
    private RoomDao roomDao;


    @Override
    public ResultBase<ArrayList<Room>> list(Room room) {
        QueryWrapper<Room> wrapper = new QueryWrapper<Room>();
        if (room!=null && room.getId()!=null)
            wrapper.or().eq("id",room.getId());
        if (room!=null && room.getName()!=null)
            wrapper.or().like("name",room.getName());
        return ResultBase.success(roomDao.selectList(wrapper));
    }
    @Override
    public ResultBase<Room> byId(Integer id) {
        QueryWrapper<Room> wrapper = new QueryWrapper<Room>();
        wrapper.eq("id",id);
        return ResultBase.success(roomDao.selectOne(wrapper));
    }
}
