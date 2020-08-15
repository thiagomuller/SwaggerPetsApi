package com.thiagomuller.service.impl.pets;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.pets.PetsRepository;

@Service
public class PetIdValidatorImpl {
	
	private PetsRepository petRepository;
	
	public PetIdValidatorImpl(PetsRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	public boolean validateIsValidInt(Integer petId) {
		if(!(petId instanceof Integer) || (petId != null)) {
			return false;
		}
		if(petId < 0) {
			return false;
		}
		return true;
	}
	
	public boolean validateIfIdAlreadyExists(Integer petId) {
		Optional<Pet> pet = petRepository.findById(petId);
		if(!pet.isPresent()) {
			return false;
		}
		return true;
	}
}
