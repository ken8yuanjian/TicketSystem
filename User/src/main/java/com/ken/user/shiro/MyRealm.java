package com.ken.user.shiro;

import com.ken.common.entity.Permission;
import com.ken.common.entity.Role;
import com.ken.common.entity.User;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.service.PermissionService;
import com.ken.user.service.RoleService;
import com.ken.user.service.UserService;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/*
自定义实现shiro的认证和授权
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashMap<String,Object> principal = (HashMap<String,Object>)principalCollection.getPrimaryPrincipal();
        ArrayList<Role> roles = (ArrayList<Role>)principal.get("roles");
        if (null != roles)
            roles.forEach( role -> info.addRole(role.getName()));
        ArrayList<Permission> permissions = (ArrayList<Permission>)principal.get("permissions");
        if (null !=permissions)
            permissions.forEach( permission -> info.addStringPermission(permission.getName()));
        return info;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        //1、获取登录信息
        String username = authenticationToken.getPrincipal().toString();
        String passwd = String.copyValueOf((char[]) authenticationToken.getCredentials());
        //2、验证用户
        ResultBase<User> userResultBase = userService.byUserName(username);
        User user = userResultBase.getData();
        if (null == user )
            throw new AccountException("用户不存在");
        //3、将数据封装返回
        HashMap<String,Object> principal = new HashMap<>();
        principal.put("user",user);
        ResultBase<ArrayList<Role>> roleResuleBase = roleService.byUserId(user.getId());//读取数据库
        ArrayList<Role> roles = roleResuleBase.getData();
        principal.put("roles",roles);
        ResultBase<ArrayList<Permission>> permissionResuleBase = permissionService.byUserId(user.getId());//读取数据库
        ArrayList<Permission> permissions = permissionResuleBase.getData();
        principal.put("permissions",permissions);
        //4、存储封装数据并进行校验
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                principal,
                user.getPasswd(),
                username
        );
        return info;
    }
}
