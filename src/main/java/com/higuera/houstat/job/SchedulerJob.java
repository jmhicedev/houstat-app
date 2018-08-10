package com.higuera.houstat.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.higuera.houstat.model.TaskExecution;
import com.higuera.houstat.repository.TaskExecutionRepository;

/**
 * It generates task execution from the schedule to execute later in another job
 * */
@Component
public class SchedulerJob implements Job {

	@Autowired(required=true)
	private TaskExecutionRepository taskExecutionRepository;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		//TODO: code job
		System.out.println("--> SchedulerJob is running!!!");
		TaskExecution taskExecution = new TaskExecution();
		taskExecution.setName("prueba" + new Date().getTime());
		
		try {
			taskExecutionRepository.save(taskExecution);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
