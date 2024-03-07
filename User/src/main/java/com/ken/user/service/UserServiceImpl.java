package com.ken.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ken.common.cache.MyCache;
import com.ken.common.entity.User;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private MyCache myCache;


    @Override
    public ResultBase<User> byId(Integer id) {
        return ResultBase.success( userDao.selectById(id));
    }
    @Override
    public ResultBase<User> byUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return ResultBase.success( userDao.selectOne(wrapper));
    }

    @Override
    public ResultBase<ArrayList<User>> list(User user) {
        return ResultBase.success(userDao.list(user));
    }

    @Override
    public ResultBase<User> insert(User user) {
        if (user.getUsername()==null || user.getUsername().length()<=0)
            return ResultBase.fail(1,"无效账户名！");
        if (user.getPasswd()==null || user.getPasswd().length()<=0)
            return ResultBase.fail(1,"无效密码！");
        if (user.getName()==null || user.getName().length()<=0)
            return ResultBase.fail(1,"无效用户名！");
        User one = byUserName(user.getUsername()).getData();
        if (one != null && one.getId()!=0 )
            return ResultBase.fail(1,"该用户已存在！");
        user.setId(null);//取消id
        userDao.insert(user);
        return ResultBase.success(user);
    }
    @Override
    public ResultBase deleteById(Integer id) {
        userDao.deleteById(id);
        return ResultBase.success(id);
    }

    @Override
    public ResultBase<String> login(String username, String passwd) {
        if (null == username || username.length()<=0
            || null == passwd || passwd.length()<=0)
            return ResultBase.fail(1,"无效用户名/密码");
        //1、获取shiro subject 对象
        Subject subject = SecurityUtils.getSubject();
        //2、封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(username,passwd);
        //3、调用login方法进行认证
        try{
            subject.login(token);
            // session数据
            Session session = subject.getSession();
            session.setTimeout(60*1000);    //设置session有效期
            System.out.println("*************userservice create session:"+session.getId().toString());
            return ResultBase.success(subject.getSession().getId().toString(),"登录成功");
        }catch (AuthenticationException e){
            return ResultBase.fail(1,e.getMessage());
        }
    }
}
