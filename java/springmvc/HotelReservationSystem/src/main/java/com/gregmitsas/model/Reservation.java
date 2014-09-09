package com.gregmitsas.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Reservation
{
	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public String getRoomType()
	{
		return roomType;
	}
	public void setRoomType(String roomType)
	{
		this.roomType = roomType;
	}
	public String getPaymentMethod()
	{
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}
	public String getMealPlan()
	{
		return mealPlan;
	}
	public void setMealPlan(String mealPlan)
	{
		this.mealPlan = mealPlan;
	}
	public Date getCheckInDate()
	{
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate)
	{
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate()
	{
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate)
	{
		this.checkOutDate = checkOutDate;
	}
	public boolean isRefund()
	{
		return refund;
	}
	public void setRefund(boolean refund)
	{
		this.refund = refund;
	}
	public double getTotalCost()
	{
		return totalCost;
	}
	public void setTotalCost(double totalCost)
	{
		this.totalCost = totalCost;
	}
	@Valid
	private Customer customer;
	@NotNull
	private String roomType;
	@NotNull
	private String paymentMethod;
	private String mealPlan;
	@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull @Future
	private Date checkInDate;
	@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull @Future
	private Date checkOutDate;
	private boolean refund;
	private double totalCost;
}
