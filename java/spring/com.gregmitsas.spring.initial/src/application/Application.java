package application;

import service.CustomerService;
import service.CustomerServiceImpl;

public class Application
{
	public static void main(String[] args)
	{
		CustomerService service = new CustomerServiceImpl();
		System.out.println(service.get().get(0).getName());
		System.out.println(service.get().get(1).getName());
	}

}
