package com.thiagomuller.repository.impl.pets;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thiagomuller.model.Pet;
import com.thiagomuller.repository.pets.PetsRepositoryCustom;

@Repository
public class PetsRepositoryCustomImpl implements PetsRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Iterable<Pet> findPetsByStatusIn(Iterable<String> statusList) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Pet pet");
		hql.append(" where pet.status");
		hql.append(" in(:status)");
		Query query = entityManager.createNativeQuery(hql.toString());
		query.setParameter("status", statusList);
		return query.getResultList();
	}

}
