package model;

import java.util.ArrayList;

import javax.jdo.annotations.Index;
import javax.jdo.annotations.Unique;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User
{

	@Id
	@GeneratedValue
	private long id;

	@Index
	@Unique
	@Basic(optional = false)
	private String username;

	@Unique
	@Basic(optional = false)
	private String password;

	@ManyToOne
	private Profile profile;

	@Basic(optional = false)
	private String name;

	@Unique
	private String email;

	@Basic(optional = false)
	private String phone;

	@SuppressWarnings("unused")
	private @OneToMany(mappedBy = "user")
	ArrayList<Order> orders;

	// Empty constructor
	public User()
	{
	}

	// Constructor
	public User(String username, String password, Profile profile, String name,
			String email, String phone)
	{
		this.username = username;
		this.password = password;
		this.profile = profile;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public long getId()
	{
		return id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Profile getProfile()
	{
		return profile;
	}

	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

}
