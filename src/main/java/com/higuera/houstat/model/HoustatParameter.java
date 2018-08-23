package com.higuera.houstat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HoustatParameter {

	private @Id @GeneratedValue long id;
	
	private String name;
	
	private String value;
	
	public enum ParameterType { INTEGER, DOUBLE, STRING, DATE, TIMESTAMP };
	private ParameterType type;
	
	private String parameterClass;
	
	
	/* Getters and Setters */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ParameterType getType() {
		return type;
	}
	public void setType(ParameterType type) {
		this.type = type;
	}
	public String getParameterClass() {
		return parameterClass;
	}
	public void setParameterClass(String parameterClass) {
		this.parameterClass = parameterClass;
	}
	
}
