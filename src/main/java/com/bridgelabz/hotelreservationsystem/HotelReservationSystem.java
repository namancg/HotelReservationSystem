package com.bridgelabz.hotelreservationsystem;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class HotelReservationSystem implements HotelReservationIF {
	ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	Hotel hotel;

	public void addHotel(String hotelName,  double weekdayCustomerCost, double weekendCustomerCost) {
		
		hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setWeekdayCustomerCost(weekdayCustomerCost);
		hotel.setWeekendCustomerCost(weekendCustomerCost);
		hotelList.add(hotel);	
	}

	@Override
	public int getHotelListSize() {
		return hotelList.size();
	}

	@Override
	public void printHotelList() {
		System.out.println(hotelList);
		
	}
	public Hotel getCheapestHotel(LocalDate startDate, LocalDate endDate) {

		long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
		Optional<Hotel> sortedHotelList = hotelList.stream().min(Comparator.comparing(Hotel::getRegularCustomerCost));
		return sortedHotelList.get();
	}

	
}


