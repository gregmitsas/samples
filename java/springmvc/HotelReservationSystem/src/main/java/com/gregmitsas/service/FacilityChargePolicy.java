package com.gregmitsas.service;

public class FacilityChargePolicy extends ChargePolicy
{
	public FacilityChargePolicy(ICharge charge, Object input)
	{
		super(charge, input);
	}
	@Override
	public double getCost()
	{
		return charge.getCost();
	}
}
