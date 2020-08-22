package com.thiagomuller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagomuller.model.Tag;
import com.thiagomuller.service.ServiceResponse;
import com.thiagomuller.service.tags.TagsService;
import com.thiagomuller.service.TagResponse;

@RestController
@RequestMapping("/tag")
public class TagsController {
	
	@Autowired
	private TagsService tagsService;
	
	@PostMapping
	public ResponseEntity createTag(@RequestBody Tag tag) {
		ServiceResponse serviceResponse = tagsService.save(tag);
		if(!(serviceResponse.getEntityResponse().equals(TagResponse.TAGCREATED)
				|| serviceResponse.getEntityResponse().equals(TagResponse.TAGUPDATED)))
			return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity(serviceResponse.getEntity(),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity updateTag(@RequestBody Tag tag) {
		ServiceResponse serviceResponse = tagsService.save(tag);
		if(!serviceResponse.getEntityResponse().equals(TagResponse.TAGCREATED) || 
				!serviceResponse.getEntityResponse().equals(TagResponse.TAGUPDATED))
			return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity(serviceResponse.getEntity(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity getAllTags() {
		return new ResponseEntity(tagsService.getAllTags(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{tagId}")
	public ResponseEntity getTagById(@PathVariable("tagId") Integer tagId) {
		ServiceResponse serviceResponse = tagsService.getTagById(tagId);
		if(serviceResponse.getEntityResponse().equals(TagResponse.TAGNOTFOUND) || 
				serviceResponse.getEntityResponse().equals(TagResponse.INVALIDTAGID))
			return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity(serviceResponse.getEntity(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{tagId}")
	public ResponseEntity deleteTagById(@PathVariable("tagId") Integer tagId) {
		ServiceResponse serviceResponse = tagsService.deleteTagById(tagId);
		if(serviceResponse.getEntityResponse().equals(TagResponse.INVALIDTAGID))
			return new ResponseEntity("Invalid ID supplied", HttpStatus.BAD_REQUEST);
		if(serviceResponse.getEntityResponse().equals(TagResponse.TAGNOTFOUND))
			return new ResponseEntity("Pet not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity(HttpStatus.OK);
	}

}
