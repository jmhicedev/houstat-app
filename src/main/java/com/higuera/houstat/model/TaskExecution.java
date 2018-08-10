package com.higuera.houstat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * It represents the execution of a task
 * */
@Entity
@Table(name="TASK_EXECUTION")
public class TaskExecution {

	private @Id @GeneratedValue Long id;
	
	private String name;
	
	public enum ExecutionType { MANUAL, AUTOMATIC };
	private ExecutionType type;
	
	private String method;
	
	private String properties;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	public enum Status { BUILDING, READY, RUNNING, FINISHED, ERROR };
	private Status status;
	
	
	public TaskExecution(){ }
	
	
	
	/* Getters and Setters */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ExecutionType getType() {
		return type;
	}
	public void setType(ExecutionType type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
