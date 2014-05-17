package model;

import java.util.Date;

import javax.jdo.annotations.Index;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Index
	@Basic(optional = false)
	private String title;

	@Index
	@Basic(optional = false)
	private String genre;

	@Index
	private String rating;

	@Basic(optional = false)
	private String type;

	@Basic(optional = false)
	private boolean availability;

	private int year;
	private String description;
	private Date create_date;
	private Date edit_date;

	public Product()
	{
		this.availability = true;
		Date now = new Date();
		this.create_date = now;
		this.edit_date = now;
	}

	public long getId()
	{
		return id;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setRating(String string)
	{
		this.rating = string;
	}

	public String getRating()
	{
		return rating;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getYear()
	{
		return year;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public void setAvailability(boolean availability)
	{
		this.availability = availability;
	}

	public boolean getAvailability()
	{
		return availability;
	}

	public void setCreateDate(Date create_date)
	{
		this.create_date = create_date;
	}

	public Date getCreateDate()
	{
		return create_date;
	}

	public void setEditDate(Date edit_date)
	{
		this.edit_date = edit_date;
	}

	public Date getEditDate()
	{
		return edit_date;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
