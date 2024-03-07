package com.ken.ticket.controller;

import com.ken.common.entity.Film;
import com.ken.common.entity.Room;
import com.ken.common.entity.http.ResultBase;
import com.ken.ticket.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public ResultBase list(Room room)
    {
        return roomService.list(room);
    }
    @GetMapping("/{id}")
    public ResultBase byId(@PathVariable("id") Integer id)
    {
        return roomService.byId(id);
    }
    @GetMapping("/insert")
    public ResultBase<Room> insert(Room room)
    {
        return ResultBase.success( roomService.save(room));

    }

}
