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

	public void addHotel(String hotelName,  double weekdayCustomerCost, double weekendCustomerCost, int rating) {
		
		hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setWeekdayCustomerCost(weekdayCustomerCost);
		hotel.setWeekendCustomerCost(weekendCustomerCost);
		hotel.setRating(rating);
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
		if (!(cheapestPrice == Double.MAX_VALUE) )
		{
        	System.out.println("Cheapest Hotel is =" + cheapestHotel.get(0).getHotelName() + ",Rates= " + cheapestPrice);
        	return cheapestHotel;
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
		System.out.println("Cheapest Best Rated Hotel :" + sortedHotelList.get().getHotelName() + ", Total Rates: " + cheapestPrice);
		return sortedHotelList.get();
	}

	
}


