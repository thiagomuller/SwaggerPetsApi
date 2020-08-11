package com.thiagomuller.service.impl;


import com.thiagomuller.model.Pet;

public class PetResponse {
	
	private Pet pet;
	
	private Response response;
	
	public PetResponse(Pet pet, Response response) {
		this.pet = pet;
		this.response = response;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	

}
