package com.thiagomuller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagomuller.model.Pet;
import com.thiagomuller.service.Response;
import com.thiagomuller.service.pets.PetResponse;
import com.thiagomuller.service.pets.PetsService;

@RestController
@RequestMapping("/pet")
public class PetsController {
	
	@Autowired
	private PetsService petService;
	
	@PostMapping
	public void createPet(@RequestBody Pet pet) {
		petService.saveOrUpdate(pet);
		
	}
	
	
	
}
