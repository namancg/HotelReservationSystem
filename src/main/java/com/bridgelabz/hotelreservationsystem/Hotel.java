package com.bridgelabz.hotelreservationsystem;

public class Hotel {
	private String hotelName;
	private double regularCustomerCost;
	double weekdayCustomerCost;
	double weekendCustomerCost;
	private int rating;
	public Hotel() {
	}
	public Hotel(String hotelName, int rating, double regularCustomerCost) {
		this.hotelName = hotelName;
		this.regularCustomerCost = regularCustomerCost;
		this.rating=rating;
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
	public double getWeekdayCustomerCost() {
		
		return weekdayCustomerCost;
	}
	
	public void setWeekdayCustomerCost(double weekdayCustomerCost) {
		this.weekdayCustomerCost = weekdayCustomerCost;
	}
	
	public double getWeekendCustomerCost() {
		return weekendCustomerCost;
	}
	
	public void setWeekendCustomerCost(double weekendCustomerCost) {
		this.weekendCustomerCost = weekendCustomerCost;
	}
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "hotelName=" + hotelName + "Customer WeekDay Cost="+ weekdayCustomerCost+ "Customer WeekDay Cost="+ weekdayCustomerCost+"Rating="+rating;
	}
}
