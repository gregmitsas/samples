package com.gregmitsas.service;

public class CapChargePolicy extends ChargePolicy
{
	public CapChargePolicy(ICharge charge, Object input)
	{
		super(charge, input);
	}
	@Override
	public double getCost()
	{
		double currentCost = charge.getCost();
		String strInput = (String) input;
		double halfPrice = ChargeFactory.getInstance().getChargeType(strInput).getCost() * 0.5;
		if(currentCost < halfPrice)
		{
			return halfPrice;
		}
		return currentCost;
	}
}
