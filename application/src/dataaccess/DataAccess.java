package dataaccess;


public interface DataAccess {
//	public void saveLibraryMember(String name, LibraryMember member);
//	public LibraryMember readLibraryMember(String name);
//
	void write(String name, Object obj);
	Object read(String name);
}
