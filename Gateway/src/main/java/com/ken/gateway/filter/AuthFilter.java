package com.ken.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/*
使用全局过滤器实现认证 与  shiro框架的差别
该方式针对客户端访问服务经过网关路由映射时，会调用全局过滤器进行认证有效，但
对于操作服务器人员直接访问服务的原始url，无法进入全局过滤器，也无法进行认证，所以
认证无效，本质原因是服务本身不含认证

而shiro没有这样一些问题
**/
@Component
public class AuthFilter implements GlobalFilter, Ordered{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*System.out.println("执行了认证过滤器......");
        String token = exchange.getRequest().getQueryParams().getFirst("access-token");
        if (token == null || token.length()<=0 ){//简单认证一下
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);  //继续执行下一个过滤器
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
