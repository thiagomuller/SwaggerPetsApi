package com.thiagomuller.service.impl.pets;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.PetIdValidator;

@Service
public class PetIdValidatorImpl implements PetIdValidator{
	
	private PetsRepository petRepository;
	
	public PetIdValidatorImpl(PetsRepository petRepository) {
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
