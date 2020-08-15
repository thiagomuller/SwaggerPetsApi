package com.thiagomuller.service;

public abstract interface IdValidator {
	
	public boolean validateIsValidInt(Integer petId);
	
	public boolean validateIfIdAlreadyExists(Integer petId);
}
