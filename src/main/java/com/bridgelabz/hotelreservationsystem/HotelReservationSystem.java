package com.bridgelabz.hotelreservationsystem;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.time.DayOfWeek;
import java.util.stream.Collectors;
public class HotelReservationSystem implements HotelReservationIF {
	ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	Hotel hotel;
	double totalRate = 0;

	public void addHotel(String hotelName,  double weekdayCustomerCost, double weekendCustomerCost) {
		
		hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setWeekdayCustomerCost(weekdayCustomerCost);
		hotel.setWeekendCustomerCost(weekendCustomerCost);
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
	public String getCheapestHotel(LocalDate startDate, LocalDate endDate) {

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
        	return cheapestHotel.get(0).getHotelName();
        }
        return null;

	}
	public ArrayList<Hotel> getHotelList(){
		return hotelList;
	}

	
}


