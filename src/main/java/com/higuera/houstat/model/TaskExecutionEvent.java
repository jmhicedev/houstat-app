package com.higuera.houstat.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * It Represents the results and events got in the execution of a task
 * */
@Entity
public class TaskExecutionEvent {

	private @Id @GeneratedValue long id;
	
	public enum EventType { ERROR, NUM_ITEMS_ACHIEVED, NUM_ITEMS_UPDATED, NUM_ITEMS_NEW, NUM_REQUESTS }
	private EventType type;
	
	private BigDecimal value;
	
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
	private TaskExecution task;
	
	
	/* Getters and Setters */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TaskExecution getTask() {
		return task;
	}
	public void setTask(TaskExecution task) {
		this.task = task;
	}
	
}
