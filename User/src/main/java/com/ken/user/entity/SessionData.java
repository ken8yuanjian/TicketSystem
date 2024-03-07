package com.ken.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SessionData {
    @ApiModelProperty("session的key")
    public String key;
    @ApiModelProperty("session的生成时间")
    public String time;
    @ApiModelProperty("session的描述")
    public String descr;
}
