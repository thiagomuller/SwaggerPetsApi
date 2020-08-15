package com.thiagomuller.service;

public abstract interface IdValidator {
	
	public boolean validateIsValidInt(Integer id);
	
	public boolean validateIfIdAlreadyExists(Integer id);
}
