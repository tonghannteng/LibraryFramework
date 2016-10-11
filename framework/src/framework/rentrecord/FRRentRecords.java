package framework.rentrecord;

import java.util.ArrayList;
import java.util.List;

import common.FRIModel;
import framework.customer.FRCustomer;

public class FRRentRecords extends FRIModel implements Aggregate{
	private static final long serialVersionUID = 1L;
	public FRCustomer customer;
	public List<FRRentRecordEntry> records = new ArrayList<FRRentRecordEntry>();
	public FRRentRecords(FRCustomer c){
		customer = c;
	}
	public void addEntry(FRRentRecordEntry entry){
		records.add(entry);
		customer.save();
	}
	public FRRentRecords(){
	}
	public List<FRRentRecordEntry> getRecords(){
		return records;
	}
	@Override
	public String getKey() {
		return customer.getName();
	}
	@Override
	public Iterator getRecordIterator() {
		return new RecordIterator();
	}  
    private class RecordIterator implements Iterator {  
        int index = 0;  
        @Override 
        public boolean hasNext() {  
           if(index < records.size()){  
        		return true;
           }      
        	return false;   
        }  
        
        @Override   
        public Object next() {  
        	if(this.hasNext()){ 
        		return records.get(index++);
        	}   
        	return null;  
        }

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return records.size();
		}   
   }
}
