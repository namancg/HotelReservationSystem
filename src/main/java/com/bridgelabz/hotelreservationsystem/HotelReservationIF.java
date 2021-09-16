package com.bridgelabz.hotelreservationsystem;

public interface HotelReservationIF {
	public void addHotel(String hotelName, double regularCustomerRate);
	public int getHotelListSize();
	public void printHotelList();
}
