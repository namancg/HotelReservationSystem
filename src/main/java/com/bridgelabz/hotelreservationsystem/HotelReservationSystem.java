package com.bridgelabz.hotelreservationsystem;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.util.stream.Collectors;
public class HotelReservationSystem implements HotelReservationIF {
	ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	Hotel hotel;
	double totalRate = 0;
	public static double cheapestPrice;

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
	public ArrayList<Hotel> getCheapestHotel(LocalDate startDate, LocalDate endDate) {

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
	public ArrayList<Hotel> getHotelList(){
		return hotelList;
	}
public Hotel getCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate)
	{
		ArrayList<Hotel> cheapestHotels = getCheapestHotel(startDate, endDate);
		Optional<Hotel> sortedHotelList = cheapestHotels.stream().max(Comparator.comparing(Hotel::getRating));
		System.out.println("Cheapest and Best Rated Hotel :" + sortedHotelList.get().getHotelName() + ", Total Rates: " + cheapestPrice);
		return sortedHotelList.get();
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

public Hotel getBestRatedHotel(LocalDate startDate, LocalDate endDate) {
	
	ArrayList<Integer> durationDetails = getDurationOfStayDetails(startDate, endDate);
	int weekdaysNumber = durationDetails.get(0);
	int weekendsNumber = durationDetails.get(1);		
	Optional<Hotel> sortedHotelList = hotelList.stream().max(Comparator.comparing(Hotel::getRating));
	double totalPrice = weekdaysNumber*sortedHotelList.get().getWeekdayCustomerCost()+ weekendsNumber*sortedHotelList.get().getWeekendCustomerCost();
	System.out.println("Best Rated Hotel =" + sortedHotelList.get().getHotelName() + ", Rates: " + totalPrice);
	return sortedHotelList.get();
}
	
}


