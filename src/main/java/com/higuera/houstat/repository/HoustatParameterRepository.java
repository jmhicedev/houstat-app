package com.higuera.houstat.repository;

import org.springframework.data.repository.CrudRepository;

import com.higuera.houstat.model.HoustatParameter;

public interface HoustatParameterRepository extends CrudRepository<HoustatParameter, Long> {

	public HoustatParameter findByName(String name);
	
	
}
