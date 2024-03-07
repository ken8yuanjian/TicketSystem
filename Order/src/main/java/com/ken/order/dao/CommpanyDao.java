package com.ken.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.common.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommpanyDao extends BaseMapper<Company> {

    public int payTo(Integer id,float amount);
}
