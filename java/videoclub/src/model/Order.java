package model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

@Entity
public class Order
{
	@Id
	@GeneratedValue
	private long order_number;

	@Basic(optional = false)
	private @ManyToOne
	User user;

	@Basic(optional = false)
	private @ManyToOne
	Product product;

	@Basic(optional = false)
	private Date order_date;

	@Basic(optional = false)
	private Date return_date;

	@Basic(optional = false)
	private boolean returned;

	// Empty constructor
	public Order()
	{
	}

	// Constructor #2
	public Order(User user, Product product)
	{
		this.product = product;
		this.setUser(user);
		Date now = new Date();
		this.setOrderDate(now); // Current system date and time is set as
		DateTime rDate = new DateTime(now);
		LocalDate returnDate = rDate.toLocalDate().plusDays(2);
		this.setReturnDate(returnDate.toDate());
		this.returned = false;
	}

	public long getOrder_number()
	{
		return order_number;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Product getProduct()
	{
		return this.product;
	}

	public void setProduct(Product p)
	{
		this.product = p;
	}

	public Date getOrderDate()
	{
		return order_date;
	}

	public void setOrderDate(Date orderDate)
	{
		this.order_date = orderDate;
	}

	public Date getReturnDate()
	{
		return return_date;
	}

	public void setReturnDate(Date returnDate)
	{
		this.return_date = returnDate;
	}

	public boolean getReturned()
	{
		return returned;
	}

	public void setReturned(boolean isReturned)
	{
		this.returned = isReturned;
	}

}