package dao;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class HibernateCustomerDaoImpl implements CustomerDao
{
	public HibernateCustomerDaoImpl()
	{
	}
	@Override
	public List<Customer> getAll()
	{
		Customer c1 = new Customer();
		c1.setId(1);
		c1.setName("greg");
		
		Customer c2 = new Customer();
		c2.setId(2);
		c2.setName("gerg");
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(c1);
		customers.add(c2);
		
		return customers;
	}
}
