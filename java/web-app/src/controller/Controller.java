package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dao.CarDao;
import dao.UserDao;
import dao.impl.CarDaoImpl;
import dao.impl.UserDaoImpl;
import database.utils.DatabaseUtils;

@WebServlet("/Controller")
public class Controller extends HttpServlet
{

	public Controller()
	{
		super();
		DatabaseUtils.setUpDatabase();
		userDao = new UserDaoImpl();
		carDao = new CarDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		if (request.getParameter("loginSubmit") != null)
		{
			if (userDao.checkLogin(request.getParameter("email"), request.getParameter("password")) == null)
			{
				setAddress("loginError.jsp");
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("password", request.getParameter("password"));
			}
			else
			{
				setAddress("menu.jsp");
				controllerWelcomeUser(request);
				request.setAttribute("controllerCarList", carDao.getAllCars());
			}
		}

		if (request.getParameter("register") != null)
		{
			setAddress("register.jsp");
		}

		if (request.getParameter("proceed") != null)
		{
			if (request.getParameter("carRadio") == null || request.getParameter("carRadio") == "")
			{
				setAddress("menuError.jsp");
				controllerWelcomeUser(request);
				request.setAttribute("controllerCarList", carDao.getAllCars());
				request.setAttribute("days", request.getParameter("days"));
			}
			else
			{
				setAddress("checkout.jsp");
				setCarId(request.getParameter("carRadio"));
				request.setAttribute("days", request.getParameter("days"));
				request.setAttribute("car", carDao.getCarById(carId));
				request.setAttribute("cost", carDao.getCarById(carId).getPricePerDay());
			}
		}

		if (request.getParameter("logOut") != null)
		{
			setAddress("login.jsp");
		}

		if (request.getParameter("registerSubmit") != null)
		{
			checkControllerRegisterUser(request);
		}

		RequestDispatcher rd = request.getRequestDispatcher(address);
		rd.forward(request, response);
	}

	private void setAddress(String address)
	{
		this.address = address;
	}

	private void setCarId(String radioLabel)
	{
		if (radioLabel.equalsIgnoreCase("radioc3"))
		{
			this.carId = 0;
		}
		else if (radioLabel.equalsIgnoreCase("yarisradio"))
		{
			this.carId = 1;
		}
		else if (radioLabel.equalsIgnoreCase("mazdaradio"))
		{
			this.carId = 2;
		}
	}

	private void controllerRegisterUser(HttpServletRequest request)	throws ServletException, IOException
	{
		final User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		userDao.addUser(user);
		setAddress("login.jsp");
	}

	private void checkControllerRegisterUser(HttpServletRequest request) throws ServletException, IOException
	{
		if (request.getParameter("firstName").equalsIgnoreCase("")
				|| request.getParameter("lastName").equalsIgnoreCase("")
				|| request.getParameter("phone").equalsIgnoreCase("")
				|| request.getParameter("email").equalsIgnoreCase("")
				|| request.getParameter("password").equalsIgnoreCase("")
				|| request.getParameter("rePassword").equalsIgnoreCase(""))
		{
			setAddress("registerError.jsp");
			request.setAttribute("firstName", request.getParameter("firstName"));
			request.setAttribute("lastName", request.getParameter("lastName"));
			request.setAttribute("phone", request.getParameter("phone"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("rePassword", request.getParameter("rePassword"));
		}
		else
		{
			controllerRegisterUser(request);
		}
	}

	private void controllerWelcomeUser(HttpServletRequest request) throws ServletException, IOException
	{
		User user = userDao.getUserByEmail(request.getParameter("email"));
		request.setAttribute("user", user);
	}
	
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private CarDao carDao;
	private String address;
	private int carId;
}
