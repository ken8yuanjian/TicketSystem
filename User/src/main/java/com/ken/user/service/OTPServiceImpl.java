package com.ken.user.service;

import com.ken.common.entity.http.ResultBase;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.OutputStream;
import java.util.HashMap;

@Service

public class OTPServiceImpl implements OTPService{
    @Override
    public ResultBase<String > generator(String type, OutputStream out) {
        Captcha captcha = null;
        int width = 130, height =48;
        int lenght = 5;
        switch (type) {
            case OTPT_PNG:
                captcha = new SpecCaptcha(width, height);
                break;
            case OTPT_GIF:
                captcha = new GifCaptcha(width, height);
                break;
            case OTPT_CN:
                captcha = new ChineseCaptcha(width, height, lenght, new Font("楷体", Font.PLAIN, 28));
                break;
            case OTPT_CNFIG:
                captcha = new ChineseGifCaptcha(width, height, lenght, new Font("楷体", Font.PLAIN, 28));
                break;
            case OTPT_ARITHMETI:
                ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(width, 48);
                //arithmeticCaptcha.setLen(3);  // 几位数运算，默认是两位
                //arithmeticCaptcha.getArithmeticString();  // 获取运算的公式：3+2=?
                captcha = arithmeticCaptcha;
                break;
            default:
                captcha = new SpecCaptcha(width, height);
                break;
        }
        // 设置类型，纯数字、纯字母、字母数字混合
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        // 输出图片流
        captcha.out(out);
        return ResultBase.success(captcha.text());
    }
}
