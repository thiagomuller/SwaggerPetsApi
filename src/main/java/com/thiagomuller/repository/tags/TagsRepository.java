package com.thiagomuller.repository.tags;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thiagomuller.model.Tag;

@Repository
public interface TagsRepository extends CrudRepository<Tag, Integer>{}
