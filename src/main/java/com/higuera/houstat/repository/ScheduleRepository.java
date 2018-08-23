package com.higuera.houstat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.higuera.houstat.model.Schedule;
import com.higuera.houstat.model.Schedule.ScheduleStatus;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

	List<Schedule> findByNameContaining(String name);
	
	List<Schedule> findByNextExecutionBeforeAndStatus(Date nextExecution, ScheduleStatus status);
}
