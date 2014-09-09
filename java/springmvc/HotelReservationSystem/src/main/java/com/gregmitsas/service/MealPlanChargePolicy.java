package com.gregmitsas.service;

public class MealPlanChargePolicy extends ChargePolicy
{
	public MealPlanChargePolicy(ICharge charge, Object input)
	{
		super(charge, input);
	}
	@Override
	public double getCost()
	{
		String strInput = (String) input;
		
		if(strInput.equalsIgnoreCase("all-inclusive"))
		{
			return charge.getCost() + 20.0;
		}
		else if(strInput.equalsIgnoreCase("full-board"))
		{
			return charge.getCost() + 15.0;
		}
		else if(strInput.equalsIgnoreCase("half-board"))
		{
			return charge.getCost() + 10.0;
		}
		
		return charge.getCost();
	}
}
