package com.bridgelabz.hotelreservationsystem;
import org.junit.Assert;
import org.junit.Test;
public class HotelReservationSystemTest {
	@Test
	public void givenHotelDetails_WhenValuesEnteredAreCorrect_ShoulReturnTrue()
	{

		HotelReservationIF hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood",  110);
		hotelReservation.addHotel("Bridgewood", 160);
		hotelReservation.addHotel("Ridgewood", 220);
		int hotelListSize = hotelReservation.getHotelListSize();
		hotelReservation.printHotelList();
		Assert.assertEquals(3, hotelListSize);
	}
	
}
