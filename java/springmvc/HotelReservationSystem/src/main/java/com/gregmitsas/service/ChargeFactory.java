package com.gregmitsas.service;

public class ChargeFactory
{
	public static ChargeFactory getInstance()
	{
		return chargeFactory;
	}

	public ICharge getChargeType(String input)
	{
		if (input.equalsIgnoreCase("double"))
		{
			return new DoubleRoomCharge();
		}
		else if (input.equalsIgnoreCase("triple"))
		{
			return new TripleRoomCharge();
		}
		else if (input.equalsIgnoreCase("quad"))
		{
			return new QuadRoomCharge();
		}
		else if (input.equalsIgnoreCase("suite"))
		{
			return new SuiteRoomCharge();
		}
		return null;
	}

	private static ChargeFactory chargeFactory = new ChargeFactory();
	private ChargeFactory(){}
}
