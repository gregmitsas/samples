package com.gregmitsas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gregmitsas.model.MealPlan;
import com.gregmitsas.model.PaymentMethod;
import com.gregmitsas.model.Reservation;
import com.gregmitsas.model.RoomType;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService
{

	public List<RoomType> generateRoomTypes()
	{
		List<RoomType> roomTypes = new ArrayList<RoomType>();
		roomTypes.add(new RoomType("Double"));
		roomTypes.add(new RoomType("Triple"));
		roomTypes.add(new RoomType("Quad"));
		roomTypes.add(new RoomType("Suite"));
		
		return roomTypes;
	}

	public List<PaymentMethod> generatePaymentMethods()
	{
		List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
		paymentMethods.add(new PaymentMethod("Credit Card"));
		paymentMethods.add(new PaymentMethod("Deposit"));
		paymentMethods.add(new PaymentMethod("Cash"));
		
		return paymentMethods;
	}
	
	public List<MealPlan> generateMealPlans()
	{
		List<MealPlan> mealPlans = new ArrayList<MealPlan>();
		mealPlans.add(new MealPlan("Half-Board"));
		mealPlans.add(new MealPlan("Full-Board"));
		mealPlans.add(new MealPlan("All-Inclusive"));
		
		return mealPlans;
	}
	
	public void setTotalCost(Reservation reservation)
	{
		double totalCost = computeTotalCost(reservation);
		reservation.setTotalCost(totalCost);
	}
	
	public double computeTotalCost(Reservation reservation)
	{
		int discount = computeDiscount(reservation);
		double charge = computeCharge(reservation);
		double discountCharge = computeDiscountCharge(charge, discount);
		double totalCharge = charge - discountCharge;
		double days = computeDays(reservation);
		double totalCost = totalCharge * days;
		
		return totalCost;
	}
	
	public int computeDiscount(Reservation reservation)
	{
		reservation.getCustomer().setMembership("silver"); // fixed value - to be removed
		IDiscount discount = DiscountFactory.getInstance().getDiscountType(reservation.getCustomer().getMembership());
		discount = new PreOrderDiscountPolicy(discount, 2); // fixed value - to be changed
		discount = new RefundDiscountPolicy(discount, reservation.isRefund());
		return discount.getPercentage();
	}
	
	public double computeCharge(Reservation reservation)
	{
		ICharge charge = ChargeFactory.getInstance().getChargeType(reservation.getRoomType());
		charge = new MealPlanChargePolicy(charge, reservation.getMealPlan());
		charge = new LessRoomsChargePolicy(charge, 5); // fixed value - to be changed
		return charge.getCost();
	}
	
	public double computeDiscountCharge(double charge, int discount)
	{
		return charge * ((double) discount/100.0);
	}
	
	public int computeDays(Reservation reservation)
	{
		long checkInDateInTime = reservation.getCheckInDate().getTime();
		long checkOutDateInTime = reservation.getCheckOutDate().getTime();
		Long totalDays = new Long(checkOutDateInTime - checkInDateInTime) / (1000 * 60 * 60 * 24);
		return totalDays.intValue();
	}

}
