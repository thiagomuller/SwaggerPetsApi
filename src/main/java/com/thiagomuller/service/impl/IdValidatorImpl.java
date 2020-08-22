package com.thiagomuller.service.impl;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.thiagomuller.service.IdValidator;

public abstract class IdValidatorImpl<T extends CrudRepository> implements IdValidator{
	
	private T repository;
	
	public IdValidatorImpl(T repository) {
		this.repository = repository;
	}
	
	public boolean validateIsValidInt(Integer id) {
		if(id == null)
			return false;
		if(id < 0)
			return false;
		return true;
	}

	public boolean doesIdAlreadyExistsInDb(Integer id) {
		Optional<T> entity = repository.findById(id);
		if((entity != null) && (entity.isPresent()))
			return true;
		return false;
	}
}
