package com.thiagomuller.service.impl.pets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagomuller.model.Image;
import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;
import com.thiagomuller.model.Tag;
import com.thiagomuller.repository.pets.PetsRepository;
import com.thiagomuller.service.Response;
import com.thiagomuller.service.pets.PetIdValidator;
import com.thiagomuller.service.pets.PetImageValidator;
import com.thiagomuller.service.pets.PetNameValidator;
import com.thiagomuller.service.pets.PetResponse;
import com.thiagomuller.service.pets.PetStatusValidator;
import com.thiagomuller.service.pets.PetTagValidator;
import com.thiagomuller.service.pets.PetsService;

@Service
public class PetsServiceImpl implements PetsService{
	
	@Autowired
	PetsRepository petsRepository;
	
	@Autowired
	PetIdValidator petIdValidator;
	
	@Autowired
	PetTagValidator petTagValidator;
	
	@Autowired
	PetNameValidator petNameValidator;
	
	@Autowired
	PetStatusValidator petStatusValidator;
	
	@Autowired
	PetImageValidator petImageValidator;
	
	@Override
	public PetResponse saveOrUpdate(Pet pet) {
		Response attrValidationResponse = validatePetAttributes(pet);
		if(!attrValidationResponse.equals(Response.VALIDID))
			return new PetResponse(pet, attrValidationResponse);
		return new PetResponse(petsRepository.save(pet), Response.CREATED);
	}

	@Override
	public PetResponse update(int id, String name, String status) {
		if(!petIdValidator.validateIfIdAlreadyExists(id))
			return new PetResponse(null, Response.NOTFOUND);
		if(petNameValidator.isNameEmpty(name))
			return new PetResponse(null, Response.INVALIDNAME);
		if(!petIdValidator.validateIsValidInt(id))
			return new PetResponse(null, Response.INVALIDID);
		if(!petStatusValidator.validateStatus(status))
			return new PetResponse(null, Response.INVALIDSTATUS);
		Pet petToUpdate = petsRepository.findById(id).get();
		petToUpdate.setName(name);
		petToUpdate.setStatus(PetStatus.valueOf(status));
		return new PetResponse(petsRepository.save(petToUpdate), Response.UPDATED);
	}

	@Override
	public Iterable<Pet> findAllPetsByStatus(List<String> status) {
		List<PetStatus> validStatus = new ArrayList<>();
		for(String stat : status) {
			if(petStatusValidator.validateStatus(stat))
				status.add(stat);
		}
		return petsRepository.findPetsByStatusIn(validStatus);
	}

	@Override
	public PetResponse findPetById(Integer id) {
		if(!petIdValidator.validateIfIdAlreadyExists(id)) {
			return new PetResponse(null, Response.NOTFOUND);
		}
		return new PetResponse(petsRepository.findById(id).get(), Response.VALIDPET);
	}

	@Override
	public Iterable<Pet> getAllPets() {
		return petsRepository.findAll();
	}

	@Override
	public PetResponse deletePet(Integer id) {
		if(!petIdValidator.validateIfIdAlreadyExists(id)) {
			return new PetResponse(null, Response.NOTFOUND);
		}
		Pet petToDelete = petsRepository.findById(id).get();
		petsRepository.delete(petToDelete);
		return new PetResponse(null, Response.DELETED);
	}

	@Override
	public PetResponse uploadImage(Integer petId, String imageUrl) {
		if(!petIdValidator.validateIfIdAlreadyExists(petId)){
			return new PetResponse(null, Response.NOTFOUND);
		}
		if(petImageValidator.isPhotoUrlEmpty(imageUrl))
			return new PetResponse(null, Response.INVALIDIMAGEURL);
		Pet pet = petsRepository.findById(petId).get();
		pet.getPhotoUrls().add(new Image(imageUrl));
		return new PetResponse(petsRepository.save(pet), Response.UPDATED);
	}
	

	@Override
	public void deleteAllPets() {
		petsRepository.deleteAll();
	}

	
	private Response validatePetAttributes(Pet pet) {
		if(!petIdValidator.validateIsValidInt(pet.getId()))
			return Response.INVALIDID;
		if(petNameValidator.isNameEmpty(pet.getName()))
			return Response.INVALIDNAME;
		if(verifyIfAnyOftheGivenTagNamesIsEmpty(pet.getTags()))
			return Response.INVALIDTAGNAME;
		return Response.VALIDID;
	}
	
	private boolean validatePetStatus(String status) {
		if(!petStatusValidator.validateStatus(status))
			return false;
		return true;
		
	}
	
	private boolean verifyIfAnyOftheGivenTagNamesIsEmpty(List<Tag> tags) {
		for(int i = 0; i < tags.size(); i++) {
			if(tags.get(i).getName().isEmpty())
				return true;
		}
		return false;
	}
}
