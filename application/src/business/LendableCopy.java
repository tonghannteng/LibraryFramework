package business;

import framework.item.FRItem;
import framework.item.FRLendableItem;

public class LendableCopy extends FRLendableItem {

	private static final long serialVersionUID = -6307098549062543163L;
	public LendableCopy(FRItem p, String k){
		super(p, k);
	}

	public Book getBook()
	{
		return (Book) this.getItem();
	}

	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}


	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
