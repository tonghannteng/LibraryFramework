package framework.fine;

import java.util.Date;

import framework.item.FRItem;
import framework.rentrecord.FRRentRecordEntry;

public interface FRVisitor {
	public void visitor(FRFineEntry fine);
	public void visitor(FRRentRecordEntry rent);
}
