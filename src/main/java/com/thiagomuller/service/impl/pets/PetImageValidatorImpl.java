package com.thiagomuller.service.impl.pets;

import org.springframework.beans.factory.annotation.Autowired;

import com.thiagomuller.repository.pets.ImageRepository;
import com.thiagomuller.service.pets.PetImageValidator;

public class PetImageValidatorImpl extends IdValidatorImpl<ImageRepository> implements PetImageValidator{
	
	@Autowired
    ImageRepository petImageRepository;
	
	public PetImageValidatorImpl(ImageRepository repository) {
		super(repository);
	}

	

	@Override
	public boolean isPhotoUrlEmpty(String photoUrl) {
		return photoUrl.isEmpty();
	}

}
