package framework.item;

import common.FRIModel;

public abstract class FRLendableItem extends FRIModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean available;
	protected FRItem item;
	protected int copyId;
	@Override
	public boolean equals(Object o){
		if(o.getClass() != this.getClass()){
			return false;
		}
		FRLendableItem li = (FRLendableItem) o;
		if(li.copyId == this.copyId){
			return true;
		}
		return false;
	}
	public FRLendableItem(FRItem item, String k) {
		super();
		this.setKey(k);
		this.available = true;
		this.item = item;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public FRItem getItem() {
		return item;
	}
	public void setItem(FRItem item) {
		this.item = item;
	}
}
