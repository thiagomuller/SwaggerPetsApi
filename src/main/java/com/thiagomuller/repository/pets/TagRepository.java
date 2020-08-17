package com.thiagomuller.repository.pets;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thiagomuller.model.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer>{}
