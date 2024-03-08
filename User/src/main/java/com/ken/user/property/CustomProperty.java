package com.ken.user.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/*
自定义配置类示例
 */
@Component
@PropertySource(value = {"classpath:custom1.properties"} )
@ConfigurationProperties(prefix = "custom")  //使用该注解一定要为属性生成 get、set方法
@Data
public class CustomProperty {
    public HashMap<String,String> v1;
    //@Value("${custom.v3}")
    public String v3;
    public Integer v4;
    public String v5;
}
