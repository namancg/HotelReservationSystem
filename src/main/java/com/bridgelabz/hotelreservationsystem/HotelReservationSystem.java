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
	public ArrayList<Hotel> getCheapestHotel(LocalDate startDate, LocalDate endDate) 
	{
		try {

		int numberOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
        int weekends = 0;
        
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
		int weekdayNumber = numberOfDays - weekends;
		int weekendNumber = weekends;
		final double cheapestPrice = hotelList.stream()
				.mapToDouble(hotel -> ((hotel.getWeekendCustomerCost()*weekendNumber) + hotel.getWeekdayCustomerCost()*weekdayNumber))
				.min()
				.orElse(Double.MAX_VALUE);
				ArrayList<Hotel> cheapestHotel = hotelList.stream()
				.filter(hotel -> (hotel.getWeekendCustomerCost()*weekendNumber + hotel.getWeekdayCustomerCost()*weekdayNumber) == cheapestPrice)
				.collect(Collectors.toCollection(ArrayList::new));
		if (!(cheapestPrice == Double.MAX_VALUE) ) {
			Iterator<Hotel> iterator = cheapestHotel.iterator();
			while(iterator.hasNext()) {
        		System.out.println("Cheapest Hotel is = " + iterator.next().getHotelName() + ", Rates: " + cheapestPrice);
        		return cheapestHotel;
        	}
	}
		return null;
	}
		catch(NullPointerException e) {
			throw new HotelReservationSystemException(ExceptionType.ENTERED_NULL, "NULL Value Entered");
		}	
	}
	public ArrayList<Hotel> getHotelList(){
		return hotelList;
	}
public Hotel getCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate)
	{
	try {
		ArrayList<Hotel> cheapestHotels = getCheapestHotel(startDate, endDate);
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

public Hotel getBestRatedHotel(LocalDate startDate, LocalDate endDate) 
{
	try {
	ArrayList<Integer> durationDetails = getDurationOfStayDetails(startDate, endDate);
	int weekdaysNumber = durationDetails.get(0);
	int weekendsNumber = durationDetails.get(1);		
	Optional<Hotel> sortedHotelList = hotelList.stream().max(Comparator.comparing(Hotel::getRating));
	double totalPrice = weekdaysNumber*sortedHotelList.get().getWeekdayCustomerCost()+ weekendsNumber*sortedHotelList.get().getWeekendCustomerCost();
	System.out.println("Best Rated Hotel =" + sortedHotelList.get().getHotelName() + ", Rates: " + totalPrice);
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


