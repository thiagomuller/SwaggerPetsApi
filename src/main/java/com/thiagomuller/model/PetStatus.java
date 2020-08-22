package com.thiagomuller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PetStatus {
	
	@JsonProperty("available")
	AVAILABLE,
	
	@JsonProperty("pending")
	PENDING,
	
	@JsonProperty("sold")
	SOLD

}
