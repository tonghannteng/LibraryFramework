package business;

import java.io.Serializable;

public class AdminState implements State,Serializable{

	@Override
	public boolean isAdmin() {
		return true;
	}

	@Override
	public boolean isLibrarian() {
		return false;
	}

	@Override
	public void addMember(LibraryMember m) {
		m.save();
	}

}
