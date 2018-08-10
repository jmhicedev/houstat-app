package com.higuera.houstat.repository;

import org.springframework.data.repository.CrudRepository;

import com.higuera.houstat.model.TaskExecution;

public interface TaskExecutionRepository extends CrudRepository<TaskExecution, Long> {

	TaskExecution findByName(String name);
}
