package com.ken.ticket.shiro;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSessionManager sessionManager(MySessionDao mySessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO (mySessionDao);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    /**
     * shiro sessionDAO
     */
    @Bean
    public MySessionDao mySessionDao() {
        MySessionDao mySessionDao = new MySessionDao();
        return mySessionDao;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(DefaultWebSessionManager defaultWebSessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }


}
