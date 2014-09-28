package service;

import java.util.List;

import model.Customer;
import dao.CustomerDao;
import dao.HibernateCustomerDaoImpl;

public class CustomerServiceImpl implements CustomerService
{
	public CustomerServiceImpl()
	{
		customerDao = new HibernateCustomerDaoImpl();
	}
	@Override
	public List<Customer> get()
	{
		return customerDao.getAll();
	}
	private CustomerDao customerDao;
}
