package com.ken.user.service;

import com.ken.common.entity.Permission;
import com.ken.common.entity.http.ResultBase;

import java.util.ArrayList;

public interface PermissionService {
    /*
    根据用户id获得权限
     */
    public ResultBase<ArrayList<Permission>> byUserId(Integer userid);
}
