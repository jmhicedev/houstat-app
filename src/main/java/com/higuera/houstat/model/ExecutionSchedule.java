package com.higuera.houstat.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.higuera.houstat.model.type.MethodType;

/**
 * It defines the generation of execution's tasks
 */
public class ExecutionSchedule {
	
	private @Id @GeneratedValue Long id;
	
	private String name;
	
	private MethodType method;
	
	private String properties;
	
	private String cron;
	
	private Date lastExecution;
	
	private Date nextExecution;
	
	public enum ScheduleStatus { DISABLE, ENABLE };
	private ScheduleStatus scheduleStatus;
	
	
	
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
	public MethodType getMethod() {
		return method;
	}
	public void setMethod(MethodType method) {
		this.method = method;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public Date getLastExecution() {
		return lastExecution;
	}
	public void setLastExecution(Date lastExecution) {
		this.lastExecution = lastExecution;
	}
	public ScheduleStatus getScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(ScheduleStatus scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	public Date getNextExecution() {
		return nextExecution;
	}
	public void setNextExecution(Date nextExecution) {
		this.nextExecution = nextExecution;
	}
	
}
