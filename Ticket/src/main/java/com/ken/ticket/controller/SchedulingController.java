package com.ken.ticket.controller;

import com.ken.common.entity.Film;
import com.ken.common.entity.Room;
import com.ken.common.entity.Scheduling;
import com.ken.common.entity.http.ResultBase;
import com.ken.ticket.service.SchedulingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/list")
    public ResultBase list(Scheduling scheduling)
    {
        return schedulingService.list(scheduling);
    }
    @GetMapping("/{id}")
    public ResultBase byId(@PathVariable("id") Integer id)
    {
        return schedulingService.byId(id);
    }

    @GetMapping("/insert")
    @RequiresPermissions("ticketservice:Scheduling:insert")
    public ResultBase insert(Scheduling scheduling ){
        return schedulingService.insert(scheduling);
    }
}
