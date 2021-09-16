package com.bridgelabz.hotelreservationsystem;

import java.time.LocalDate;
import java.util.ArrayList;

public interface HotelReservationIF {
	public void addHotel(String hotelName, double weekdayCustomerCost, double weekendCustomerCost, int rating, double weekdayRewardCost, double weekendRewardCost);
	public int getHotelListSize();
	public void printHotelList();
	public ArrayList<Hotel> getCheapestHotel(LocalDate startDate, LocalDate endDate);
	public ArrayList<Hotel> getHotelList();
	public Hotel getCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate);
	public Hotel getBestRatedHotel(LocalDate startDate, LocalDate endDate);
}
