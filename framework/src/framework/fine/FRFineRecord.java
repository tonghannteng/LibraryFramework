package framework.fine;

import java.util.ArrayList;
import java.util.List;

import common.FRIModel;
import framework.customer.FRCustomer;
import framework.rentrecord.FRRentRecordEntry;

public class FRFineRecord extends FRIModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -435819322789079216L;
	public FRCustomer customer;
	public FRRentRecordEntry rentrecord;
	public double fine;
	public boolean isPaid;
	public List<FRFineEntry> records = new ArrayList<FRFineEntry>();
	
	public FRFineRecord(FRCustomer customer, FRRentRecordEntry rentrecord, double fine, boolean isPaid,
			List<FRFineEntry> records) {
		super();
		this.customer = customer;
		this.rentrecord = rentrecord;
		this.fine = fine;
		this.isPaid = isPaid;
		this.records = records;
	}
	public FRFineRecord(){
	}
	@Override
	public String getKey() {
		return customer.getName();
	}
}
