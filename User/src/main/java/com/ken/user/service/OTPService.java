package com.ken.user.service;

import com.ken.common.entity.http.ResultBase;
import net.sf.jsqlparser.expression.operators.relational.OldOracleJoinBinaryExpression;

import java.io.OutputStream;
import java.util.HashMap;

public interface OTPService {
    public static String OTPT_PNG = "png";  //png
    public static String OTPT_GIF = "gif";  //gif
    public static String OTPT_CN = "cn";    //中文png
    public static String OTPT_CNFIG = "cngif";  //中文gif
    public static String OTPT_ARITHMETI = "art";  //算数表达式

    public ResultBase<String> generator(String type, OutputStream out);
}
