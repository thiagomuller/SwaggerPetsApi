package com.thiagomuller.service;

public interface PetTagValidator extends IdValidator{
	public boolean isTagNameEmpty(String tagName);
}
