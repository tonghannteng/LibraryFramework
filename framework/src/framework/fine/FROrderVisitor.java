package framework.fine;

import framework.item.FRItem;
import framework.rentrecord.FRRentRecordEntry;
import java.util.Date;

public class FROrderVisitor implements FRVisitor {
	private double price_order = 0.0;
	private double price_fine = 0.0;
	@Override
	public void visitor(FRRentRecordEntry item) {
		// TODO Auto-generated method stub
		price_order += item.calPrice();
	}
	public double getTotalPrice(){	
		return price_order;
	}
	public double getTotalFine(){	
		return price_fine;
	}
	@Override
	public void visitor(FRFineEntry fine) {
		// TODO Auto-generated method stub
		price_fine += fine.getFineValue();
	}

}
