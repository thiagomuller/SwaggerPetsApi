package com.thiagomuller.service;


public interface PetIdValidator {
	
	public boolean validateIsValidInt(Integer petId);
	
	public boolean validateIfIdAlreadyExists(Integer petId);

}
