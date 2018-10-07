package com.higuera.houstat.app.web;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higuera.apiidealista.IdealistaClient;
import com.higuera.houstat.model.TaskExecution;
import com.higuera.houstat.model.TaskExecution.ExecutionType;
import com.higuera.houstat.model.TaskExecution.Status;
import com.higuera.houstat.model.type.MethodType;
import com.higuera.houstat.repository.TaskExecutionRepository;

@RestController
public class HelloController {
	
	@Autowired(required=true)
	TaskExecutionRepository taskExecutionRepository;
	
	@Autowired(required=true)
	IdealistaClient idealistaClient;

    @RequestMapping("/hello")
    public String sayHello(){
        return ("Hello World!");
    }
    
    @RequestMapping("/bye")
    public String sayBye(){
        return ("Bye!!!");
    }
    
    @RequestMapping("/createtask")
    public String createTask() {
    	TaskExecution task = new TaskExecution();
    	
    	task.setMethod(MethodType.ARCHIVER);
    	task.setName("Test Sanse Rent");
    	task.setCreationDate(new Date());
    	task.setType(ExecutionType.MANUAL);
    	task.setStatus(Status.READY);
    	task.setParameters("{\"LocationId\":\"MADRID_NORTE_SANSE\",\"Operation\":\"RENT\",\"PropertyType\":\"HOMES\",\"SinceDate\":\"LAST_DAY\"}");
    	taskExecutionRepository.save(task);
    	
    	return "task created";
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