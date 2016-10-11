package business;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import dataaccess.DataAccessFacade;
import framework.rentrecord.FRRentRecordEntry;

public class CheckoutRecordEntry extends FRRentRecordEntry {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private LendableCopy copy;
	private String title;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	private boolean returned;

	public CheckoutRecordEntry(LendableCopy copy, LocalDate checkoutDate, LocalDate dueDate){
		this.copy = copy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.title= copy.getBook().getTitle();
		this.setReturnedDate(null);
		this.returned = false;
	}


	public LendableCopy getCopy() {
		return copy;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setCopy(LendableCopy copy) {
		this.copy = copy;
	}




	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}




	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}




	public LocalDate getDueDate() {
		return dueDate;
	}




	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}




	public String toString() {
		return "[" + "checkoutdate:" +
	        checkoutDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
	        ", dueDate: " + dueDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
	        ", publication: " + copy + "]";
	}


	public LocalDate getReturnedDate() {
		return returnedDate;
	}


	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}


	public boolean isReturned() {
		return returned;
	}


	public void setReturned(boolean returned) {
		this.returned = returned;
	}


	
}
