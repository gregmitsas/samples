package com.gregmitsas.service;

public class PreOrderDiscountPolicy extends DiscountPolicy
{
	public PreOrderDiscountPolicy(IDiscount discount, Object input)
	{
		super(discount, input);
	}
	@Override
	public int getPercentage()
	{
		int months = (Integer) input;
		
		if(months < 1)
		{
			return discount.getPercentage();
		}
		
		int percentage = 0;
		for(int i=0; i<months; i++)
		{
			percentage += 2;
		}
		return discount.getPercentage() + percentage;
	}
}
