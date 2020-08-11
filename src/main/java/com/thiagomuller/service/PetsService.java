package com.thiagomuller.service;


import com.thiagomuller.model.Pet;
import com.thiagomuller.model.Status;
import com.thiagomuller.service.impl.PetResponse;

public interface PetsService {
	public PetResponse save(Pet pet);
	public PetResponse update(Pet pet);
	public Iterable<Pet> findAllPetsByStatus(Status status);
	public PetResponse findPetById(Integer id);
	public Iterable<Pet> getAllPets();
	public PetResponse deletePet(Integer id);
	public PetResponse uploadImage(Integer petId, String imageUrl);
	public void deleteAllPets();
}
