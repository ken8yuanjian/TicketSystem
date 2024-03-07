package com.ken.ticket.dao;

import com.ken.common.entity.Film;
import com.ken.common.entity.Scheduling;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchedulingDao {

    public List<Scheduling> list(Scheduling scheduling);

    public Scheduling byId(Integer id);

    public Integer insert(Scheduling scheduling);


}
