package com.gregmitsas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gregmitsas.service.CustomerService;

@Controller
public class CustomerController
{
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllCustomers()
	{
		return "customer/all";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getCustomerDetails(@PathVariable Long id)
	{
		return "customer/details";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCustomer()
	{
		return "customer/add";
	}
	
	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
}
