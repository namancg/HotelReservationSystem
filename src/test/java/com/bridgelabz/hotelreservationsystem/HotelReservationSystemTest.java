package com.bridgelabz.hotelreservationsystem;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
public class HotelReservationSystemTest {
	@Test
	public void givenHotelDetails_WhenValuesEnteredAreCorrect_ShoulReturnTrue()
	{

		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood",  110, 70);
		hotelReservation.addHotel("Bridgewood", 160, 50);
		hotelReservation.addHotel("Ridgewood", 220,90);
		int hotelListSize = hotelReservation.getHotelListSize();
		hotelReservation.printHotelList();
		Assert.assertEquals(3, hotelListSize);
	}
	@Test
	public void givenHotelDetails_shouldReturnCheapestHotel(){
		
		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood", 210,90);
		hotelReservation.addHotel("Bridgewood", 260,87);
		LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 22);    
		LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 23);    
		String hotel = hotelReservation.getCheapestHotel(startDate, endDate);
		Assert.assertEquals("Lakewood", hotel);
	}
	@Test
	public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekdayRegularCustomerCost(){
		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Bridgewood", 150, 50);
		int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekdayCustomerCost();
		Assert.assertEquals(150, hotelRegularCustomerCost);
	}
	
	@Test
	public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekendRegularCustomerCost(){
		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Bridgewood", 150, 50);
		int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekendCustomerCost();
		Assert.assertEquals(50, hotelRegularCustomerCost);
	}

	
}
