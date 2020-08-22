package com.thiagomuller.service;

public abstract interface IdValidator {
	
	public boolean validateIsValidInt(Integer id);
	
	public boolean doesIdAlreadyExistsInDb(Integer id);
}
