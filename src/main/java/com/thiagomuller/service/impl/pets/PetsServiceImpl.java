package com.thiagomuller.service.impl.pets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Image;
import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;
import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.PetResponse;
import com.thiagomuller.service.ServiceResponse;
import com.thiagomuller.service.pets.PetIdValidator;
import com.thiagomuller.service.pets.PetImageValidator;
import com.thiagomuller.service.pets.PetNameValidator;
import com.thiagomuller.service.pets.PetStatusValidator;
import com.thiagomuller.service.pets.PetsService;
import com.thiagomuller.service.tags.TagValidator;

@Service
public class PetsServiceImpl implements PetsService{
	
	@Autowired
	PetsRepository petsRepository;
	
	@Autowired
	PetIdValidator petIdValidator;
	
	@Autowired
	TagValidator petTagValidator;
	
	@Autowired
	PetNameValidator petNameValidator;
	
	@Autowired
	PetStatusValidator petStatusValidator;
	
	@Autowired
	PetImageValidator petImageValidator;
	
	@Override
	public ServiceResponse<Pet, PetResponse> save(Pet pet) {
		if(!petIdValidator.validateIsValidInt(pet.getId()))
			return new ServiceResponse(pet, PetResponse.INVALIDPETID);
		if(petNameValidator.isNameEmpty(pet.getName()))
			return new ServiceResponse(pet, PetResponse.INVALIDPETNAME);
		if(petIdValidator.doesIdAlreadyExistsInDb(pet.getId()))
			return update(pet);
		return new ServiceResponse(petsRepository.save(pet), PetResponse.PETCREATED);
	}
	
	@Override
	public ServiceResponse update(Pet pet) {
		Pet petFoundInDb = petsRepository.findById(pet.getId()).get();
		petFoundInDb.setName(pet.getName());
		petFoundInDb.setPhotoUrls(pet.getPhotoUrls());
		petFoundInDb.setStatus(pet.getStatus());
		petFoundInDb.setTags(pet.getTags());
		return new ServiceResponse(petsRepository.save(petFoundInDb), PetResponse.PETUPDATED);
	}

	@Override
	public ServiceResponse<Pet, PetResponse> update(int id, String name, String status) {
		if(!petIdValidator.doesIdAlreadyExistsInDb(id))
			return new ServiceResponse(null, PetResponse.PETNOTFOUND);
		if(petNameValidator.isNameEmpty(name))
			return new ServiceResponse(null, PetResponse.INVALIDPETNAME);
		if(!petIdValidator.validateIsValidInt(id))
			return new ServiceResponse(null, PetResponse.INVALIDPETID);
		if(!petStatusValidator.validateStatus(status))
			return new ServiceResponse(null, PetResponse.INVALIDPETSTATUS);
		Pet petToUpdate = petsRepository.findById(id).get();
		petToUpdate.setName(name);
		petToUpdate.setStatus(PetStatus.valueOf(status));
		return new ServiceResponse(petsRepository.save(petToUpdate), PetResponse.PETUPDATED);
	}

	@Override
	public Map<PetResponse, Iterable<Pet>> findAllPetsByStatus(List<String> status) {
		Map<PetResponse, Iterable<Pet>> response = new HashMap<>();
		List<PetStatus> validStatus = new ArrayList<>();
		for(String stat : status) {
			if(petStatusValidator.validateStatus(stat))
				status.add(stat);
		}
		if(validStatus.isEmpty()) {
			response.put(PetResponse.INVALIDPETSTATUS, null);
			return response;
		}
		response.put(PetResponse.PETFOUND, petsRepository.findPetsByStatusIn(validStatus));
		return response;
	}

	@Override
	public ServiceResponse<Pet, PetResponse> findPetById(Integer id) {
		if(!petIdValidator.doesIdAlreadyExistsInDb(id)) {
			return new ServiceResponse(null, PetResponse.PETNOTFOUND);
		}
		return new ServiceResponse(petsRepository.findById(id).get(), PetResponse.VALIDPET);
	}

	@Override
	public Iterable<Pet> getAllPets() {
		return petsRepository.findAll();
	}

	@Override
	public ServiceResponse<Pet, PetResponse> deletePet(Integer id) {
		if(!petIdValidator.doesIdAlreadyExistsInDb(id)) {
			return new ServiceResponse(null, PetResponse.PETNOTFOUND);
		}
		if(!petIdValidator.validateIsValidInt(id))
			return new ServiceResponse(null, PetResponse.INVALIDPETID);
		Pet petToDelete = petsRepository.findById(id).get();
		petsRepository.delete(petToDelete);
		return new ServiceResponse(null, PetResponse.PETDELETED);
	}

	@Override
	public ServiceResponse<Pet, PetResponse> uploadImage(Integer petId, String imageUrl) {
		if(!petIdValidator.doesIdAlreadyExistsInDb(petId)){
			return new ServiceResponse(null, PetResponse.PETNOTFOUND);
		}
		if(petImageValidator.isPhotoUrlEmpty(imageUrl))
			return new ServiceResponse(null, PetResponse.INVALIDIMAGEURL);
		Pet pet = petsRepository.findById(petId).get();
		pet.getPhotoUrls().add(new Image(imageUrl));
		return new ServiceResponse(petsRepository.save(pet), PetResponse.PETUPDATED);
	}
	

	@Override
	public void deleteAllPets() {
		petsRepository.deleteAll();
	}

}
