package com.bridgelabz.hotelreservationsystem;

public class HotelReservationSystemException extends RuntimeException {
	enum ExceptionType{
		ENTERED_NULL, ENTERED_EMPTY;
	}
	
	ExceptionType type;

	public HotelReservationSystemException (ExceptionType type, String message) {

		super(message);
		this.type = type;
		
	}
}
