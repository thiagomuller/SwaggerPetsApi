package com.thiagomuller.repository.pets;

import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;

public interface PetsRepositoryCustom {
	public Iterable<Pet> findPetsByStatusIn(Iterable<PetStatus> statusList);
}
