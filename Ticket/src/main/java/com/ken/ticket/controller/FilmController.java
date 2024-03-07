package com.ken.ticket.controller;

import com.ken.common.entity.Film;
import com.ken.common.entity.http.ResultBase;
import com.ken.ticket.service.FilmService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    /*
    操作request、response示例
    操作session 、 cookie示例
     */
    @GetMapping("/list")
    public ResultBase list(Film film)
    {
        return filmService.list(film);
    }

    @GetMapping("/{id}")
    public ResultBase byId(@PathVariable("id") Integer id)
    {
        return filmService.byId(id);
    }

    /*
    先插入电影排期，会自动生成电影票
    scheduling/insert?price=30&film.id=2&room.id=2&start=2023-10-01%2010:00:00&end=2023-10-01%2012:00:00
     */
    @GetMapping("/insert")
    @RequiresPermissions("ticketservice:film:insert")
    public ResultBase insert(Film film ){
        return filmService.insert(film);
    }
}
