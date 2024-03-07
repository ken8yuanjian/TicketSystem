package com.ken.user.shiro;

import com.ken.common.cache.MyCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

public class MySessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    MyCache myCache;

    //删除session，登出时会发生
    @Override
    public void delete(Session session) {
        super.delete(session);
        myCache.delete(session.getId());
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        super.update(session);
        if (! (session instanceof SimpleSession))
            return;
        Boolean authen = (Boolean) session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
        if (null != authen && authen.booleanValue()) {   //认证的session 更新到redis共享
            myCache.setForValue(session.getId(),session);
            myCache.expire(session.getId(), session.getTimeout()/1000 );
        }
    }
}
