package com.thiagomuller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAGS")
public class Tag {
	
	@Id
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="petId")
	private Integer petId;
	
	public Tag() {}
	
	public Tag(int id, String name, Integer petId) {
		this.id = id;
		this.name = name;
		this.petId = petId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}
	
	
	
	
	
}
