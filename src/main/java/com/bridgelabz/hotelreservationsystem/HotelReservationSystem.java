package com.bridgelabz.hotelreservationsystem;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.util.stream.Collectors;

import com.bridgelabz.hotelreservationsystem.HotelReservationSystemException.ExceptionType;
public class HotelReservationSystem implements HotelReservationIF {
	ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	Hotel hotel;
	double totalRate = 0;
	public static double cheapestPrice;
	Scanner sc= new Scanner(System.in);

	public void addHotel(String hotelName,  double weekdayCustomerCost, double weekendCustomerCost, int rating, double weekdayRewardCost, double weekendRewardCost) {
		
		hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setWeekdayCustomerCost(weekdayCustomerCost);
		hotel.setWeekendCustomerCost(weekendCustomerCost);
		hotel.setRating(rating);
		hotel.setWeekdayRewardCost(weekdayRewardCost);
		hotel.setWeekendRewardCost(weekendRewardCost);
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
	public ArrayList<Hotel> getCheapestHotel(LocalDate startDate, LocalDate endDate, String customerType) 
	{
		ArrayList<Integer> durationDetails = getDurationOfStayDetails(startDate, endDate);
		int weekdaysNumber = durationDetails.get(0);
		int weekendsNumber = durationDetails.get(1);
		ArrayList<Hotel> cheapestHotel = new ArrayList<Hotel>();

		if (customerType.equalsIgnoreCase("Regular")) {

			cheapestPrice = hotelList.stream()
					.mapToDouble(hotel -> ((hotel.getWeekendCustomerCost() * weekendsNumber)
							+ hotel.getWeekdayCustomerCost() * weekdaysNumber))
					.min().orElse(Double.MAX_VALUE);

			cheapestHotel = hotelList.stream()
					.filter(hotel -> (hotel.getWeekendCustomerCost() * weekendsNumber
							+ hotel.getWeekdayCustomerCost() * weekdaysNumber) == cheapestPrice)
					.collect(Collectors.toCollection(ArrayList::new));
		} 
		else {
			
			cheapestPrice = hotelList.stream()
					.mapToDouble(hotel -> ((hotel.getWeekendCustomerCost() * weekendsNumber)
							+ hotel.getWeekdayCustomerCost() * weekdaysNumber))
					.min().orElse(Double.MAX_VALUE);

			cheapestHotel = hotelList.stream()
					.filter(hotel -> (hotel.getWeekendCustomerCost() * weekendsNumber
							+ hotel.getWeekdayCustomerCost() * weekdaysNumber) == cheapestPrice)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		if (cheapestPrice != Double.MAX_VALUE) {
			Iterator<Hotel> iterator = cheapestHotel.iterator();
			System.out.println("Cheap Hotels : \n");
			while (iterator.hasNext()) {
				System.out.println(iterator.next().getHotelName() + ", Total Rates: " + cheapestPrice);
			}
			return cheapestHotel;
		}
		return null;
		
		
	}
	public ArrayList<Hotel> getHotelList(){
		return hotelList;
	}
public Hotel getCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate, String customerType)
	{
	
	try {
		if(customerType.length() == 0)
			throw new HotelReservationSystemException(ExceptionType.ENTERED_EMPTY, "EMPTY Value Entered");
		ArrayList<Hotel> cheapestHotels = getCheapestHotel(startDate, endDate ,customerType);
		Optional<Hotel> sortedHotelList = cheapestHotels.stream().max(Comparator.comparing(Hotel::getRating));
		System.out.println("Cheapest and Best Rated Hotel :" + sortedHotelList.get().getHotelName() + ", Total Rates: " + cheapestPrice);
		return sortedHotelList.get();
	}
	catch(NullPointerException e) {
		throw new HotelReservationSystemException(ExceptionType.ENTERED_NULL, "NULL Value Entered");
	}
	}
private ArrayList<Integer> getDurationOfStayDetails(LocalDate startDate, LocalDate endDate) {
	int numberOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
    int weekends = 0;
    ArrayList<Integer> durationDetails = new ArrayList<Integer>();
    
	while (startDate.compareTo(endDate) != 0) {
        switch (DayOfWeek.of(startDate.get(ChronoField.DAY_OF_WEEK))) {
            case SATURDAY:
                ++weekends;
                break;
            case SUNDAY:
                ++weekends;
                break;
        }
        startDate = startDate.plusDays(1);
    }
	int weekdays = numberOfDays - weekends;
	durationDetails.add(weekdays);
	durationDetails.add(weekends);
	return durationDetails;
	
}	

public Hotel getBestRatedHotel(LocalDate startDate, LocalDate endDate ,String customerType) 
{
	try {
		if(customerType.length() == 0)
			throw new HotelReservationSystemException(ExceptionType.ENTERED_EMPTY, "EMPTY Value Entered");
	ArrayList<Integer> durationDetails = getDurationOfStayDetails(startDate, endDate);
	int weekdaysNumber = durationDetails.get(0);
	int weekendsNumber = durationDetails.get(1);		
	Optional<Hotel> sortedHotelList = hotelList.stream().max(Comparator.comparing(Hotel::getRating));
	double totalPrice = 0;
	if(customerType.equalsIgnoreCase("Regular")) {
		
		totalPrice = weekdaysNumber * sortedHotelList.get().getWeekdayCustomerCost()
				+ weekendsNumber * sortedHotelList.get().getWeekendCustomerCost();
	}
	else if(customerType.equalsIgnoreCase("Reward")){
		
		totalPrice = weekdaysNumber * sortedHotelList.get().getWeekdayRewardCost()
				+ weekendsNumber * sortedHotelList.get().getWeekendRewardCost();
	}
	
	
	System.out.println("Best Rated Hotel =" + sortedHotelList.get().getHotelName() + ", Rating : "
			+ sortedHotelList.get().getRating() + ", Total Rates: " + totalPrice);
	return sortedHotelList.get();
}
catch(NullPointerException e) {
	throw new HotelReservationSystemException(ExceptionType.ENTERED_NULL, "NULL Value Entered");
}
}
public String getDates()
{
	System.out.println("Enter Date in YYYY-MM-DD format = ");
	String date = sc.next();
	boolean isValid = validateDate(date);
	if(isValid)
		return date;
	return null;
}

public boolean validateDate(String date) {
	
	try {
		if(date.length() == 0)
			throw new HotelReservationSystemException(ExceptionType.ENTERED_EMPTY, "EMPTY value entered");
		
		String dateRegEx = "^([0-9]{4})[\\-]((0[1-9])|1[012])[\\-]([012][0-9]|[3][01])$";
		return date.matches(dateRegEx);
	}
	catch(NullPointerException e) 
	{
		throw new HotelReservationSystemException(ExceptionType.ENTERED_NULL, "NULL value entered");
	}
	
	
}
	
}


