package com.thiagomuller.service.impl.pets;

import org.springframework.stereotype.Service;

import com.thiagomuller.service.pets.PetNameValidator;

@Service
public class PetNameValidatorImpl implements PetNameValidator{

	@Override
	public boolean isNameEmpty(String name) {
		return name.isEmpty();
	}

}
