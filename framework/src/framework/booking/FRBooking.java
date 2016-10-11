package framework.booking;

import java.util.*;

import common.FRIModel;
import framework.customer.FRCustomer;
import framework.item.FRItem;

public class FRBooking extends FRIModel implements FRIBookingObserver{
	private static final long serialVersionUID = 7988123077496059561L;
	
	private HashMap<String, List<String>> hash;
	private String classname;
	
	public FRBooking(){
		hash = new HashMap<String, List<String>>();
	}
	@Override
	public String getKey() {
		return "manager";
	}
	@Override
	public void attach(FRCustomer c, FRItem item) {
		List<String> list = null;
		classname = new String(c.getClass().getName());
		if(hash.get(item.getKey()) == null){
			list = new ArrayList<String>();
			hash.put(item.getKey(), list);
		}else{
			list = hash.get(item.getKey());
		}
		list.add(c.getKey());
		save();
	}
	@Override
	public void detach(FRCustomer c, FRItem item) {
		List<String> list = hash.get(item.getKey());
		list.remove(c.getKey());
		save();
	}
	@Override
	public void notifyCustomer(FRItem item) {
		List<String> list = hash.get(item.getKey());
		if(list == null){
			return;
		}
		for(int i = 0; i < list.size(); i++){
			String memberId = list.get(i);
			FRCustomer c = (FRCustomer)FRCustomer.getInstance(classname, memberId);
			c.update(item);
		}
		hash.remove(item.getKey());
		save();
	}
	
}
