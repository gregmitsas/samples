package com.gregmitsas.service;

public class RefundDiscountPolicy extends DiscountPolicy
{
	public RefundDiscountPolicy(IDiscount discount, Object input)
	{
		super(discount, input);
	}
	@Override
	public int getPercentage()
	{
		if((Boolean) input == false)
		{
			return discount.getPercentage() + 25;
		}
		return discount.getPercentage();
	}
}
