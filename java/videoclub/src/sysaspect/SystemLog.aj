package sysaspect;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.Main;
import model.Order;
import model.Product;
import model.SystemLogger;
import model.User;
import controller.PersistenceController;
import dao.SystemLoggerDao;
import dao.impl.SystemLoggerDaoImpl;

public aspect SystemLog
{
	/**
	 * Log action after user login
	 */
	pointcut userLoggedInCall():
		call(void controller.LoginController.userLoggedIn());

	after(): userLoggedInCall()
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("user logged in");
		systemLogger.setDetails("email: "
				+ Main.current_user.getEmail().toLowerCase());
		addLog(systemLogger);
	}

	/**
	 * Log action after login fail
	 */
	pointcut loginFailedCall():
		call(void view.LoginView.loginFailed());

	after(): loginFailedCall()
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("login failed");
		systemLogger.setDetails("email: " + " - password: ");
		addLog(systemLogger);
	}

	/**
	 * Log action before user logout
	 */
	pointcut userLoggedOutCall():
		call(void view.ProductView.userLoggedOut());

	before(): userLoggedOutCall()
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("user logged out");
		systemLogger.setDetails("email: "
				+ Main.current_user.getEmail().toLowerCase());
		addLog(systemLogger);
	}

	/**
	 * Log action after movie import
	 */
	pointcut movieAddedCall():
		call(void controller.impl.ManageProductControllerImpl.SubmitListener.updateNotice());

	after(): movieAddedCall()
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("new movie added");
		systemLogger.setDetails("title: " + " - type: ");
		addLog(systemLogger);
	}

	/**
	 * Log action after a movie is modified
	 */
	pointcut movieEditedCall(String title):
		call(void controller.impl.ManageProductControllerImpl.EditListener.updateNotice(String))
		&& args(title);

	after(String title) returning: movieEditedCall(title)
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("movie modified");
		systemLogger.setDetails("title: " + title.toLowerCase() + " - type: ");
		addLog(systemLogger);
	}

	/**
	 * Log action after user import
	 */
	pointcut userAddedCall():
		call(void controller.impl.ManageUserControllerImpl.AddListener.updateNotice());

	after(): userAddedCall()
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("new user added");
		systemLogger.setDetails("email: " + " - rights: ");
		addLog(systemLogger);
	}

	/**
	 * Log action after a user is modified
	 */
	pointcut userEditedCall(String email):
		call(void controller.impl.ManageUserControllerImpl.EditListener.updateNotice(String))
		&& args(email);

	after(String email) returning: movieEditedCall(email)
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("user modified");
		systemLogger
				.setDetails("email: " + email.toLowerCase() + " - rights: ");
		addLog(systemLogger);
	}

	/**
	 * Log action after a movie rent
	 */
	pointcut movieRent(User user, Product product):
		call(model.Order.new(User, Product))
		&& args(user, product);

	after(User user, Product product) returning: movieRent(user, product){
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("movie rented");
		systemLogger.setDetails("email: " + user.getEmail().toLowerCase()
				+ " - movie title: " + product.getTitle().toLowerCase());
		addLog(systemLogger);
	}

	/**
	 * Log action after a movie return
	 */
	pointcut movieReturn(Order order):
		call(void dao.OrderDao.setAsReturned(Order))
		&& args(order);

	after(Order order) returning: movieReturn(order)
	{
		now = new Date();
		systemLogger = new SystemLogger();
		systemLogger.setDatetime(now);
		systemLogger.setAction("movie returned");
		systemLogger.setDetails("email: "
				+ order.getUser().getEmail().toLowerCase() + " - movie title: "
				+ order.getProduct().getTitle().toLowerCase()
				+ " - movie type: "
				+ order.getProduct().getType().toLowerCase());
		addLog(systemLogger);
	}

	/**
	 * Persist a SystemLogger Object
	 */
	public void addLog(SystemLogger systemLogger)
	{
		systemLoggerDao.persist(systemLogger);
	}

	/**
	 * Our Aspect Members
	 */
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private SystemLoggerDao systemLoggerDao = new SystemLoggerDaoImpl(em);
	private SystemLogger systemLogger;
	private Date now;
}
