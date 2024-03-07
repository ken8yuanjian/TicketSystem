package com.ken.gateway.filter;

import com.ken.common.cache.MyCache;
import com.ken.common.entity.http.ResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
重放攻击过滤器
短时间内连续使用合法URL进行攻击
实现：
1、前端每次请求都需遵守重新生成带唯一UUID的URL；
2、后端服务短时间内禁止执行相同UUID的URL；
3、后端对于防重放攻击的功能代码放在Gateway部分，每个访问都需要经过该服务
*/
@Component
public class ReplayAttackFilter implements GlobalFilter , Ordered {

    @Autowired
    MyCache myCache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ResultBase.fail(0,"");
        ServerHttpResponse resp = exchange.getResponse();
        String url = exchange.getRequest().getURI().toString();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte [] hash = digest.digest(url.getBytes());
            StringBuilder stringBuilder=new StringBuilder();
            for (byte b:hash) {
                stringBuilder.append(String.format("%02x",b));
            }
            String hashString = stringBuilder.toString();
            String value = myCache.getForValue(hashString);
            if (null != value){//认定重放攻击
                System.out.println("检测到重放攻击...");
                resp.setStatusCode(HttpStatus.FORBIDDEN);
                return resp.setComplete();
            }else{
                myCache.setForValue(hashString,"exist"); //缓存URL
                myCache.expire(hashString,10); //10秒之内访问为重放攻击
            }
        }catch (NoSuchAlgorithmException e){

        }
        return chain.filter(exchange);  //继续执行下一个过滤器
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
