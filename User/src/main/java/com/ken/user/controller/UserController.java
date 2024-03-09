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
