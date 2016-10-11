package business;

import framework.booking.FRBooking;

public class Booking extends FRBooking{
	private static Booking manager = null;
	private Booking(){
		
	}
	public static Booking sharedBooking(){
		if(manager == null){
			manager = (Booking)Booking.getInstance(Booking.class.getName(), "manager");
			if(manager == null){
				manager = new Booking();
			}
		}
		return manager;
	}
}
