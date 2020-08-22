package com.thiagomuller.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiagomuller.model.Pet;
import com.thiagomuller.service.FileUploadService;
import com.thiagomuller.service.PetResponse;
import com.thiagomuller.service.ServiceResponse;
import com.thiagomuller.service.pets.PetsService;

@RestController
@RequestMapping("/pet")
public class PetsController {
	
	@Autowired
	private PetsService petService;
	
	@Autowired
	private FileUploadService fileService;
	
	@PostMapping
	public ResponseEntity createPet(@RequestBody Pet pet) {
		ServiceResponse serviceResponse = petService.save(pet);
		if(!(serviceResponse.getEntityResponse().equals(PetResponse.PETCREATED)
				|| serviceResponse.getEntityResponse().equals(PetResponse.PETUPDATED)))
			return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity(serviceResponse.getEntity(), HttpStatus.OK);
		
	}
	
	@PutMapping
	public ResponseEntity updatePet(@RequestBody Pet pet) {
		ServiceResponse serviceResponse = petService.save(pet);
		if(serviceResponse.getEntityResponse().equals(PetResponse.INVALIDPETID))
			return new ResponseEntity("Invalid ID supplied", HttpStatus.BAD_REQUEST);
		if(serviceResponse.getEntityResponse().equals(PetResponse.PETNOTFOUND))
			return new ResponseEntity("Pet not found", HttpStatus.NOT_FOUND);
		if(serviceResponse.getEntity().equals(pet))
			return new ResponseEntity(serviceResponse.getEntity(), HttpStatus.OK);
		return new ResponseEntity("Validation exception", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@GetMapping("/findByStatus")
	public ResponseEntity findByStatus(@RequestParam List<String> status) {
		Map<PetResponse, Iterable<Pet>> serviceResponse = petService.findAllPetsByStatus(status);
		if(serviceResponse.containsKey(PetResponse.INVALIDPETSTATUS))
			return new ResponseEntity("Invalid status value", HttpStatus.BAD_REQUEST);
		return new ResponseEntity(serviceResponse.get(PetResponse.PETFOUND), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity getAllPets() {
		return new ResponseEntity(petService.getAllPets(), HttpStatus.OK);
	}
	
	@GetMapping("/{petId}")
	public ResponseEntity findById(@PathVariable("petId") Integer id) {
		ServiceResponse serviceResponse = petService.findPetById(id);
		if(serviceResponse.getEntityResponse().equals(PetResponse.INVALIDPETID))
			return new ResponseEntity("Invalid ID supplied", HttpStatus.BAD_REQUEST);
		if(serviceResponse.getEntityResponse().equals(PetResponse.PETNOTFOUND))
			return new ResponseEntity("Pet not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity(serviceResponse.getEntity(), HttpStatus.OK);
		
	}
	
	@PostMapping("{/petId}")
	public ResponseEntity updatePet(@PathVariable("petId") Integer petId,
			@RequestParam String name,
			@RequestParam String status) {
		ServiceResponse serviceResponse = petService.update(petId, name, status);
		if(serviceResponse.getEntityResponse().equals(PetResponse.INVALIDPETID) || 
				serviceResponse.getEntityResponse().equals(PetResponse.INVALIDPETNAME) ||
				serviceResponse.getEntityResponse().equals(PetResponse.INVALIDPETSTATUS))
			return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity(serviceResponse.getEntity(), HttpStatus.OK);	
	}
	
	@DeleteMapping("/{petId}")
	public ResponseEntity deletePet(@PathVariable("petId") Integer petId) {
		ServiceResponse serviceResponse = petService.deletePet(petId);
		if(serviceResponse.getEntityResponse().equals(PetResponse.INVALIDPETID))
			return new ResponseEntity("Invalid ID supplied", HttpStatus.BAD_REQUEST);
		if(serviceResponse.getEntityResponse().equals(PetResponse.PETNOTFOUND))
			return new ResponseEntity("Pet not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/{petId}/uploadImage")
	public ResponseEntity uploadImage(@PathVariable("petId") Integer petId,
			@RequestParam String additionalMetadata,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			fileService.uploadFile(file);
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity("Coundn't upload file", HttpStatus.BAD_REQUEST);
		}		
		
	}
	
}
