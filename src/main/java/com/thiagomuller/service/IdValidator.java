package com.thiagomuller.service;

public interface IdValidator {
	
	public boolean validateIsValidInt(Integer id);
	
	public boolean validateIfIdAlreadyExists(Integer id);
}
