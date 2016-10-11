package business;

import framework.item.FRItem;
import framework.item.IPrototype;

public class Magazin extends Publication{
	public String mid;
	@Override
	public String getKey() {
		return mid;
	}

	public IPrototype frclone(){
		Magazin b = new Magazin();
		b.mid = this.mid;
		return b;
	}
}
