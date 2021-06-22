package com.hhhh.athena.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hhhh.athena.exceptions.CustomerisExistException;
import com.hhhh.athena.model.Customer;

@Repository
@Transactional
public class CustomerDAOi implements CustomerDAO {

	@PersistenceContext
	@Autowired
	EntityManager entityManager;
	CustomerDAOi customerDAOi;
	AddressDAOi addressDAOi;

	@Override
	public Customer create(Customer customer) {
		entityManager.persist(customer.getRegistred_address());
		entityManager.persist(customer.getActual_address());
		entityManager.persist(customer);
		return customer;
	}

	@Override
	public Customer findById(long id){
		try {
			Customer customerFromDB=entityManager.createQuery
					("SELECT e from Customer e where e.id=:id", Customer.class)
					.setParameter("id",id)
					.setMaxResults(1)
					.getSingleResult();
		return customerFromDB;
		}catch (NoResultException e) {
			throw new CustomerisExistException("customer is not exist");
		}
	}
	
	@Override
	public Customer findByFirst_nameAndLast_name(String first_name,String last_name) {
			try{
				Customer customerFromDB=entityManager.createQuery
					("select e from Customer e where "
							+ "e.first_name=:first_name and "
							+ "e.last_name=:last_name", Customer.class)
					.setParameter("first_name",first_name)
					.setParameter("last_name",last_name)
					.setMaxResults(1)
					.getSingleResult();
			return customerFromDB;
			}catch(NoResultException e) {
				throw new CustomerisExistException("customer is not exist");
			}
		}
	
	@Override
	public List<Customer> findAll() {
		return entityManager.createQuery("from Customer", Customer.class).getResultList();
	}
}