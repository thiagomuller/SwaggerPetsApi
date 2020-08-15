package com.thiagomuller.service.impl.pets;

import java.util.Optional;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.IdValidator;

public abstract class IdValidatorImpl implements IdValidator{
	
	private PetsRepository petRepository;
	
	public IdValidatorImpl(PetsRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	@Override
	public boolean validateIsValidInt(Integer petId) {
		if(petId == null)
			return false;
		if(petId < 0)
			return false;
		return true;
	}

	@Override
	public boolean validateIfIdAlreadyExists(Integer petId) {
		Optional<Pet> pet = petRepository.findById(petId);
		if(!pet.isPresent())
			return false;
		return true;
	}
}
