package com.gregmitsas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gregmitsas.model.Customer;
import com.gregmitsas.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService
{
	@Transactional
	public List<Customer> findAll()
	{
		return customerRepository.findAll();
	}

	@Transactional
	public Customer findById(Long id)
	{
		return customerRepository.findOne(id);
	}

	@Transactional
	public void save(Customer customer)
	{
		customerRepository.save(customer);
	}

	@Transactional
	public void delete(Customer customer)
	{
		customerRepository.delete(customer);
	}
	
	@Autowired
	@Qualifier("customerRepository")
	private CustomerRepository customerRepository;
}
