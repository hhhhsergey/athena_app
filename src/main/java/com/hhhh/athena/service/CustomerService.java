package com.hhhh.athena.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhhh.athena.dao.AddressDAOi;
import com.hhhh.athena.dao.CustomerDAOi;
import com.hhhh.athena.model.Address;
import com.hhhh.athena.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAOi customerDAOi;

	@Autowired
	private AddressDAOi addressDAOi;
	
	public Customer createCustomerByJson(String json)throws JsonMappingException, JsonProcessingException  {
		ObjectMapper mapper = new ObjectMapper();
		Customer customer=mapper.readValue(json, Customer.class);
		customer.getActual_address().setCreated(new Date());
		customer.getActual_address().setModified(new Date());
		customer.getRegistred_address().setCreated(new Date());
		customer.getRegistred_address().setModified(new Date());
		return customerDAOi.create(customer);
	}
	
	public Customer findCustomerById(long id){
		return customerDAOi.findById(id);
	}

	public Customer findCustomerByFirst_nameAndLast_name(String first_name,String last_name) {
		return customerDAOi.findByFirst_nameAndLast_name(first_name,last_name);
	}

	public Customer updateCustomerActualAddressByJson(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Customer customer=mapper.readValue(json, Customer.class);
		Customer customerFromDb=customerDAOi.findById(customer.getId());
		Address newAddress=customer.getActual_address();
		newAddress.setCreated(customerFromDb.getActual_address().getCreated());
		newAddress.setModified(new Date());
		newAddress.setId(customerFromDb.getActual_address().getId());
		addressDAOi.update(newAddress);
		return customerDAOi.findById(customerFromDb.getId());
	}
	
	public List<Customer> findAllCustomers() {
		return customerDAOi.findAll();
	}
}
