package com.higuera.houstat.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higuera.apiidealista.filters.model.LocationIdType;
import com.higuera.apiidealista.filters.model.OperationType;
import com.higuera.apiidealista.filters.model.PropertyTypeType;
import com.higuera.apiidealista.filters.model.SinceDateType;
import com.higuera.houstat.constant.Parameters;
import com.higuera.houstat.model.HoustatParameter;
import com.higuera.houstat.model.Schedule;
import com.higuera.houstat.model.HoustatParameter.ParameterType;
import com.higuera.houstat.model.type.MethodType;
import com.higuera.houstat.repository.HoustatParameterRepository;
import com.higuera.houstat.repository.ScheduleRepository;
import com.higuera.houstat.service.InitService;


/**
 * This service creates sample data for testing purpose
 * */
@Service
public class InitServiceImpl implements InitService {
	
	@Autowired(required = true)
	private ScheduleRepository scheduleRepository;
	
	@Autowired(required = true)
	private HoustatParameterRepository parameterRepository;

	/** Schedules samples */
	public void createSchedules() {
		//Save a schedule for testing
        Schedule schedule = new Schedule();
        schedule.setName("Sanse");
        schedule.setMethod(MethodType.ARCHIVER);
        schedule.setLastExecution(null);
        schedule.setNextExecution(new Date());
//        schedule.setCron("0 0/2 * 1/1 * ? *");
        schedule.setCron("0 0 0 1/1 * ? *");
        schedule.setStatus(Schedule.ScheduleStatus.ENABLE);
        
        Properties props = new Properties();
        props.put("LocationId", LocationIdType.MADRID_NORTE_SANSE.toString());
        props.put("PropertyType", PropertyTypeType.HOMES.toString());
        props.put("Operation", OperationType.RENT.toString());
        props.put("SinceDate", SinceDateType.LAST_DAY.toString());
        
        ObjectMapper mapper = new ObjectMapper();
        try {
			String propsJson = mapper.writeValueAsString(props);
			schedule.setParameters(propsJson);
			//Properties propsread = mapper.readValue(json, Properties.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.scheduleRepository.save(schedule);
	}

	/* Create parameters */
	public void createParameters() {
		HoustatParameter parameter = new HoustatParameter();
		parameter.setName(Parameters.RETRIEVE_PICTURE_LAST_TIME);
		parameter.setValue("0");
		parameter.setType(ParameterType.TIMESTAMP);
		parameter.setParameterClass(Long.class.getCanonicalName());
		parameterRepository.save(parameter);
		
	}
}
