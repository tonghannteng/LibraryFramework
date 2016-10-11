package business;

import java.io.Serializable;
import java.time.LocalDate;

import business.*;
import framework.fine.FRFineEntry;

import java.util.*;

public class FineEntry extends FRFineEntry {

	private static final long serialVersionUID = 12345654L;

	private CheckoutRecordEntry checkoutRecordEntry;

	public FineEntry(LocalDate datePaid, LocalDate returnDate, double fineValue,
			CheckoutRecordEntry checkoutRecordEntry) {
		super(datePaid, returnDate, fineValue, checkoutRecordEntry);
		this.copy = checkoutRecordEntry.getCopy();
	//	this.datePaid = datePaid;
		LendableCopy copy = (LendableCopy) this.copy;
		this.setTitle(copy.getBook().getTitle());
	/*	this.returnDate = returnDate;
		this.fineValue = fineValue;*/
		this.paid = false;
	//	this.checkoutRecordEntry = checkoutRecordEntry;
	}

	
	public LendableCopy getCopy() {
		return (LendableCopy)copy;
	}
	public void setCopy(LendableCopy copy) {
		this.copy = copy;
	}
	public LocalDate getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(LocalDate datePaid) {
		this.datePaid = datePaid;
	}
/*	public LocalDate getReturnDate() {
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
	public CheckoutRecordEntry getCheckoutRecordEntry() {
		return checkoutRecordEntry;
	}
	public void setCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
		this.checkoutRecordEntry = checkoutRecordEntry;
	}*/
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
}
