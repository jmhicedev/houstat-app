package com.higuera.houstat.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.util.FileCopyUtils;

import com.higuera.apiidealista.model.ElementList;

@Entity
public class RealEstate {

	private @Id @GeneratedValue Long id;
	
	//Identifier of the real state
	private Integer propertyCode;

	private Date creationDate;
	private Long lastUpdateMs;
	private Long vers = 0L;
	private boolean lastVers = true;
	
	private String address;
	private Integer bathrooms;
	private String country;
	private String distance;
	private String district;
	private Boolean exterior;
	private String floor;
	private Boolean hasVideo;
	private Double latitude;
	private Double longitude;
	private String municipality;
	private String neighborhood;
	private Integer numPhotos;
	private String operation;
	private Integer price;
	private String province;
	private String region;
	private Integer rooms;
	private Boolean showAddress;
	private Integer size;
	private String subregion;
	private String thumbnail;
	private String url;
	private String status;
	private Boolean newDevelopment;
	private String tenantGender;
	private String garageType;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private REParkingSpace parkingSpace;
	
	private Boolean hasLift;
	private Boolean newDevelopmentFinished;
	private Boolean isSmookingAllowed;
	private Double priceByArea;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private REDetailedType detailedType;
	
	private String externalReference;
	
	@Lob
	private byte[] picture;
	
