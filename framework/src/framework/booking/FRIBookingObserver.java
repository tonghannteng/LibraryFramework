package framework.booking;

import framework.customer.FRCustomer;
import framework.item.FRItem;

public interface FRIBookingObserver {
	public void attach(FRCustomer c, FRItem item);
	public void detach(FRCustomer c, FRItem item);
	public void notifyCustomer(FRItem item);
}
