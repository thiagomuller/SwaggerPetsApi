package com.thiagomuller.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PETS")
public class Pet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="photoUrls")
	@OneToMany
	private List<Image> photoUrls;
	
	@Column(name="tags")
	@OneToMany
	private List<Tag> tags;
	
	@Column(name="status")
	private Status status;

	public Pet(int id, String name, List<Image> photoUrls, List<Tag> tags, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public int getId() {
		return id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
			
}
