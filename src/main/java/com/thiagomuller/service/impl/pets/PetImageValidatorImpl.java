package com.thiagomuller.service.impl.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.repository.pets.ImageRepository;
import com.thiagomuller.service.impl.IdValidatorImpl;
import com.thiagomuller.service.pets.PetImageValidator;

@Service
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
