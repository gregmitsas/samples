package com.gregmitsas.model;

public class PaymentMethod
{
	public PaymentMethod(String description)
	{
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	private String description;
}
