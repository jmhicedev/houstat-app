package com.higuera.houstatapp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("hola")
    public String sayHello(){
        return ("Hello, SpringBoot on Wildfly");
    }
}
