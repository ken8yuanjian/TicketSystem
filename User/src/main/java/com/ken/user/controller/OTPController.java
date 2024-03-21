package com.ken.user.controller;
/*
验证码Controller
 */

import com.ken.common.cache.MyCache;
import com.ken.common.cache.MyCachePrefix;
import com.ken.common.entity.http.ResultBase;
import com.ken.user.service.OTPService;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/otp")
public class OTPController {

    @Autowired
    OTPService otpService;
    @Autowired
    MyCache cache;

    @ApiOperation("验证码获取接口")
    @GetMapping("/{id}")
    @RequiresGuest
    public void get(HttpServletResponse resp,@PathVariable("id") String id , @ApiParam("验证码类型") String type)
    {
        // 设置请求头为输出图片类型
        resp.setContentType("image/gif");
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        try {
            ResultBase<String> resultBase = otpService.generator(type, resp.getOutputStream());
            String key = MyCachePrefix.optid(id);
            cache.setForValue(key, resultBase.getData() );
            cache.expire(key,5* 60);
        }catch (IOException e){

        }
    }
    @ApiOperation("验证码验证接口")
    @GetMapping("/{id}/check")
    @RequiresGuest
    public ResultBase<String> put( @PathVariable("id") String id ,@ApiParam("验证码text") String text)
    {
        String key = MyCachePrefix.optid(id);
        String str = cache.getForValue(key );
        if (null == str || null == text || !text.equalsIgnoreCase(str) )
            return ResultBase.fail(1,"无效验证码");
        return ResultBase.success(0,"有效验证码");
    }

}
