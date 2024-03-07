package com.ken.order.controller;


import com.ken.common.entity.Order;
import com.ken.common.entity.http.ResultBase;
import com.ken.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public ResultBase list(Order order){
        return orderService.list(order);
    }

    @GetMapping("/buy")
    public ResultBase buy(Integer memberId, Integer schedulingId){
        return orderService.buy(memberId,schedulingId);
    }
}
