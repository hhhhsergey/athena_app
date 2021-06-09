package com.hhhh.athena.dao;

import java.util.List;

import com.hhhh.athena.model.Customer;

public interface CustomerDAO {
	
	Customer create(Customer customer);
	Customer findById(long id);
	Customer findByFirst_nameAndLast_name(String first_name,String last_name);
	List<Customer>findAll();
}
