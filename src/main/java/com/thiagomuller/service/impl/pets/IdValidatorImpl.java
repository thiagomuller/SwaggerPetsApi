package com.thiagomuller.service.impl.pets;

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

	public boolean validateIfIdAlreadyExists(Integer id) {
		Optional<T> entity = repository.findById(id);
		if(!entity.isPresent())
			return false;
		return true;
	}
}
