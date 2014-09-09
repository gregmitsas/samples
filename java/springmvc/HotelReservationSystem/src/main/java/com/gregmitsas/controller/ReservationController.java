package com.gregmitsas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gregmitsas.model.MealPlan;
import com.gregmitsas.model.PaymentMethod;
import com.gregmitsas.model.Reservation;
import com.gregmitsas.model.RoomType;
import com.gregmitsas.service.ReservationService;

@Controller
@SessionAttributes("reservation")
public class ReservationController
{
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value="/roomType", method = RequestMethod.GET)
	public @ResponseBody List<RoomType> generateRoomTypes()
	{
		return reservationService.generateRoomTypes();
	}
	
	@RequestMapping(value="/paymentMethod", method = RequestMethod.GET)
	public @ResponseBody List<PaymentMethod> generatePaymentMethods()
	{
		return reservationService.generatePaymentMethods();
	}
	
	@RequestMapping(value="/mealPlan", method = RequestMethod.GET)
	public @ResponseBody List<MealPlan> generateMealPlans()
	{
		return reservationService.generateMealPlans();
	}
	
	@RequestMapping(value="/addReservation", method = RequestMethod.GET)
	public String addReservation(Model model)
	{
		model.addAttribute("reservation", new Reservation());
		return "reservation/add";
	}
	
	@RequestMapping(value="/addReservation", method = RequestMethod.POST)
	public String updateReservation(@Valid @ModelAttribute ("reservation") Reservation reservation, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "reservation/add";
		}
		reservationService.setTotalCost(reservation);
		return "redirect:reservationDetails.html";
	}
	
	@RequestMapping(value="/reservationDetails")
	public String reservationDetails()
	{
		return "reservation/details";
	}
}
