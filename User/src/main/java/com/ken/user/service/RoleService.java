package com.ken.user.service;

import com.ken.common.entity.Role;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;

public interface RoleService {

    /*
    根据用户id获得角色
     */
    public ResultBase<ArrayList<Role>> byUserId(Integer userid);
}
