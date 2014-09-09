package com.gregmitsas.service;

public class DiscountFactory
{
	public static DiscountFactory getInstance()
	{
		return discountFactory;
	}

	public IDiscount getDiscountType(String input)
	{
		if (input.equalsIgnoreCase("golden"))
		{
			return new GoldenMembershipDiscount();
		}
		else if (input.equalsIgnoreCase("silver"))
		{
			return new SilverMembershipDiscount();
		}
		else if (input.equalsIgnoreCase("bronze"))
		{
			return new BronzeMembershipDiscount();
		}
		return new RegularMembershipDiscount();
	}

	private static DiscountFactory discountFactory = new DiscountFactory();
	private DiscountFactory(){}
}
