package com.thiagomuller.service.impl.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.repository.pets.TagRepository;
import com.thiagomuller.service.pets.PetTagValidator;

@Service
public class PetTagValidatorImpl extends IdValidatorImpl<TagRepository> implements PetTagValidator{
	
	@Autowired
	private TagRepository tagRepository;

	public PetTagValidatorImpl(TagRepository tagRepository) {
		super(tagRepository);
	}

	@Override
	public boolean isTagNameEmpty(String tagName) {
		return tagName.isEmpty();
	}

}
