package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import framework.item.FRItem;

abstract public class Publication extends FRItem {

	private static final long serialVersionUID = 2010893663327964921L;
	private LocalDate dateDue;
	private String title;
	private int maxCheckoutLength;

	protected void setDateDue(LocalDate d) {
		dateDue = d;
	}
	public Publication() {
		
	}
	public LocalDate getDateDue() {
		return dateDue;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
}
