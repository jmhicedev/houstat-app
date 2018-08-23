package com.higuera.houstat.model;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higuera.houstat.model.type.MethodType;


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
	
	private MethodType method;
	
	private String parameters;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	private Date startDate;
	private Date endDate;
	
	public enum Status { BUILDING, READY, RUNNING, FINISHED, ERROR };
	private Status status;
	
	@OneToMany(
        mappedBy = "task", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private Set<TaskExecutionEvent> events = new HashSet<TaskExecutionEvent>();
	
	
	public TaskExecution(){ }
	

	public Properties getProperties() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(this.getParameters(), Properties.class);
	}


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
	public MethodType getMethod() {
		return method;
	}
	public void setMethod(MethodType method) {
		this.method = method;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Set<TaskExecutionEvent> getEvents() {
		return events;
	}
	public void setEvents(Set<TaskExecutionEvent> events) {
		this.events = events;
	}
	
	
	public void addEvent(TaskExecutionEvent event) {
        events.add(event);
        event.setTask(this);
    }
    public void removeEvent(TaskExecutionEvent event) {
        events.remove(event);
        event.setTask(null);
    }

}
