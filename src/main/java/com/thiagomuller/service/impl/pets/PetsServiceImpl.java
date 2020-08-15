package com.thiagomuller.service.impl.pets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Image;
import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;
import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.PetResponse;
import com.thiagomuller.service.PetsService;
import com.thiagomuller.service.Response;

@Service
public class PetsServiceImpl implements PetsService{
	
	@Autowired
	private PetsRepository petsRepository;
	
	@Autowired
	private PetIdValidatorImpl petIdValidator;

	public PetResponse save(Pet pet) {
		/*
		Response petValidationResponse = petIdValidator.validatePetForCreation(pet);
		if(petValidationResponse.equals(Response.INVALIDID) || 
				petValidationResponse.equals(Response.INCOMPLETE))
			return new PetResponse(pet, petValidationResponse);
		if(petIdValidator.validateIfIdAlreadyExists(pet.getId()))
			return new PetResponse(null, Response.INVALIDID);
		Pet savedPet = petsRepository.save(pet);
		return new PetResponse(savedPet, Response.CREATED);
		*/
		return null;
	}
	
	public PetResponse update(Pet pet) {
		/*
		Response petValidationResponse = PetValidator.validatePetForCreation(pet);
		if(petValidationResponse.equals(Response.INVALIDID) || 
				petValidationResponse.equals(Response.INCOMPLETE))
			return new PetResponse(pet, petValidationResponse);
		if(!PetValidator.validateIfIdAlreadyExists(pet, this))
			return new PetResponse(null, Response.NOTFOUND);
		Pet updatedPet = petsRepository.save(pet);
		return new PetResponse(updatedPet, Response.UPDATED);
		*/
		return null;
	}
	
	public Iterable<Pet> findAllPetsByStatus(PetStatus status){
		//TODO implement this
		return null;
	}
	

	public PetResponse findPetById(Integer id) {
		Optional<Pet> pet = petsRepository.findById(id);
		if(!pet.isPresent())
			return new PetResponse(null, Response.NOTFOUND);
		return new PetResponse(pet.get(), Response.FOUND);
	}

	public Iterable<Pet> getAllPets() {
		return petsRepository.findAll();
	}

	public PetResponse deletePet(Integer id) {
		PetResponse findPetResponse = findPetById(id);
		if(findPetResponse.getResponse().equals(Response.NOTFOUND))
			return findPetResponse;
		petsRepository.delete(findPetResponse.getPet());
		return new PetResponse(findPetResponse.getPet(), Response.DELETED);
	}

	public void deleteAllPets() {
		petsRepository.deleteAll();
	}

	
	public PetResponse uploadImage(Integer petId, String imageUrl) {
		Pet pet = findPetById(petId).getPet();
		if(pet != null)
			pet.getPhotoUrls().add(new Image(imageUrl));
		PetResponse response = update(pet);
		if(response.getResponse().equals(Response.UPDATED))
			return new PetResponse(pet, Response.UPDATED);
		return new PetResponse(pet, Response.NOTFOUND);
	}

	@Override
	public PetResponse update(int id, String name, String status) {
		return null;
	}

}
