package com.higuera.houstat.app.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higuera.apiidealista.IdealistaClient;
import com.higuera.houstat.model.TaskExecution;
import com.higuera.houstat.repository.TaskExecutionRepository;

@RestController
public class HelloController {
	
	@Autowired(required=true)
	TaskExecutionRepository taskExecutionRepository;
	
	@Autowired(required=true)
	IdealistaClient idealistaClient;

    @RequestMapping("/hello")
    public String sayHello(){
    	
    	TaskExecution te = new TaskExecution();
    	te.setName("holaaa");
    	try {
    		taskExecutionRepository.save(te);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return ("Hello World!");
    }
    
    @RequestMapping("/bye")
    public String sayBye(){
        return ("Bye!!!");
    }
    
    @RequestMapping("/test")
    public String test(){
    	try {
			idealistaClient.authenticate();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return "Test page";
    }
    
}