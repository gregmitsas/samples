package com.gregmitsas.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.gregmitsas.model.Customer;
import com.gregmitsas.model.Reservation;

public class ReservationServiceImplTest
{
	private ReservationService reservationService;
	
	@Before
	public void setup()
	{
		reservationService = new ReservationServiceImpl();
	}
	
	@Test
	public void testComputeTotalCost() throws Exception
	{
		Customer customer = new Customer();
		customer.setFirstName("greg");
		customer.setLastName("gerg");
		customer.setMembership("silver");
		
		Reservation reservation = new Reservation();
		reservation.setCustomer(customer);
		reservation.setRoomType("triple");
		reservation.setMealPlan("half-board");
		reservation.setPaymentMethod("credit card");
		reservation.setCheckInDate(createDate("01/01/2015"));
		reservation.setCheckOutDate(createDate("01/02/2015"));
		reservation.setRefund(false);
		
		assertEquals(1087.325, reservationService.computeTotalCost(reservation), 0.001);
	}
	
	@Test
	public void testComputeCharge() throws Exception
	{
		Reservation reservation = new Reservation();
		reservation.setRoomType("suite");
		reservation.setMealPlan("half-board");
		
		assertEquals(67.5, reservationService.computeCharge(reservation), 0.001);
	}
	
	@Test
	public void testComputeDiscountCharge() throws Exception
	{
		assertEquals(4.33125, reservationService.computeDiscountCharge(19.6875, 22), 0.001);
	}
	
	@Test
	public void testComputeDays() throws Exception
	{
		Reservation reservation = new Reservation();
		reservation.setCheckInDate(createDate("15/02/15"));
		reservation.setCheckOutDate(createDate("15/03/15"));
		
		assertEquals(28, reservationService.computeDays(reservation), 0);
	}
	
	private Date createDate(String strDate) throws Exception
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(strDate);
	}
}
