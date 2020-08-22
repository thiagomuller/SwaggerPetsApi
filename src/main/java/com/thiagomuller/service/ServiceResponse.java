package com.thiagomuller.service;

public class ServiceResponse<S, T> {
	
	private S entity;
	
	private T entityResponse;

	public ServiceResponse(S repositoryReturnEntity, T entityResponse) {
		super();
		this.entity = repositoryReturnEntity;
		this.entityResponse = entityResponse;
	}

	public S getEntity() {
		return entity;
	}

	public void setEntity(S repositoryReturnEntity) {
		this.entity = repositoryReturnEntity;
	}

	public T getEntityResponse() {
		return entityResponse;
	}

	public void setEntityResponse(T entityResponse) {
		this.entityResponse = entityResponse;
	}

}
