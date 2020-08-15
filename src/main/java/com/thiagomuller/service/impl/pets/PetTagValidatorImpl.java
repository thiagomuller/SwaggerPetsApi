package com.thiagomuller.service.impl.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.PetTagValidator;

@Service
public class PetTagValidatorImpl extends IdValidatorImpl implements PetTagValidator{
	
	@Autowired
	private PetsRepository petRepository;

	public PetTagValidatorImpl(PetsRepository petRepository) {
		super(petRepository);
	}

	@Override
	public boolean validateIfTagHasANotEmptyname(String tagName) {
		return tagName.isEmpty();
	}

}
