package com.thiagomuller.service.pets;

import com.thiagomuller.service.IdValidator;

public interface PetImageValidator extends IdValidator{
	public boolean isPhotoUrlEmpty(String photoUrl);
}
