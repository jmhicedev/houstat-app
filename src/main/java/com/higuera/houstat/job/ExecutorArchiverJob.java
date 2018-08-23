package com.higuera.houstat.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
				GenericFilter filter = new GenericFilter();
				Properties props = task.getProperties();
				filter.setLocationId(LocationIdType.valueOf(props.getProperty("LocationId")));
				filter.setPropertyType(PropertyTypeType.valueOf(props.getProperty("PropertyType")));
				filter.setOperation(OperationType.valueOf(props.getProperty("Operation")));
				filter.setSinceDate(SinceDateType.valueOf(props.getProperty("SinceDate")));
				filter.setMaxItems(50);
				SearchResponseDTO searchResponse = null;
				do {
					searchResponse = idealistaClient.search(filter);
					numRequests++;
					for(ElementList element: searchResponse.getElementList()) {
						RealEstate realEstate = new RealEstate(element);
						realEstateService.insertOrUpdate(realEstate);
						counter++;
					}
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
			
		}
		
	}

}
