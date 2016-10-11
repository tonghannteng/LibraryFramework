package dataaccess;

import java.util.List;

import business.Book;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LendableCopy;
import business.LibraryMember;

public interface IDataAccessFacade {


	void saveLibraryMember(String name, LibraryMember member);

	LibraryMember readLibraryMember(String name);

	void editLibraryMember(String name, LibraryMember member);
	void saveLendableCopy(int copyId, LendableCopy lendableCopy);

	LendableCopy readLendableCopy(int copyId);

	void saveCheckOutRecord(String memId, CheckoutRecord entry);

	CheckoutRecord readCheckOutRecord(String memberID);

}
