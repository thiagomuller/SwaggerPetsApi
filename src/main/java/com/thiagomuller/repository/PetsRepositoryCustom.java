package com.thiagomuller.repository;

import com.thiagomuller.model.Pet;

public interface PetsRepositoryCustom {
	public Iterable<Pet> findPetsByStatus(Iterable<String> statusList);
}
