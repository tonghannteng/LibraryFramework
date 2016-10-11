package framework.customer;

import java.time.LocalDate;
import java.util.Date;

import common.FRIModel;
import framework.item.FRItem;
import framework.item.FRLendableItem;
import framework.rentrecord.FRRentRecordEntry;
import framework.rentrecord.FRRentRecords;

public abstract class FRCustomer extends FRIModel{
	private static final long serialVersionUID = -1245500069931305298L;
	protected String name;
	protected String email;
	protected String password;
	protected FRRentRecords record;
	protected FRAddress dpaddress = new FRAddress();
	
	public void update(FRItem item){
		System.out.println("User " + getKey() + " notified. Item: " + item.getKey() + " has one copy availbale");
	}
	public abstract void createRentRecord();
	public abstract void createFineRecord();
	public FRCustomer(){

	}

	public String getKey() {
		return name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public FRAddress getDPAddress() {
		return dpaddress;
	}
	public void setDPAddress(FRAddress address) {
		this.dpaddress = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public abstract FRRentRecordEntry createRentRecord(FRItem item, FRLendableItem copy, LocalDate startDate, LocalDate dueDate);
	
	public FRRentRecordEntry getRentRecordEntry(FRLendableItem copy){
		FRRentRecordEntry rr = null;
		while(record.getRecordIterator().hasNext()){
			rr = (FRRentRecordEntry) record.getRecordIterator().next();
			if(rr.getLendableItem().equals(copy)){
				return rr;
			}
		}
		
/*		for(int i = 0; i < record.getRecords().size(); i++){
			rr = record.getRecords().get(i);
			if(rr.getLendableItem().equals(copy)){
				return rr;
			}
		}*/
		return null;
	}
	public boolean checkOut(FRItem item, LocalDate startDate, LocalDate dueDate) {
		//DP: template pattern
		if(item.hasLendableCopy()){
			FRLendableItem li = item.rent();
			FRRentRecordEntry rr = this.createRentRecord(item,  li, startDate, dueDate);
			this.record.addEntry(rr);
		}
		return false;
	}
	
	public void checkIn(FRLendableItem copy){
		FRRentRecordEntry rr = getRentRecordEntry(copy);
		Date now = new Date();
		double price = rr.calPrice();
		if(price > 0){
			System.out.println("Total Price ="+price);
		}
		double fine = rr.calFine(now);
		if(fine > 0){
			System.out.println("Fine ="+fine);
		}
		copy.setAvailable(true);
		copy.save();
	}

}
