package com.thiagomuller.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PETS")
public class Pet {
	
	@Id
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="photoUrls")
	@OneToMany(mappedBy="id")
	private List<Image> photoUrls;
	
	@OneToMany(mappedBy = "petId")
	private List<Tag> tags;
	
	@Column(name="status")
	private PetStatus status;
	
	public Pet() {}

	public Pet(Integer id, String name, List<Image> photoUrls, List<Tag> tags, PetStatus status) {
		this.id = id;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Image> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<Image> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public PetStatus getStatus() {
		return status;
	}

	public void setStatus(PetStatus status) {
		this.status = status;
	}
			
}
