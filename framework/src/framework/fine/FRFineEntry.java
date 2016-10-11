package framework.fine;

import java.io.Serializable;
import java.time.LocalDate;

import framework.*;
import framework.item.FRLendableItem;
import framework.rentrecord.FRRentRecordEntry;

import java.util.*;

import common.FRIModel;

public class FRFineEntry extends FRIModel implements FRCheckItem{

	private static final long serialVersionUID = 12345654L;
	protected FRLendableItem copy;
	protected LocalDate datePaid;
	protected String title;
	protected LocalDate returnDate;
	protected double fineValue;
	protected boolean paid;
	protected FRRentRecordEntry checkoutRecordEntry;

	public FRFineEntry(LocalDate datePaid, LocalDate returnDate, double fineValue,
			FRRentRecordEntry rentRecord) {
		this.datePaid = datePaid;
		this.returnDate = returnDate;
		this.fineValue = fineValue;
		this.checkoutRecordEntry = checkoutRecordEntry;
	
	}
	public double callFine(){
		
		LocalDate now = LocalDate.now();
		int days= returnDate.getDayOfYear() - now.getDayOfYear();  
		System.out.println(days);
		
		//totalPrice = (pricePerDay * days);
		return fineValue*days;
	}
	
	public FRLendableItem getCopy() {
		return copy;
	}
	public void setCopy(FRLendableItem copy) {
		this.copy = copy;
	}
	public LocalDate getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(LocalDate datePaid) {
		this.datePaid = datePaid;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public double getFineValue() {
		return fineValue;
	}
	public void setFineValue(double fineValue) {
		this.fineValue = fineValue;
	}
	public FRRentRecordEntry getCheckoutRecordEntry() {
		return checkoutRecordEntry;
	}
	public void setCheckoutRecordEntry(FRRentRecordEntry rentRecord) {
		this.checkoutRecordEntry = rentRecord;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public void accept(FRVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitor(this);
	}


	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
