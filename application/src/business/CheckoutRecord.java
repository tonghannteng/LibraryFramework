package business;


import java.util.ArrayList;
import java.util.List;

import framework.rentrecord.FRRentRecords;

public class CheckoutRecord extends FRRentRecords {

	private static final long serialVersionUID = -3119855589946373695L;
	private List<CheckoutRecordEntry> entries = new ArrayList<>();



	public CheckoutRecord(LibraryMember m) {
		customer = m;
	}

	public String toString() {
		return entries.toString();
	}

	public CheckoutRecord readCheckoutRecord(String memID)
	{
		return (CheckoutRecord) CheckoutRecord.getInstance(CheckoutRecord.class.getName(), memID);
	}

	@Override
	public String getKey() {
		return this.customer.getKey();
	}

}
