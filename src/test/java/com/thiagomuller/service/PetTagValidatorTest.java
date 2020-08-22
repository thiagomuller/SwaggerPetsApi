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

import com.thiagomuller.model.Tag;
import com.thiagomuller.repository.tags.TagsRepository;
import com.thiagomuller.service.tags.TagValidator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetTagValidatorTest {
	
	@Autowired
	TagValidator petTagValidator;
	
	private static Tag fakeTag;
	
	@MockBean
	TagsRepository tagRepository;
	
	@BeforeAll
	public static void setup() {
		fakeTag = new Tag();
		fakeTag.setId(1);
		fakeTag.setName("Cute");
	}
	
	@Test
	public void tagWithNegativeIdShouldReturnFalse(){
		assertFalse(petTagValidator.validateIsValidInt(-1),
				"A tag id must be positive");
	}
	
	@Test
	public void tagWithExistentIdShouldReturnTrue() {
		when(tagRepository.findById(1)).thenReturn(Optional.of(fakeTag));
		assertTrue(petTagValidator.doesIdAlreadyExistsInDb(1),
				"An existent tag id must return true");
	}
	
	@Test
	public void tagWithNonExistentIdShouldReturnFalse() {
		when(tagRepository.findById(1)).thenReturn(Optional.of(fakeTag));
		assertFalse(petTagValidator.doesIdAlreadyExistsInDb(2),
				"A non existent tag id must return false");
	}
	
	@Test
	public void tagWithNoTextShouldReturnFalse() {
		assertTrue(petTagValidator.isTagNameEmpty(""),
				"Tags with empty name should return false");
	}
	

}
