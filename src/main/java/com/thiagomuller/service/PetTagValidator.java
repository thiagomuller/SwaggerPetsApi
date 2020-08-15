package com.thiagomuller.service;

public interface PetTagValidator extends IdValidator{
	public boolean validateIfTagHasANotEmptyname(String tagName);
}
