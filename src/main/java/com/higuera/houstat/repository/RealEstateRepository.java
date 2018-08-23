package com.higuera.houstat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.higuera.houstat.model.RealEstate;

public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {

	public RealEstate findByPropertyCodeAndLastVers(Integer code, boolean lastVers);
	
	public List<RealEstate> findByLastUpdateMsGreaterThanEqual(long lasttimems);
	
}
