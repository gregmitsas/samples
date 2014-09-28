package com.gregmitsas.service;

import java.util.List;

import com.gregmitsas.model.Customer;

public interface CustomerService
{
	List<Customer> findAll();
	Customer findById(Long id);
	void save(Customer customer);
	void delete(Customer customer);
}
