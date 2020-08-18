package com.thiagomuller.service.pets;

import com.thiagomuller.service.IdValidator;

public interface PetTagValidator extends IdValidator{
	public boolean isTagNameEmpty(String tagName);
}
