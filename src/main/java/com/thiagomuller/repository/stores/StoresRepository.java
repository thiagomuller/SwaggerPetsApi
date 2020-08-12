package com.thiagomuller.repository.stores;

import org.springframework.data.repository.CrudRepository;

import com.thiagomuller.model.Order;

public interface StoresRepository extends CrudRepository<Order, Integer>{}
