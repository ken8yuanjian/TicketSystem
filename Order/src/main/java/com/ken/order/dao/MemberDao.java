package com.ken.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao extends BaseMapper<User> {
    public User byIdForUpdate(Integer id);
}
