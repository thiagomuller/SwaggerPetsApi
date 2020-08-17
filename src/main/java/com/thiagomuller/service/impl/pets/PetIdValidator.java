package com.thiagomuller.service.impl.pets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.IdValidator;

@Service
public class PetIdValidator{
	
	@Autowired
	public IdValidator idValidator;
	
	private PetsRepository petRepository;
	
	public PetIdValidator(PetsRepository petRepository) {
		this.petRepository = petRepository;
	}
}
