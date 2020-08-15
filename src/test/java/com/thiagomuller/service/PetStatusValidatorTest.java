package com.thiagomuller.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.thiagomuller.service.impl.pets.PetStatusValidatorImpl;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetStatusValidatorTest {
	
	@Autowired
	PetStatusValidatorImpl petStatusValidator;
	
	@Test
	public void statusOutOfEnumValuesShouldReturnFalse() {
		assertFalse(petStatusValidator.validateStatus("batata"), 
				"A status that is out of the enum possible values must return false");
	}
	
	@Test
	public void statusPresentInEnumValuesShouldReturnTrue() {
		assertTrue(petStatusValidator.validateStatus("AVAILABLE"), 
				"A status that is inside of the enum possible values must return true");
		assertTrue(petStatusValidator.validateStatus("PENDING"), 
				"A status that is inside of the enum possible values must return true");
		assertTrue(petStatusValidator.validateStatus("SOLD"), 
				"A status that is inside of the enum possible values must return true");
	}
	
	@Test
	public void statusPresentInEnumValueShouldReturnTrueCaseInsensitive() {
		assertTrue(petStatusValidator.validateStatus("aVaILaBlE"), 
				"A status that is inside of the enum possible values must return true");
		assertTrue(petStatusValidator.validateStatus("PeNdInG"), 
				"A status that is inside of the enum possible values must return true");
		assertTrue(petStatusValidator.validateStatus("sOlD"), 
				"A status that is inside of the enum possible values must return true");
	}

}
