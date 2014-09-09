package com.gregmitsas.service;

public abstract class DiscountPolicy implements IDiscount
{
	public DiscountPolicy(IDiscount discount, Object input)
	{
		this.discount = discount;
		this.input = input;
	}
	public int getPercentage()
	{
		return discount.getPercentage();
	}
	protected IDiscount discount;
	protected Object input;
}
