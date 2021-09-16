package com.bridgelabz.hotelreservationsystem;

import java.time.LocalDate;

public interface HotelReservationIF {
	public void addHotel(String hotelName, double weekdayCustomerCost, double weekendCustomerCost);
	public int getHotelListSize();
	public void printHotelList();
	public Hotel getCheapestHotel(LocalDate startDate, LocalDate endDate);
}
