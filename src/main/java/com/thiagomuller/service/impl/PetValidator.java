package com.thiagomuller.service.impl;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.PetsRepository;

public class PetValidator {
	
	public static Response validatePetForCreation(Pet pet) {
		if(pet.getId() < 0) {
			return Response.INVALIDID;
		}
		if(pet.getName().isEmpty()) {
			return Response.INCOMPLETE;
		}
		if(pet.getStatus() == null) {
			return Response.INCOMPLETE;
		}
		if(pet.getPhotoUrls().isEmpty() || pet.getPhotoUrls() == null) {
			return Response.INCOMPLETE;
		}
		if(pet.getTags().isEmpty() || pet.getTags() == null) {
			return Response.INCOMPLETE;
		}
		return Response.VALIDPET;
	}
	
	public static boolean validateIfIdAlreadyExists(Pet pet, PetsRepository petsRepository) {
		Iterable<Pet> pets = petsRepository.findAll();
		for(Pet foundedPet : pets) {
			if(foundedPet.getId() == pet.getId()){
				return true;
			}
		}
		return false;
	}
}
