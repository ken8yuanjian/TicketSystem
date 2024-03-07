package com.ken.user.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.profiles.active:}")    //获取环境信息 dev/prod/test 等信息，保留默认值避免无值异常
    private String env;

    @Bean
    public Docket docket(Environment environment){
        //配置api信息
        ApiInfo apiInfo = new ApiInfo("Ticketsystem Api Documentation",
                "Ticketsystem Api Documentation",
                "1.0",
                "urn:tos",
                new Contact("ken","xxx","ken_yuanjian@126.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .enable( env.equals("") || env.equals("dev") ) //enable是否启动swagger，false表示不能在浏览器中访问
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ken.user.controller"))
                //.paths(PathSelectors.ant("/user/*"))
                .build();
    }

}
