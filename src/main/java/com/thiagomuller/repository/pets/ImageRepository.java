package com.thiagomuller.repository.pets;

import org.springframework.data.repository.CrudRepository;

import com.thiagomuller.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer>{}
