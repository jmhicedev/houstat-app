package com.higuera.houstat.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String sayHello(){
        return ("Hello, SpringBoot on Wildfly");
    }
    
    @RequestMapping("/bye")
    public String sayBye(){
        return ("Bye!!!");
    }
    
}