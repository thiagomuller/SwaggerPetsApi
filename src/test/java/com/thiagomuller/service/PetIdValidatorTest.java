package com.thiagomuller.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.thiagomuller.model.Pet;
import com.thiagomuller.model.PetStatus;
import com.thiagomuller.repository.pets.PetsRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetIdValidatorTest {
	
	@Autowired
	PetIdValidator petIdValidator;
	
	private static Pet fakePet;
	
	@MockBean
	PetsRepository petRepository;
	
	@BeforeAll
	public static void setUpFakePet() {
		fakePet = new Pet();
		fakePet.setId(1);
		fakePet.setName("Jack");
		fakePet.setStatus(PetStatus.AVAILABLE);
	}
	
	@Test
	public void negativePetIdShouldReturnFalse() {
		assertFalse(petIdValidator.validateIsValidInt(-1), "Negative pet id should return false");
	}
	
	@Test
	public void petWithExistentIdShouldReturnTrue() {
		when(petRepository.findById(1)).thenReturn(Optional.of(fakePet));
		assertTrue(petIdValidator.validateIfIdAlreadyExists(1), "Already existing pet id on db should return true");
	}
	
	@Test
	public void petWithNonExistentIdShouldReturnTrue() {
		when(petRepository.findById(1)).thenReturn(Optional.of(fakePet));
		assertFalse(petIdValidator.validateIfIdAlreadyExists(2), "Non existing, positive pet id should return false");
	}
	
}
