package com.thiagomuller.repository.pets;

import com.thiagomuller.model.Pet;

public interface PetsRepositoryCustom {
	public Iterable<Pet> findPetsByStatus(Iterable<String> statusList);
}
