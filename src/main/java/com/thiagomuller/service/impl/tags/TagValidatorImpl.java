package com.thiagomuller.service.impl.tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Tag;
import com.thiagomuller.repository.tags.TagsRepository;
import com.thiagomuller.service.impl.IdValidatorImpl;
import com.thiagomuller.service.tags.TagValidator;

@Service
public class TagValidatorImpl extends IdValidatorImpl<TagsRepository> implements TagValidator{
	
	@Autowired
	private TagsRepository tagsRepository;

	public TagValidatorImpl(TagsRepository tagRepository) {
		super(tagRepository);
	}

	@Override
	public boolean isTagNameEmpty(String tagName) {
		return tagName.isEmpty();
	}

	@Override
	public boolean doesIdAlreadyExists(Integer tagId) {
		Optional<Tag> optionalTagFoundInDb = tagsRepository.findById(tagId);
		if(optionalTagFoundInDb.isPresent())
			return true;
		return false;
	}
	
	@Override
	public boolean doesTagNameAlreadyExists(String tagName) {
		Iterable<Tag> repoResult = tagsRepository.findAll();
		List<Tag> tags = new ArrayList<>();
		repoResult.forEach(tags::add);
		if(tags.isEmpty()) 
			return false;
		for(Tag tag : tags) {
			if(tag.getName().equals(tagName))
				return true;
		}
		return false;
	}

	

}
