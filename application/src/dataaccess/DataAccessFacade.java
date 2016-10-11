package dataaccess;

import business.Book;
import business.FineRecord;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LendableCopy;
import business.LibraryMember;

public class DataAccessFacade implements IDataAccessFacade  {

	public static final String DATE_PATTERN = "MM/dd/yyyy";
	private DataAccess libraryMember;
	private DataAccess lendableCopy;
	private DataAccess fines;
	private DataAccess checkOutRecords;

	public DataAccessFacade(){
		this.libraryMember = new LibraryMemberDataAccess();
		this.lendableCopy =  new LendableCopyDataAccess();
		this.fines =  new FineRecordDataAccess();
		this.checkOutRecords = new CheckOutRecordAccess();
	}

	@Override
	public void saveLibraryMember(String name, LibraryMember member) {
		libraryMember.write(name, member);
	}


	@Override
	public CheckoutRecord readCheckOutRecord(String memberID) {
		return (CheckoutRecord)libraryMember.read(memberID);
	}

	@Override
	public void saveCheckOutRecord(String memId, CheckoutRecord record) {
		libraryMember.write(memId, record);
	}


	@Override
	public LibraryMember readLibraryMember(String memberID) {
		return (LibraryMember)libraryMember.read(memberID);
	}

	@Override
	public void saveLendableCopy(int copyId, LendableCopy lendableCopy) {
			this.lendableCopy.write(Integer.toString(copyId), lendableCopy);
	}

	@Override
	public LendableCopy readLendableCopy(int copyId) {
		return (LendableCopy)lendableCopy.read(Integer.toString(copyId));
	}


	public void saveFineRecord(String memBerId, FineRecord records) {
		this.fines.write(memBerId, records);
	}

	public FineRecord readFines(String memID) {
		return (FineRecord)fines.read(memID);
	}

	@Override
	public void editLibraryMember(String name, LibraryMember member) {
		// TODO Auto-generated method stub

	}

}
