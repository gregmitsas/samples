package service;

import java.util.List;

import model.Customer;
import dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService
{
	/*public CustomerServiceImpl(CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}*/
	public void setCustomerDao(CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}
	@Override
	public List<Customer> get()
	{
		return customerDao.getAll();
	}
	private CustomerDao customerDao;
}
