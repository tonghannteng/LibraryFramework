package framework.booking;

import java.util.Date;

import common.FRIModel;
import database.FRDBFacade;
import framework.customer.FRCustomer;
import framework.item.FRItem;

public class FRBookingRecord extends FRIModel{
	private static final long serialVersionUID = 1L;
	private FRCustomer customer;
	private FRItem item;
	private Date bookingDate;
	
	@Override
	public String getKey() {
		return item.getKey();
	}

	public FRCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(FRCustomer customer) {
		this.customer = customer;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
}
