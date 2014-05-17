package service;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import controller.CustomerController;

public class CustomerService
{
	
	public List<String> getCustomers()
	{
		final List<Customer> customers = customerController.get();
		names = new ArrayList<String>();
		for(Customer c : customers)
		{
			names.add(c.getFirstName() + ", " + c.getLastName());
		}
		return names;
	}
	
	public String getCustomer(int id)
	{
		final Customer customer = customerController.get(id);
		name = customer.getFirstName() + ", " + customer.getLastName();
		return name;
	}
	
	private final CustomerController customerController = new CustomerController();
	private List<String> names = null;
	private String name = "";
}