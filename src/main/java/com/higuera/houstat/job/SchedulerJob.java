package com.higuera.houstat.job;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.higuera.houstat.model.Schedule;
import com.higuera.houstat.model.Schedule.ScheduleStatus;
import com.higuera.houstat.model.TaskExecution;
import com.higuera.houstat.model.TaskExecution.ExecutionType;
import com.higuera.houstat.model.type.MethodType;
import com.higuera.houstat.repository.ScheduleRepository;
import com.higuera.houstat.repository.TaskExecutionRepository;
import com.higuera.houstat.util.WorkerUtil;

/**
 * It generates task execution from the schedule to execute later in another job
 * */
@Component
public class SchedulerJob {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerJob.class);

	@Autowired(required=true)
	private TaskExecutionRepository taskExecutionRepository;
	
	@Autowired(required=true)
	private ScheduleRepository scheduleRepository;
	
	public void execute() {

		List<Schedule> schedules = 
				scheduleRepository.findByNextExecutionBeforeAndStatus(new Date(), ScheduleStatus.ENABLE);
		
		List<TaskExecution> tasks = new ArrayList<TaskExecution>();
		for(Schedule s: schedules) {
			//Creating execution tasks
			TaskExecution task = new TaskExecution();
			task.setName(s.getName());
			task.setMethod(MethodType.ARCHIVER);
			task.setCreationDate(new Date());
			task.setStatus(TaskExecution.Status.READY);
			task.setParameters(s.getParameters());
			task.setType(ExecutionType.AUTOMATIC);
			tasks.add(task);
			
			//Updating next execution from cron string
			try {
				s.setLastExecution(new Date());
				s.setNextExecution(WorkerUtil.calculateNextExecution(s.getCron(), s.getLastExecution()));
			} catch (ParseException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				scheduleRepository.save(s);
			}
		}
		taskExecutionRepository.saveAll(tasks);
		
	}

}
