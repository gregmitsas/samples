package com.gregmitsas.service;

public abstract class ChargePolicy implements ICharge
{
	public ChargePolicy(ICharge charge, Object input)
	{
		this.charge = charge;
		this.input = input;
	}
	public double getCost()
	{
		return charge.getCost();
	}
	protected ICharge charge;
	protected Object input;
}
