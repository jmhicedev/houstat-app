package com.higuera.houstat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.higuera.houstat.model.RealEstate;
import com.higuera.houstat.repository.RealEstateRepository;
import com.higuera.houstat.service.RealEstateService;

@Service
public class RealEstateServiceImpl implements RealEstateService {
	
	@Autowired
	RealEstateRepository realEstateRepository;

	@Override
	public boolean insertOrUpdate(RealEstate realEstate) {
		RealEstate reAux = realEstateRepository.findByPropertyCodeAndLastVers(realEstate.getPropertyCode(), true);
		if(reAux != null) {
			if(!realEstate.equals(reAux)) {
				realEstate.setVers(reAux.getVers()+1);
				realEstate.setLastVers(true);
				reAux.setLastVers(false);
				
				realEstateRepository.save(realEstate);
				realEstateRepository.save(reAux);
			}
		} else {
			realEstateRepository.save(realEstate);
		}
		return true;
	}

	@Override
	public long insertOrUpdateList(List<RealEstate> reals) {
		long counter = 0;
		
		for(RealEstate realEstate: reals) {
			if(insertOrUpdate(realEstate)) {
				counter++;
			}
		}
		return counter;
	}

	
}
