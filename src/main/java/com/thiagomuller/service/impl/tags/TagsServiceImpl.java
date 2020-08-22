package com.thiagomuller.service.impl.tags;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Tag;
import com.thiagomuller.repository.tags.TagsRepository;
import com.thiagomuller.service.ServiceResponse;
import com.thiagomuller.service.TagResponse;
import com.thiagomuller.service.tags.TagValidator;
import com.thiagomuller.service.tags.TagsService;

@Service
public class TagsServiceImpl implements TagsService{

	@Autowired
	private TagValidator tagValidator;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@Override
	public ServiceResponse<Tag, TagResponse>  save(Tag tag) {
		if(!tagValidator.validateIsValidInt(tag.getId()))
			return new ServiceResponse(tag, TagResponse.INVALIDTAGID);
		if(tagValidator.isTagNameEmpty(tag.getName()))
			return new ServiceResponse(tag, TagResponse.INVALIDTAGNAME);
		if(tagValidator.doesTagNameAlreadyExists(tag.getName()))
			return update(tag);
		if(tagValidator.doesIdAlreadyExists(tag.getId()))
			return update(tag);
		return new ServiceResponse(tagsRepository.save(tag), TagResponse.TAGCREATED);
	}
	
	@Override
	public ServiceResponse<Tag, TagResponse>  update(Tag tag) {
		Tag tagFoundInRepo = tagsRepository.findById(tag.getId()).get();
		tagFoundInRepo.setName(tag.getName());
		return new ServiceResponse(tagsRepository.save(tagFoundInRepo), TagResponse.TAGUPDATED);
	}

	@Override
	public Iterable<Tag> getAllTags() {
		return tagsRepository.findAll();
	}
	
	@Override
	public ServiceResponse<Tag, TagResponse> getTagById(Integer id) {
		if(!tagValidator.validateIsValidInt(id))
			return new ServiceResponse(null, TagResponse.INVALIDTAGID);
		Optional<Tag> repoResponse = tagsRepository.findById(id);
		if(!repoResponse.isPresent()) {
			return new ServiceResponse(null, TagResponse.TAGNOTFOUND);
		}
		return new ServiceResponse(repoResponse.get(), TagResponse.TAGFOUND);
	}

	@Override
	public ServiceResponse<Tag, TagResponse> deleteTagById(Integer id) {
		if(!tagValidator.doesIdAlreadyExistsInDb(id))
			return new ServiceResponse(null, TagResponse.TAGNOTFOUND);
		tagsRepository.deleteById(id);
		return new ServiceResponse(null, TagResponse.TAGDELETED);
	}

	@Override
	public void deletAllTags() {
		tagsRepository.deleteAll();
		
	}
}
