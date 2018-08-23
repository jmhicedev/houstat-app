package com.higuera.houstat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.higuera.houstat.service.impl.InitServiceImpl;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext*.xml" })
public class Application {
	
	@Autowired
    private DataSource dataSource;

	public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        //Load schedule samples
        InitServiceImpl initService = ctx.getBean(InitServiceImpl.class);
//        initService.createSchedules();
//        initService.createParameters();
    }

}

