package business;

import java.io.Serializable;
import java.util.*;

import dataaccess.DataAccessFacade;
import dataaccess.FineRecordDataAccess;
import database.FRDBFacade;
import framework.fine.FRFineRecord;

public class FineRecord extends FRFineRecord{

	private static final long serialVersionUID = 3333L;
	private LibraryMember lmember;
	private ArrayList<FineEntry> entries;
	public void addEntry(FineEntry fe){
		entries.add(fe);
	}
	public FineRecord(LibraryMember m){
		setLmember(m);
		entries = new ArrayList<FineEntry>();
		
	}
	public LibraryMember getLmember() {
		return lmember;
	}
	public void setLmember(LibraryMember lmember) {
		this.lmember = lmember;
	}
	public ArrayList<FineEntry> getEntries(){
		return entries;
	}
	public static FineRecord readFineRecord(String memberid){
/*		DataAccessFacade da = new DataAccessFacade();
		return da.readFines(memberid);
*/
		return (FineRecord) FineRecord.getInstance(FineRecord.class.getName(), memberid);

	}
	@Override
	public String getKey() {
		return lmember.getKey();
	}
/*	public void save(String memberId){
		DataAccessFacade da = new DataAccessFacade();
		da.saveFineRecord(memberId, this);
	}*/
	@Override
	public void save() {
		FRDBFacade.saveObject(this);
	}
}
