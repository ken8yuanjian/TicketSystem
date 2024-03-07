package com.ken.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.common.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao extends BaseMapper<Permission> {
    public List<Permission> listByUserId(Integer userid);
}
