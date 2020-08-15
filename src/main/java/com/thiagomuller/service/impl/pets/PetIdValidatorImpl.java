package com.thiagomuller.service.impl.pets;


import org.springframework.stereotype.Service;

import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.PetIdValidator;

@Service
public class PetIdValidatorImpl extends IdValidatorImpl implements PetIdValidator{
	
	private PetsRepository petRepository;
	
	public PetIdValidatorImpl(PetsRepository petRepository) {
		super(petRepository);
	}
}
