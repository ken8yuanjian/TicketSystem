package com.ken.ticket.shiro;

import com.ken.common.cache.MyCache;
import com.ken.common.cache.MyCachePrefix;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.Subject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Calendar;

public class MySessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    MyCache myCache;

    //读入session时  通过共享session缓存实现跨服务认证
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session session = this.getCachedSession(sessionId);
        //System.out.println(Calendar.getInstance().getTime()+ "readSession getCachedSession :"+sessionId);
        if (null != session) {
            if (session instanceof SimpleSession){
                try {
                    ((SimpleSession)session).validate();    //如果本地缓存失效，给以一次redis缓存补读机会
                    return session;
                }catch (InvalidSessionException e){
                }
            }
        }
        //long millis = System.currentTimeMillis();
        session = myCache.getForValue(sessionId);   //从redis缓存中读取session
        //millis = System.currentTimeMillis() - millis;
        //System.out.println(Calendar.getInstance().getTime()+"readSession myCache :"+sessionId+" spend:"+String.valueOf(millis) +"ms");
        if (null != session) {
            this.cache(session, sessionId); //立即缓存到内存，防止反复从redis读取session
            //System.out.println(Calendar.getInstance().getTime()+"cache session :"+sessionId);
            return session;
        }
        //System.out.println(Calendar.getInstance().getTime()+"readSession super :" + sessionId);
        return super.readSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        super.update(session);
        if (! (session instanceof SimpleSession))
            return;
        Boolean authen = (Boolean) session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
        if (null != authen && authen.booleanValue()) {   //认证的session 更新到redis共享
            String key = MyCachePrefix.sessionid(session.getId().toString());
            myCache.setForValue(key,session);
            myCache.expire(key, session.getTimeout()/1000 );
        }
    }


}
