package com.higuera.houstat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.higuera.apiidealista.model.DetailedType;

@Entity
public class REDetailedType {

	private @Id @GeneratedValue long id;
	
	@OneToOne(mappedBy="detailedType")
	private RealEstate realEstate;
	
	private String typology;
	
	private String subtypology;

	
	public REDetailedType() {}
	
	public REDetailedType(DetailedType detailedType) {
		this.typology = detailedType.getTypology();
		this.subtypology = detailedType.getSubtypology();
	}
	
	/* Getters and Setters */
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public RealEstate getRealEstate() {
		return realEstate;
	}
	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}
	public String getTypology() {
		return typology;
	}
	public void setTypology(String typology) {
		this.typology = typology;
	}
	public String getSubtypology() {
		return subtypology;
	}
	public void setSubtypology(String subtypology) {
		this.subtypology = subtypology;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		REDetailedType other = (REDetailedType) obj;
		if(typology==null) {
			if(other.getTypology() !=null)
				return false;
		} else if(!typology.equals(other.getTypology())) {
			return false;
		}
		if(subtypology == null) {
			if(other.getSubtypology() != null)
				return false;
		} else if(!subtypology.equals(other.getSubtypology())) {
			return false;
		}
		
		return true;
	}
	
}
