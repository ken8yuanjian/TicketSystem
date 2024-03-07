package com.ken.ticket.dao;

import com.ken.common.entity.Scheduling;
import com.ken.common.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketDao {

    /*
    根据一个电影排期开始放票，产生所有待售电影票
     */
    public Integer releaseByScheduling(Scheduling scheduling );

    /*
    获得所有电影票
    ticket 过滤参数
     */
    public List<Ticket> list(Ticket ticket);
}
