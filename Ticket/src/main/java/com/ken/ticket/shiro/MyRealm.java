package com.ken.ticket.shiro;

import com.ken.common.entity.Permission;
import com.ken.common.entity.Role;
import com.ken.common.entity.User;
import com.ken.common.entity.http.ResultBase;
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
import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;

/*
自定义实现shiro的认证和授权
 */
@Component
public class MyRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashMap<String,Object> principal = (HashMap<String,Object>)principalCollection.getPrimaryPrincipal();
        ArrayList<Role> roles = (ArrayList<Role>)principal.get("roles");
        if (null != roles)
            roles.forEach(r -> info.addRole( r.getName() ) );
        ArrayList<Permission> permissions = (ArrayList<Permission>)principal.get("permissions");
        if (null !=permissions)
            permissions.forEach( p -> info.addStringPermission( p.getName() ) );
        return info;
    }

    //有UserService进行身份认证，服务本身不再认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        return new SimpleAuthenticationInfo();
    }
}
