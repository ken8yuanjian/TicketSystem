package com.ken.user.service;

import com.ken.common.entity.Permission;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    PermissionDao permissionDao;

    @Override
    public ResultBase<ArrayList<Permission>> byUserId(Integer userid) {
        return ResultBase.success(permissionDao.listByUserId(userid));
    }
}
