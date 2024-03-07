package com.ken.ticket.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.common.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//不使用配置文件，采用mybatis plus 接口方式实现
@Mapper
public interface RoomDao extends BaseMapper<Room>
{
}
