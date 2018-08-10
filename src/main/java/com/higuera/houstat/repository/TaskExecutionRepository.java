package com.higuera.houstat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.higuera.houstat.model.TaskExecution;

//@Repository
public interface TaskExecutionRepository extends CrudRepository<TaskExecution, Long> {

	TaskExecution findByName(String name);
}
