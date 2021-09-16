package com.bridgelabz.hotelreservationsystem;

import java.time.LocalDate;
import java.util.ArrayList;

public interface HotelReservationIF {
	public void addHotel(String hotelName, double weekdayCustomerCost, double weekendCustomerCost);
	public int getHotelListSize();
	public void printHotelList();
	public String getCheapestHotel(LocalDate startDate, LocalDate endDate);
	public ArrayList<Hotel> getHotelList();
}
