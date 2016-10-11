package business;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import common.FRIModel;
import database.FRDBFacade;
import framework.customer.FRAdmin;
import security.FRPasswordHasher;
import security.FRSession;

public class SystemUser extends FRAdmin{
	
	private static final long serialVersionUID = 73493544632078552L;
	public long id;
	State curstate;
	State admin, librarian;
	boolean isLibrarian = false;
	public SystemUser(){
		admin = new AdminState();
		librarian = new LibrarianState();
		curstate = admin;
	}
	public SystemUser(String u, String p) throws NoSuchAlgorithmException{
		super(u,p);
	}

	public boolean login(String name, String pwd) throws NoSuchAlgorithmException {
		System.out.println("name=" + name + " pwd=" + pwd);

		passwordHasher = new FRPasswordHasher(FRPasswordHasher.getMD5HashMethod());
		String p = passwordHasher.encrypt(pwd);
		SystemUser admin = (SystemUser) this.getInstance(this.getClass().getName(), name);
		if (admin != null) {
			if (admin.password.equals(p) == true) {
				System.out.println("is librarian" + isLibrarian);
				if(admin.isLibrarian == true){
					this.setLibrarian();
				}
				return true;
			}else{
				return false;
			}
		} else {
			if (name.equals("root") && pwd.equals("111111")) {
				admin = new SystemUser();
				admin.username = "root";
				// admin.password = "12345678";
				// use md5/sha1 encrypt the password
				admin.passwordHasher = new FRPasswordHasher(FRPasswordHasher.getMD5HashMethod());
				admin.password = admin.passwordHasher.encrypt(pwd);
				admin.save();
				// Session
				FRSession.INSTANCE.setAdmin(admin);
				return true;
			}
		}
		return false;
	}
	public void setAdmin(){
		curstate = admin;
	}
	public void setLibrarian(){
		curstate = librarian;
		isLibrarian = true;
	}
	public boolean isAdmin(){
		return curstate.isAdmin();
	}
	public boolean isLibrarian(){
		return curstate.isLibrarian();
	}
	public void addMember(LibraryMember m){
		curstate.addMember(m);
	}
	public String getName(){
		return username;
	}
}
