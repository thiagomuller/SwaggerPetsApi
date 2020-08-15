package com.thiagomuller.repository.pets;

import org.springframework.data.repository.CrudRepository;

import com.thiagomuller.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer>{}
