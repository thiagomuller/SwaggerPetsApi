package com.thiagomuller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.pets.PetsRepository;

public interface PetIdValidator {
	
	public boolean validateIsValidInt(Integer petId);
	
	public boolean validateIfIdAlreadyExists(Integer petId);

}
