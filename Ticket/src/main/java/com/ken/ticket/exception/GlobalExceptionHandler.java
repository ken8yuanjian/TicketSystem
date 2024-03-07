package com.ken.ticket.exception;

import com.ken.common.entity.http.ResultBase;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/*
全局异常捕获处理，优先匹配子级异常类，再匹配顶层异常类
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResultBase Exception(Exception exception)
    {
        return ResultBase.fail(1,exception.getMessage());
    }
    @ExceptionHandler({MyBatisSystemException.class})
    public ResultBase MyBatisSystemException(MyBatisSystemException exception)
    {
        return ResultBase.fail(1,"数据库连接异常!");
    }
    @ExceptionHandler({RedisConnectionFailureException.class})
    public ResultBase RedisConnectionFailureException(RedisConnectionFailureException exception)
    {
        return ResultBase.fail(1,"缓存系统连接异常!");
    }

}
