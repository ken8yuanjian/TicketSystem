package com.ken.ticket.service;

import com.ken.common.entity.Seat;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;
import java.util.List;

public interface SeatService {
    /*
    根据放映厅id获得座位数
    roomId 放映厅id
     */
    public ResultBase<Integer> seatsByRoomId(Integer roomId);
    /*
    根据放映厅id获得座位列表
    roomId 放映厅id
    seat 过滤参数
     */
    public ResultBase<ArrayList<Seat>> list(Integer roomId, Seat seat);
    /*
    根据座位id获得座位详情
    id 座位id
     */
    public ResultBase<Seat> byId(Integer id);
}
