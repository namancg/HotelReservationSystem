package com.bridgelabz.hotelreservationsystem;
import java.util.*;
public class HotelReservationSystem implements HotelReservationIF {
	ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	Hotel hotel;

	public void addHotel(String hotelName,  double regularCustomerRate) {
		
		hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setRegularCustomerCost(regularCustomerRate);
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
}


