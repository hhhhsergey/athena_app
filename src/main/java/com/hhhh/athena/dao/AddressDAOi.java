package com.hhhh.athena.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hhhh.athena.model.Address;

@Repository
@Transactional
public class AddressDAOi implements AddressDAO{

	@PersistenceContext
	EntityManager entityManager;
	AddressDAOi addressDAOi;

	@Override
	public void update(Address address) {
		entityManager.merge(address);
	}
}
