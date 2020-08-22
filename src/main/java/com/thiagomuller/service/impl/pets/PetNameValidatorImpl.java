package com.thiagomuller.service.impl.pets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.pets.PetNameValidator;

@Service
public class PetNameValidatorImpl implements PetNameValidator{
	
	@Autowired
	private PetsRepository petsRepository;

	@Override
	public boolean isNameEmpty(String name) {
		return name.isEmpty();
	}

}
