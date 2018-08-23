package com.higuera.houstat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.higuera.houstat.model.TaskExecution;
import com.higuera.houstat.model.TaskExecution.Status;
import com.higuera.houstat.model.type.MethodType;

public interface TaskExecutionRepository extends CrudRepository<TaskExecution, Long> {

	List<TaskExecution> findByNameContaining(String name);
	
	List<TaskExecution> findByMethodAndStatus(MethodType method, Status status);
	
}