	@PrePersist
	protected void onCreate() {
		this.creationDate = new Date();
		this.lastUpdateMs = this.creationDate.getTime();
	}
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdateMs = new Date().getTime();
	}
	
	public RealEstate() {}
	
	//Constructor from ElementList object
	public RealEstate(ElementList element) {
		this.propertyCode = element.getPropertyCode();
		this.address = element.getAddress();
		this.bathrooms = element.getBathrooms();
		this.country = element.getCountry();
		this.distance = element.getDistance();
		this.district = element.getDistrict();
		this.exterior = element.getExterior();
		this.floor = element.getFloor();
		this.hasVideo = element.getHasVideo();
		this.latitude = element.getLatitude();
		this.longitude = element.getLongitude();
		this.municipality = element.getMunicipality();
		this.neighborhood = element.getNeighborhood();
		this.numPhotos = element.getNumPhotos();
		this.operation = element.getOperation();
		this.price = element.getPrice();
		this.province = element.getProvince();
		this.region = element.getRegion();
		this.rooms = element.getRooms();
		this.showAddress = element.getShowAddress();
		this.size = element.getSize();
		this.subregion = element.getSubregion();
		this.thumbnail = element.getThumbnail();
		this.url = element.getUrl();
		this.status = element.getStatus();
		this.newDevelopment = element.getNewDevelopment();
		this.tenantGender = element.getTenantGender();
		this.garageType = element.getGarageType();
		this.parkingSpace = element.getParkingSpace()!=null ? new REParkingSpace(element.getParkingSpace()) : null;
		this.hasLift = element.getHasLift();
		this.newDevelopmentFinished = element.getNewDevelopmentFinished();
		this.isSmookingAllowed = element.getIsSmookingAllowed();
		this.priceByArea = element.getPriceByArea();
		this.detailedType = element.getDetailedType()!=null ? new REDetailedType(element.getDetailedType()) : null;
		this.externalReference = element.getExternalReference();
		
		// Store thumbnail picture if it exists
		if(this.thumbnail != null && !this.thumbnail.isEmpty()) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] chunk = new byte[4096];
				int bytesRead;
				URL url = new URL(this.thumbnail);
				InputStream inputStream = url.openStream();
				while ((bytesRead = inputStream.read(chunk)) > 0) {
					outputStream.write(chunk, 0, bytesRead);
				}
				this.picture = outputStream.toByteArray();
			} catch (MalformedURLException e) {
				//TODO: use logger
				e.printStackTrace();
			} catch (IOException e) {
				//TODO: use logger
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RealEstate other = (RealEstate) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bathrooms == null) {
			if (other.bathrooms != null)
				return false;
		} else if (!bathrooms.equals(other.bathrooms))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (detailedType == null) {
			if (other.detailedType != null)
				return false;
		} else if (!detailedType.equals(other.detailedType))
			return false;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (exterior == null) {
			if (other.exterior != null)
				return false;
		} else if (!exterior.equals(other.exterior))
			return false;
		if (externalReference == null) {
			if (other.externalReference != null)
				return false;
		} else if (!externalReference.equals(other.externalReference))
			return false;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (garageType == null) {
			if (other.garageType != null)
				return false;
		} else if (!garageType.equals(other.garageType))
			return false;
		if (hasLift == null) {
			if (other.hasLift != null)
				return false;
		} else if (!hasLift.equals(other.hasLift))
			return false;
		if (hasVideo == null) {
			if (other.hasVideo != null)
				return false;
		} else if (!hasVideo.equals(other.hasVideo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isSmookingAllowed == null) {
			if (other.isSmookingAllowed != null)
				return false;
		} else if (!isSmookingAllowed.equals(other.isSmookingAllowed))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (municipality == null) {
			if (other.municipality != null)
				return false;
		} else if (!municipality.equals(other.municipality))
			return false;
		if (neighborhood == null) {
			if (other.neighborhood != null)
				return false;
		} else if (!neighborhood.equals(other.neighborhood))
			return false;
		if (newDevelopment == null) {
			if (other.newDevelopment != null)
				return false;
		} else if (!newDevelopment.equals(other.newDevelopment))
			return false;
		if (newDevelopmentFinished == null) {
			if (other.newDevelopmentFinished != null)
				return false;
		} else if (!newDevelopmentFinished.equals(other.newDevelopmentFinished))
			return false;
		if (numPhotos == null) {
			if (other.numPhotos != null)
				return false;
		} else if (!numPhotos.equals(other.numPhotos))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (parkingSpace == null) {
			if (other.parkingSpace != null)
				return false;
		} else if (!parkingSpace.equals(other.parkingSpace))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceByArea == null) {
			if (other.priceByArea != null)
				return false;
		} else if (!priceByArea.equals(other.priceByArea))
			return false;
		if (propertyCode == null) {
			if (other.propertyCode != null)
				return false;
		} else if (!propertyCode.equals(other.propertyCode))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		if (showAddress == null) {
			if (other.showAddress != null)
				return false;
		} else if (!showAddress.equals(other.showAddress))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (subregion == null) {
			if (other.subregion != null)
				return false;
		} else if (!subregion.equals(other.subregion))
			return false;
		if (tenantGender == null) {
			if (other.tenantGender != null)
				return false;
		} else if (!tenantGender.equals(other.tenantGender))
			return false;
		if (thumbnail == null) {
			if (other.thumbnail != null)
				return false;
		} else if (!thumbnail.equals(other.thumbnail))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	/* Getters and Setters */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(Integer propertyCode) {
		this.propertyCode = propertyCode;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getLastUpdateMs() {
		return lastUpdateMs;
	}
	public void setLastUpdateMs(Long lastUpdateMs) {
		this.lastUpdateMs = lastUpdateMs;
	}
	public Long getVers() {
		return vers;
	}
	public void setVers(Long vers) {
		this.vers = vers;
	}
	public boolean isLastVers() {
		return lastVers;
	}
	public void setLastVers(boolean lastVers) {
		this.lastVers = lastVers;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Boolean getExterior() {
		return exterior;
	}
	public void setExterior(Boolean exterior) {
		this.exterior = exterior;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public Boolean getHasVideo() {
		return hasVideo;
	}
	public void setHasVideo(Boolean hasVideo) {
		this.hasVideo = hasVideo;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public Integer getNumPhotos() {
		return numPhotos;
	}
	public void setNumPhotos(Integer numPhotos) {
		this.numPhotos = numPhotos;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getRooms() {
		return rooms;
	}
	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}
	public Boolean getShowAddress() {
		return showAddress;
	}
	public void setShowAddress(Boolean showAddress) {
		this.showAddress = showAddress;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getSubregion() {
		return subregion;
	}
	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getNewDevelopment() {
		return newDevelopment;
	}
	public void setNewDevelopment(Boolean newDevelopment) {
		this.newDevelopment = newDevelopment;
	}
	public String getTenantGender() {
		return tenantGender;
	}
	public void setTenantGender(String tenantGender) {
		this.tenantGender = tenantGender;
	}
	public String getGarageType() {
		return garageType;
	}
	public void setGarageType(String garageType) {
		this.garageType = garageType;
	}
	public REParkingSpace getParkingSpace() {
		return parkingSpace;
	}
	public void setParkingSpace(REParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	public Boolean getHasLift() {
		return hasLift;
	}
	public void setHasLift(Boolean hasLift) {
		this.hasLift = hasLift;
	}
	public Boolean getNewDevelopmentFinished() {
		return newDevelopmentFinished;
	}
	public void setNewDevelopmentFinished(Boolean newDevelopmentFinished) {
		this.newDevelopmentFinished = newDevelopmentFinished;
	}
	public Boolean getIsSmookingAllowed() {
		return isSmookingAllowed;
	}
	public void setIsSmookingAllowed(Boolean isSmookingAllowed) {
		this.isSmookingAllowed = isSmookingAllowed;
	}
	public Double getPriceByArea() {
		return priceByArea;
	}
	public void setPriceByArea(Double priceByArea) {
		this.priceByArea = priceByArea;
	}
	public REDetailedType getDetailedType() {
		return detailedType;
	}
	public void setDetailedType(REDetailedType detailedType) {
		this.detailedType = detailedType;
	}
	public String getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	
}
