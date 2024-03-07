package com.ken.user.service;

import com.ken.common.entity.User;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;

public interface UserService {
    public ResultBase<User> byId(Integer id);

    public ResultBase<User> byUserName(String username);

    public ResultBase<ArrayList<User>> list(User user);

    public ResultBase<User> insert(User user);

    public ResultBase deleteById(Integer id);

    public ResultBase<String> login(String username,String passwd);

}
