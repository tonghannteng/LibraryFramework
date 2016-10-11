package business;
public interface State {
	public boolean isAdmin();
	public boolean isLibrarian();

	public void addMember(LibraryMember m);
}
