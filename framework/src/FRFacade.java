import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import framework.booking.FRBooking;
import framework.customer.*;
import framework.item.FRItem;
import framework.item.FRLendableItem;

public class FRFacade {
	public FRAdmin admin;
	public FRBooking booking;
	public boolean login(String u, String p) throws NoSuchAlgorithmException{
		if(admin == null){
			System.out.println("Internal error: Application should set admin first");
			return false;
		}
		return admin.login(u, p);
	}
	public void notifyCustomer(FRItem i){
		if(booking == null){
			System.out.println("Booking is not set");
			return;
		}
		booking.notifyCustomer(i);
	}
	protected boolean checkAdmin(){
		if(admin == null){
			System.out.println("Internal error: Application should set admin first");
			return false;
		}
		return true;
	}
	public void addCUstomer(FRCustomer c){
		if(checkAdmin()){
			admin.addCustomer(c);
		}
	}
	
	public void returnItem(FRCustomer c, FRLendableItem copy) {
		admin.returnItem(c, copy);
	}
	
	public void rentItem(FRCustomer c, FRItem item, LocalDate dueDate){
		if(checkAdmin()){
			admin.rentItem(c, item, dueDate);
		}
	}
}
