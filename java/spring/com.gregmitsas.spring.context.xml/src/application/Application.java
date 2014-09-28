package application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.CustomerService;

public class Application
{
	public static void main(String[] args)
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application/application-context.xml");
		
		CustomerService service = context.getBean("customerService", CustomerService.class);
		
		System.out.println(service.get().get(0).getName());
		System.out.println(service.get().get(1).getName());
	}

}
