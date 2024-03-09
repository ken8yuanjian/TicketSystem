package com.ken.user.controller;

import com.ken.common.cache.MyCache;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.entity.SessionData;
import com.ken.user.service.UserService;
import com.ken.user.thread.RedisRWThread;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    MyCache myCache;

    @ApiOperation("登录接口")
    @GetMapping ("")
    public ResultBase<String> login(@ApiParam("用户名") String username,
                                    @ApiParam("密码") String passwd)
    {
        return userService.login(username,passwd);
    }

    @GetMapping("/session")
    public ResultBase<SessionData> session()
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.touch();
        SessionData sessionData = new SessionData(session.getId().toString(),
                session.getLastAccessTime().toString(),"test");
        return ResultBase.success(sessionData);

    }
    @GetMapping("/thread")
    @RequiresGuest
    public ResultBase thread(){

        Thread thread = new Thread( new RedisRWThread(myCache) );
        thread.start();
        return ResultBase.success(thread.toString());
    }
    @GetMapping("/setcookie")
    @RequiresGuest
    public ResultBase setcookie(HttpServletResponse response){
        Cookie[] cookies = {new Cookie("ca","va"),
                new Cookie("cb","vb")};
        String str="";
        for (Cookie cookie : cookies) {
            str = str + cookie.getName() + " = " + cookie.getValue() + " , ";
            response.addCookie(cookie);
        }
        return ResultBase.success("set cookie: " + str);
    }
    @GetMapping("/getcookie")
    @RequiresGuest
    public ResultBase getcookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String str="";
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                str = str + cookie.getName() + " = " + cookie.getValue() + " , ";
            }
        }
        return ResultBase.success("get cookie: "+str);
    }
}
