package com.ken.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/gateway")
    public String gateway(){
        return "Hello Gateway! 1.0.0";
    }
}
