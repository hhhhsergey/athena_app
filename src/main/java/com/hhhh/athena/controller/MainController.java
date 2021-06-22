package com.hhhh.athena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hhhh.athena.model.Customer;
import com.hhhh.athena.service.CustomerService;

@RestController
public class MainController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping("customers")
	public List<Customer> findall() {
		return customerService.findAllCustomers();
	}

	@PostMapping("customers/create")
	public Customer create(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		return customerService.createCustomerByJson(json);
	}
	
	@PutMapping("customers/updateactualaddress")
	public Customer update(@RequestBody String json) throws JsonMappingException, JsonProcessingException  {
		return customerService.updateCustomerActualAddressByJson(json);
	}
	
	@GetMapping("customers/{id}")
	public Customer find(@PathVariable long id) {
		return customerService.findCustomerById(id);
	}
	
	@GetMapping("customers/search")
	public Customer findByFirstNameAndLastname(@RequestParam String first_name,@RequestParam String last_name) {
		return customerService.findCustomerByFirst_nameAndLast_name(first_name,last_name);
	}
}
