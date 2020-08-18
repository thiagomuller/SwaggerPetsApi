package com.thiagomuller.service.pets;


import java.util.List;

import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;

public interface PetsService {
	public PetResponse saveOrUpdate(Pet pet);
	public PetResponse update(int id, String name, String status);
	public Iterable<Pet> findAllPetsByStatus(List<String> status);
	public PetResponse findPetById(Integer id);
	public Iterable<Pet> getAllPets();
	public PetResponse deletePet(Integer id);
	public PetResponse uploadImage(Integer petId, String imageUrl);
	public void deleteAllPets();
}
