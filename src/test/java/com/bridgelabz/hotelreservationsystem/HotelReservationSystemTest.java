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
		String startDateValidation = startDate.toString();
		String endDateValidation = endDate.toString();
		boolean validStartDate = hotelReservation.validateDate(startDateValidation);
		boolean validEndDate = hotelReservation.validateDate(endDateValidation);
		if(validStartDate && validEndDate) {
			Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate,"regular");
			Assert.assertEquals("Bridgewood", hotel.getHotelName());
		}	
		else {
			System.out.println("Not Valid");
		}

	}
	@Test
	public void givenHotelDetails_shouldReturnHighestRatedHotel(){
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		hotelReservation.addHotel("Lakewood", 3, 110, 90,80,80);
		hotelReservation.addHotel("Bridgewood", 4, 150, 50,110,50);
		hotelReservation.addHotel("Ridgewood", 5, 220, 150,100,40);
		LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);    
		LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);    
		String startDateValidation = startDate.toString();
		String endDateValidation = endDate.toString();
		boolean validStartDate = hotelReservation.validateDate(startDateValidation);
		boolean validEndDate = hotelReservation.validateDate(endDateValidation);
		if(validStartDate && validEndDate) {
			Hotel hotel = hotelReservation.getCheapestBestRatedHotel(startDate, endDate,"regular");
			Assert.assertEquals("Ridgewood", hotel.getHotelName());
		}	
		else {
			System.out.println("Not Valid ");
		}
	}
	@Test
	public void givenHotelDetails_WhenNull_ShouldThrowHotelReservationException() 
	{
		
		
		try {
			HotelReservationSystem hotelReservation = new HotelReservationSystem();
			hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
			hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
			hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
			LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
			LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
			hotelReservation.getCheapestBestRatedHotel( startDate, endDate, null);
		}
		catch(HotelReservationSystemException e){
			Assert.assertEquals(HotelReservationSystemException.ExceptionType.ENTERED_NULL, e.type);
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void givenHotelDetails_WhenEmpty_ShouldThrowHotelReservationException()
	{
		try {
			HotelReservationSystem hotelReservation = new HotelReservationSystem();
			hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
			hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
			hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
			LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
			LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
			hotelReservation.getCheapestBestRatedHotel(startDate, endDate, "");
		}
		catch(HotelReservationSystemException e){
			Assert.assertEquals(HotelReservationSystemException.ExceptionType.ENTERED_EMPTY, e.type);
			e.printStackTrace();
		}
		
	}
	@Test
	public void givenDate_WhenProper_ShouldReturnTrue() {
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		boolean isValid = hotelReservation.validateDate("2009-12-13");
		Assert.assertEquals(isValid, true);
	}
	
	@Test
	public void givenDate_WhenNotProperFormatPresent_ShouldReturnFalse() 
	{
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		boolean isValid = hotelReservation.validateDate("28-07-1999");
		Assert.assertEquals(isValid, false);
	}
	
	@Test
	public void givenDate_WhenSeperatedBySlashesPresent_ShouldReturnFalse()
	{
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		boolean isValid = hotelReservation.validateDate("1976/10/2021");
		Assert.assertEquals(isValid, false);
	}
	
	@Test
	public void givenDate_WhenContainsCharctersPresent_ShouldReturnFalse() 
	{
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		boolean isValid = hotelReservation.validateDate("200e-a-19");
		Assert.assertEquals(isValid,false);
	}
	
	@Test
	public void givenDate_WhenSpecialCharctersPresent_ShouldReturnFalse() {
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		boolean isValid = hotelReservation.validateDate("2009/^$/13");
		Assert.assertEquals(isValid,false);
	}
	
	@Test
	public void givenDate_WhenNull_ShouldThrowHotelReservationSystemException() 
	{
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		try {
			String date = null;
			hotelReservation.validateDate(date);
		}
		catch(HotelReservationSystemException e){
			Assert.assertEquals(HotelReservationSystemException.ExceptionType.ENTERED_NULL,e.type);
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenDate_WhenEmpty_ShouldThrowHotelReservationSystemException()
	{
		
		HotelReservationSystem hotelReservation = new HotelReservationSystem();
		try {
			String date = "";
			hotelReservation.validateDate(date);
		}
		catch(HotelReservationSystemException e){
			Assert.assertEquals(HotelReservationSystemException.ExceptionType.ENTERED_EMPTY,e.type);
			e.printStackTrace();
		}
	}

	
}
