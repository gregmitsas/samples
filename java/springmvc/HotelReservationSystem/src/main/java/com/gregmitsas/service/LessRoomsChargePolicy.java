package com.gregmitsas.service;

public class LessRoomsChargePolicy extends ChargePolicy
{
	public LessRoomsChargePolicy(ICharge charge, Object input)
	{
		super(charge, input);
	}
	@Override
	public double getCost()
	{
		int totalRooms = 50;
		int currentRooms = (Integer) input;
		double additionalCharge = 0.0;
		for(int i=totalRooms; i>currentRooms; --i)
		{
			additionalCharge += 0.5;
		}
		return charge.getCost() + additionalCharge;
	}
}
