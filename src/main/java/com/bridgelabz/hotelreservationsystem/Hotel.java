package com.bridgelabz.hotelreservationsystem;

public class Hotel {
	private String hotelName;
	private double regularCustomerCost;
	public Hotel() {
	}
	public Hotel(String hotelName, int rating, double regularCustomerCost) {
		this.hotelName = hotelName;
		this.regularCustomerCost = regularCustomerCost;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public double getRegularCustomerCost() {
		return regularCustomerCost;
	}
	public void setRegularCustomerCost(double regularCustomerCost) {
		this.regularCustomerCost = regularCustomerCost;
	}
	@Override
	public String toString() {
		return "Hotels [hotelName=" + hotelName + ",Regular Customer Cost="+ regularCustomerCost +"]\n";
	}
}
