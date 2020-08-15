package com.thiagomuller.repository.pets;

import com.thiagomuller.model.Pet;

public interface PetsRepositoryCustom {
	public Iterable<Pet> findPetsByStatusIn(Iterable<String> statusList);
}
