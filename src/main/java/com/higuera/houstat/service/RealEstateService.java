package com.higuera.houstat.service;

import java.util.List;

import com.higuera.houstat.model.RealEstate;

public interface RealEstateService {

	public boolean insertOrUpdate(RealEstate realEstate);
	
	public long insertOrUpdateList(List<RealEstate> reals);
}
