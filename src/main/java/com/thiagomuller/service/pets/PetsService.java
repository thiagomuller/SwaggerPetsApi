package com.thiagomuller.service.pets;


import java.util.List;
import java.util.Map;

import com.thiagomuller.model.Pet;
import com.thiagomuller.service.PetResponse;
import com.thiagomuller.service.ServiceResponse;

public interface PetsService {
	public ServiceResponse save(Pet pet);
	public ServiceResponse update(Pet pet);
	public ServiceResponse update(int id, String name, String status);
	public Map<PetResponse, Iterable<Pet>> findAllPetsByStatus(List<String> status);
	public ServiceResponse findPetById(Integer id);
	public Iterable<Pet> getAllPets();
	public ServiceResponse deletePet(Integer id);
	public ServiceResponse uploadImage(Integer petId, String imageUrl);
	public void deleteAllPets();
}
