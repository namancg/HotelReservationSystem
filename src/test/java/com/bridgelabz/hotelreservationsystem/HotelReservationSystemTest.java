package com.bridgelabz.hotelreservationsystem;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
public class HotelReservationSystemTest {
	@Test
	public void givenHotelDetails_WhenValuesEnteredAreCorrect_ShoulReturnTrue()
	{

		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood",110,90,3,80,80);
		hotelReservation.addHotel("Bridgewood",150,50,4,110,50);
		hotelReservation.addHotel("Ridgewood",220,150,5,100,40);
		int hotelListSize = hotelReservation.getHotelListSize();
		hotelReservation.printHotelList();
		Assert.assertEquals(3, hotelListSize);
	}
	@Test
	public void givenHotelDetails_shouldReturnCheapestHotel(){
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood",110,90,3,80,80);
		hotelReservation.addHotel("Bridgewood",150,50,4,110,50);
		LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);    
		LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);    
		ArrayList<Hotel> hotel = hotelReservation.getCheapestHotel(startDate, endDate);
		String hotelName = hotel.get(0).getHotelName()+","+hotel.get(1).getHotelName();
		Assert.assertEquals("Lakewood,Bridgewood", hotelName);
	}
	@Test
	public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekdayRegularCustomerCost(){
		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Bridgewood", 150, 50, 4,110,50);
		int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekdayCustomerCost();
		Assert.assertEquals(150, hotelRegularCustomerCost);
	}
	
	@Test
	public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekendRegularCustomerCost(){
		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Bridgewood", 150, 50, 4,110,50);
		int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekendCustomerCost();
		Assert.assertEquals(50, hotelRegularCustomerCost);
	}
	@Test
	public void givenHotelDetails_WhenHotelPricesAreSame_shouldReturnHighestRatedHotel(){
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood",  110, 90,3,80,80);
		hotelReservation.addHotel("Bridgewood", 150, 50,4,110,50);
		LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);    
		LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);    
		Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate);
		Assert.assertEquals("Bridgewood", hotel.getHotelName());
	}
	@Test
	public void givenHotelDetails_shouldReturnHighestRatedHotel(){
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood", 3, 110, 90,80,80);
		hotelReservation.addHotel("Bridgewood", 4, 150, 50,110,50);
		hotelReservation.addHotel("Ridgewood", 5, 220, 150,100,40);
		LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);    
		LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);    
		Hotel hotel = hotelReservation.getBestRatedHotel(startDate, endDate);
		Assert.assertEquals("Ridgewood", hotel.getHotelName());
	}

	
}
