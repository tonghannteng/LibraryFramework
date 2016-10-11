package framework.rentrecord;

import java.util.Date;

import common.FRIModel;
import framework.customer.FRCustomer;
import framework.fine.FRCheckItem;
import framework.fine.FRVisitor;
import framework.item.*;

public class FRRentRecordEntry extends FRIModel implements FRCheckItem{

	private static final long serialVersionUID = 1L;
	private FRItem item;
	private FRLendableItem lendableItem;
	public Date startDate, dueDate;
	public double pricePerDay, totalPrice, fine;
	private FRCustomer customer;
	
	public FRRentRecordEntry(FRItem item, FRLendableItem lendableItem, Date startDate, Date dueDate, double pricePerDay,
			double totalPrice, double fine, FRCustomer customer) {
		super();
		this.item = item;
		this.lendableItem = lendableItem;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.pricePerDay = pricePerDay;
		this.totalPrice = totalPrice;
		this.fine = fine;
		this.customer = customer;
	}
	public double calPrice(){
		int days= (int) ((dueDate.getTime()- startDate.getTime()) / 1000 / 60 / 60 / 24);  
		totalPrice = (pricePerDay * days);
		return totalPrice;
	}
	public double calFine(Date date){
		double ret = 0;
		
		return ret;
	}
	public FRRentRecordEntry(){
		
	}
	@Override
	public String getKey() {
		return lendableItem.getKey();
	}


	public FRCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(FRCustomer customer) {
		this.customer = customer;
	}
	public FRItem getItem() {
		return item;
	}
	public void setItem(FRItem itme) {
		this.item = itme;
	}
	public FRLendableItem getLendableItem() {
		return lendableItem;
	}
	public void setLendableItem(FRLendableItem lendableItem) {
		this.lendableItem = lendableItem;
	}
	@Override
	public void accept(FRVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitor(this);
	}
	
}
