package com.ken.user.controller;

import com.ken.common.cache.MyCache;
import com.ken.common.entity.User;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    /*
    volatile 防止指令重排和保证可见性
    多个线程并发的时候，可能有多个线程同时发现 accessThread==null，
    同时分配内存并赋值，就出现了多线程安全问题，volatile保证这种情况不会发生

    另外 accssThread赋值时，仍有可能发生多线程安全问题，因为 volatile不保证原子性，
    所以仍需结合 synchronized 或 Lock 对 accessThread进行赋值，保证原子性.
     */
    public volatile ArrayList<Integer> accessThread; //记录访问过此Controller的线程；


    @Autowired
    UserService userService;

    @Autowired
    MyCache myCache;

    // restful 标准 实现
    @GetMapping("")
    @RequiresPermissions("userservice:user:query")
    public ResultBase<ArrayList<User>> get(User user)
    {

        return userService.list(user);
    }

    @GetMapping("/{id}")
    @RequiresPermissions("userservice:user:query")
    public ResultBase< User > get(@PathVariable Integer id)
    {
        return userService.byId(id);
    }

    @PutMapping("")
    @RequiresPermissions("userservice:user:insert")
    public ResultBase<User> put(User user)
    {
        return userService.insert(user);
    }

    @DeleteMapping ("/{id}")
    @RequiresPermissions("userservice:user:delete")
    public ResultBase< User > delete(@PathVariable Integer id)
    {
        return userService.deleteById(id);
    }



}
