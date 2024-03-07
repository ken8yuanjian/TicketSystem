package com.ken.ticket.service;

import com.ken.common.entity.Seat;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;
import java.util.List;

public class SeatServiceImpl implements SeatService {
    @Override
    public ResultBase<Integer> seatsByRoomId(Integer roomId) {
        return null;
    }

    @Override
    public ResultBase<ArrayList<Seat>> list(Integer roomId, Seat seat) {
        return null;
    }

    @Override
    public ResultBase<Seat> byId(Integer id) {
        return null;
    }
}
