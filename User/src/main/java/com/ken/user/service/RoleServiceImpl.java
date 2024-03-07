package com.ken.user.service;

import com.ken.common.entity.Role;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao roleDao;

    @Override
    public ResultBase<ArrayList<Role>> byUserId(Integer userid) {

        return ResultBase.success( roleDao.listByUserId(userid));
    }
}
