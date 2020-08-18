package com.thiagomuller.service.impl.pets;

import org.springframework.stereotype.Service;

import com.thiagomuller.model.PetStatus;
import com.thiagomuller.service.pets.PetStatusValidator;

@Service
public class PetStatusValidatorImpl implements PetStatusValidator{

	@Override
	public boolean validateStatus(String status) {
		String upperCasedStatus = status.toUpperCase();
		PetStatus[] possibleStatus = PetStatus.values();
		for(int i  = 0; i < possibleStatus.length; i++) {
			if(possibleStatus[i].name().equals(upperCasedStatus))
				return true;
		}
		return false;
	}

}
