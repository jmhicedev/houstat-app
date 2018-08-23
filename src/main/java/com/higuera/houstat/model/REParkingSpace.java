package com.higuera.houstat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.higuera.apiidealista.model.DetailedType;
import com.higuera.apiidealista.model.ParkingSpace;

@Entity
public class REParkingSpace {

	private @Id @GeneratedValue long id;
	
	@OneToOne(mappedBy="parkingSpace")
	private RealEstate realEstate;
	
	private Boolean hasParkingSpace;
	
	private Boolean isParkingSpaceIncludedInPrice;  
	
	private Double parkingSpacePrice;
	
	public REParkingSpace() {}
	
	public REParkingSpace(ParkingSpace parkingSpace) {
		this.hasParkingSpace = parkingSpace.getHasParkingSpace();
		this.isParkingSpaceIncludedInPrice = parkingSpace.getIsParkingSpaceIncludedInPrice();
		this.parkingSpacePrice = parkingSpace.getParkingSpacePrice();
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
	public Boolean getHasParkingSpace() {
		return hasParkingSpace;
	}
	public void setHasParkingSpace(Boolean hasParkingSpace) {
		this.hasParkingSpace = hasParkingSpace;
	}
	public Boolean getIsParkingSpaceIncludedInPrice() {
		return isParkingSpaceIncludedInPrice;
	}
	public void setIsParkingSpaceIncludedInPrice(Boolean isParkingSpaceIncludedInPrice) {
		this.isParkingSpaceIncludedInPrice = isParkingSpaceIncludedInPrice;
	}
	public Double getParkingSpacePrice() {
		return parkingSpacePrice;
	}
	public void setParkingSpacePrice(Double parkingSpacePrice) {
		this.parkingSpacePrice = parkingSpacePrice;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		REParkingSpace other = (REParkingSpace) obj;
		if(hasParkingSpace==null) {
			if(other.hasParkingSpace !=null)
				return false;
		} else if(!hasParkingSpace.equals(other.hasParkingSpace)) {
			return false;
		}
		if(isParkingSpaceIncludedInPrice == null) {
			if(other.isParkingSpaceIncludedInPrice != null)
				return false;
		} else if(!isParkingSpaceIncludedInPrice.equals(other.isParkingSpaceIncludedInPrice)) {
			return false;
		}
		if(parkingSpacePrice == null) {
			if(other.parkingSpacePrice != null)
				return false;
		} else if(!parkingSpacePrice.equals(other.parkingSpacePrice)) {
			return false;
		}
		
		return true;
	}
}
