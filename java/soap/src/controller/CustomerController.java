package controller;

import java.util.List;

import model.Customer;
import model.utils.CustomerUtils;

public class CustomerController
{
	public List<Customer> get()
	{
		return customerUtilsInstance.getCustomers();
	}

	public Customer get(int id)
	{ 
		return customerUtilsInstance.getCustomerById(id);
	}
	
	private CustomerUtils customerUtilsInstance = CustomerUtils.getInstance();
}
