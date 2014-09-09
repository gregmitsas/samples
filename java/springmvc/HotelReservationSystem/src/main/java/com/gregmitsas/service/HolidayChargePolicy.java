package com.gregmitsas.service;


public class HolidayChargePolicy extends ChargePolicy
{
	public HolidayChargePolicy(ICharge charge, Object input)
	{
		super(charge, input);
	}
	@Override
	public double getCost()
	{		
		double currentCharge = charge.getCost();
		if(((String) input).equalsIgnoreCase("christmas"))
		{
			return currentCharge * 1.1;
		}
		else if(((String) input).equalsIgnoreCase("easter"))
		{
			return currentCharge * 1.2;
		}
		else if(((String) input).equalsIgnoreCase("summer"))
		{
			return currentCharge * 1.3;
		}
		return currentCharge;
	}
}
