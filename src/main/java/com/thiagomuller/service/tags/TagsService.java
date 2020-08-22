package com.thiagomuller.service.tags;

import com.thiagomuller.model.Tag;
import com.thiagomuller.service.ServiceResponse;

public interface TagsService {
	
	public ServiceResponse save(Tag tag);
	
	public ServiceResponse update(Tag tag);
	
	public Iterable<Tag> getAllTags();
	
	public ServiceResponse getTagById(Integer id);
	
	public ServiceResponse deleteTagById(Integer id);
	
	public void deletAllTags();

}
