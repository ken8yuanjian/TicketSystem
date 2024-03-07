package com.ken.order.service;

import com.ken.common.entity.Order;
import com.ken.common.entity.http.ResultBase;

import java.util.List;

public interface OrderService {
    public ResultBase< List<Order>> list(Order order);
    /* 购票接口 */
    public ResultBase<Integer> buy(Integer memberId,Integer schedulingId);
}
