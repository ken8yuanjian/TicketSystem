package com.ken.common.entity.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
返回结果基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBase<T> {
    private Integer code; //返回结果码
    private String  message; //返回消息提示
    private T data; //返回数据

    static public <T> ResultBase success(T data)
    {
        return success(data,"");
    }
    static public <T> ResultBase success(T data,String message)
    {
        ResultBase r = new ResultBase();
        r.setCode(0);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
    static public ResultBase  fail(Integer code,String message )
    {
        ResultBase r = new ResultBase();
        r.setCode(code);
        r.setMessage(message);
        r.setData(null);
        return r;
    }

}
