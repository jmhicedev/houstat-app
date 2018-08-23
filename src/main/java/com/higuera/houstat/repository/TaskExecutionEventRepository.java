package com.higuera.houstat.repository;

import org.springframework.data.repository.CrudRepository;

import com.higuera.houstat.model.TaskExecutionEvent;

public interface TaskExecutionEventRepository extends CrudRepository<TaskExecutionEvent, Long> {

}
