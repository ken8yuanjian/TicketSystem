package com.ken.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
    public List<Role> listByUserId(Integer userid);
}
