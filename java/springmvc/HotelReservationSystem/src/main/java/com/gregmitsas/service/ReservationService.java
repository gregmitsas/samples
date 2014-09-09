package com.gregmitsas.service;

import java.util.List;

import com.gregmitsas.model.MealPlan;
import com.gregmitsas.model.PaymentMethod;
import com.gregmitsas.model.Reservation;
import com.gregmitsas.model.RoomType;

public interface ReservationService
{
	List<RoomType> generateRoomTypes();
	List<PaymentMethod> generatePaymentMethods();
	List<MealPlan> generateMealPlans();
	void setTotalCost(Reservation reservation);
	double computeTotalCost(Reservation reservation);
	int computeDiscount(Reservation reservation);
	double computeCharge(Reservation reservation);
	double computeDiscountCharge(double charge, int discount);
	int computeDays(Reservation reservation);
}
