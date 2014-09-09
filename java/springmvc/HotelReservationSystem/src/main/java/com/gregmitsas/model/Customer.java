package com.gregmitsas.model;

import javax.validation.constraints.Size;

public class Customer
{
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getMembership()
	{
		return membership;
	}
	public void setMembership(String membership)
	{
		this.membership = membership;
	}
	@Size(min=1, max=30)
	private String firstName;
	@Size(min=1, max=30)
	private String lastName;
	private String membership;
}