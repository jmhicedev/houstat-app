package com.higuera.houstat.model.type;

public enum MethodType {
	
	ARCHIVER("ARCHIVER");
	
	private String value;
	
	MethodType(String value){
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
