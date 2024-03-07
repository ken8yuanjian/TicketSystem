package com.ken.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    public List<User> list(User user);
}
