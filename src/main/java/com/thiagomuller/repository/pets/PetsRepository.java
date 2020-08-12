package com.thiagomuller.repository.pets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;

@Repository
public interface PetsRepository extends CrudRepository<Pet, Integer>, PetsRepositoryCustom{}