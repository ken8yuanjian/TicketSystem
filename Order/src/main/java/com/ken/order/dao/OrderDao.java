package com.ken.order.dao;

import com.ken.common.entity.Order;
import com.ken.common.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

    public List<Order> list(Order order);

    /*
    从库存中锁定一张票
     */
    public Ticket ticketByIdforUpdate(Integer schedulingid);
    /*
    售出电影票
     */
    public Integer updateTicket(Integer ticketid,Integer memberid);

    /*
    生成一张订单信息
     */
    public Integer insert(Integer memberid,Integer ticketid);
}
