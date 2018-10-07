package com.higuera.houstat.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.higuera.apiidealista.IdealistaClient;
import com.higuera.apiidealista.dto.SearchResponseDTO;
import com.higuera.apiidealista.filters.GenericFilter;
import com.higuera.apiidealista.filters.model.LocationIdType;
import com.higuera.apiidealista.filters.model.OperationType;
import com.higuera.apiidealista.filters.model.PropertyTypeType;
import com.higuera.apiidealista.filters.model.SinceDateType;
import com.higuera.apiidealista.model.ElementList;
import com.higuera.houstat.model.RealEstate;
import com.higuera.houstat.model.TaskExecution;
import com.higuera.houstat.model.TaskExecution.Status;
import com.higuera.houstat.model.TaskExecutionEvent;
import com.higuera.houstat.model.TaskExecutionEvent.EventType;
import com.higuera.houstat.model.type.MethodType;
import com.higuera.houstat.repository.TaskExecutionEventRepository;
import com.higuera.houstat.repository.TaskExecutionRepository;
import com.higuera.houstat.service.RealEstateService;

/**
 * It gets information from external servers (idealista) and saves data into database
 * */
@Component
public class ExecutorArchiverJob {
	
	private static final Logger logger = LoggerFactory.getLogger(ExecutorArchiverJob.class);
	
	@Autowired
	private TaskExecutionRepository taskExecutionRepository;
	
	@Autowired
	private TaskExecutionEventRepository eventRepository;
	
	@Autowired
	private RealEstateService realEstateService;
	
	@Autowired
	private IdealistaClient idealistaClient;
	
	public void execute() {

		List<TaskExecution> tasks = taskExecutionRepository.findByMethodAndStatus(MethodType.ARCHIVER, Status.READY);
		
		for(TaskExecution task: tasks) {
			task.setStartDate(new Date());
			task.setStatus(Status.RUNNING);
			taskExecutionRepository.save(task);
			long counter = 0;
			long numRequests = 0;
			try {
				Properties props = task.getProperties();
				GenericFilter filter = new GenericFilter(props);
				filter.setMaxItems(50);
				filter.setNumPage(1);
				SearchResponseDTO searchResponse = null;
				do {
					searchResponse = idealistaClient.search(filter);
					numRequests++;
					for(ElementList element: searchResponse.getElementList()) {
						RealEstate realEstate = new RealEstate(element);
						realEstateService.insertOrUpdate(realEstate);
						counter++;
					}
					filter.setNumPage(filter.getNumPage()+1);
				} while(searchResponse != null && searchResponse.getActualPage() < searchResponse.getTotalPages());
				
				task.setEndDate(new Date());
				task.setStatus(Status.FINISHED);
				taskExecutionRepository.save(task);
			} catch (Exception e) {
				//TODO: use logger
				e.printStackTrace();
				task.setStatus(Status.ERROR);
				taskExecutionRepository.save(task);
			} 
			
			//Summary task
			TaskExecutionEvent event = new TaskExecutionEvent();
			event.setType(EventType.NUM_ITEMS_ACHIEVED);
			event.setValue(BigDecimal.valueOf(counter));
			event.setTask(task);
			eventRepository.save(event);
			
			TaskExecutionEvent eventRequests = new TaskExecutionEvent();
			eventRequests.setType(EventType.NUM_REQUESTS);
			eventRequests.setValue(BigDecimal.valueOf(numRequests));
			eventRequests.setTask(task);
			eventRepository.save(eventRequests);
			
			logger.info("REAL STATES ARCHIVED: " + counter);
			logger.info("NUMBER OF REQUESTS: " + numRequests);
		}
		
	}

}
