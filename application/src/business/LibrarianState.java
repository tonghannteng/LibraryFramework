package business;

import java.io.Serializable;

public class LibrarianState implements State,Serializable{

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public boolean isLibrarian() {
		return true;
	}

	@Override
	public void addMember(LibraryMember m) {
		System.out.println("No privilage to add member");
		
	}

}
