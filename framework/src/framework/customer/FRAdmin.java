package framework.customer;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import common.FRIModel;
import database.FRDBFacade;
import framework.item.FRItem;
import framework.item.FRLendableItem;
import security.FRPasswordHasher;
import security.FRSession;

public class FRAdmin extends FRIModel {

	protected FRPasswordHasher passwordHasher;

	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;

	public String getUsername(){
		return username;
	}
	public FRAdmin() {

	}

	public FRAdmin(String u, String p) throws NoSuchAlgorithmException{
		username = u;
		passwordHasher = new FRPasswordHasher(FRPasswordHasher.getMD5HashMethod());
		password = passwordHasher.encrypt(p);
	}
	public boolean login(String name, String pwd) throws NoSuchAlgorithmException {
		System.out.println("name=" + name + " pwd=" + pwd);

		passwordHasher = new FRPasswordHasher(FRPasswordHasher.getMD5HashMethod());
		String p = passwordHasher.encrypt(pwd);
		FRAdmin admin = (FRAdmin) this.getInstance(this.getClass().getName(), name);
		if (admin != null) {
			if (admin.password.equals(p) == true) {
				return true;
			}else{
				return false;
			}
		} else {
			if (name.equals("root") && pwd.equals("111111")) {
				admin = new FRAdmin();
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

	public final void save() {
		FRDBFacade.saveObject(this);
	}

	public static void createAdmin(String name, String pwd, FRPasswordHasher passwordHasher)
			throws NoSuchAlgorithmException {
		FRAdmin a = new FRAdmin();
		a.username = name;
		a.password = passwordHasher.encrypt(pwd);
		a.passwordHasher = passwordHasher;
		a.save();
	}

	public boolean addItem(FRItem item) {
		try {
			FRDBFacade.saveObject(item);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addLendable(FRLendableItem copy) {
		FRDBFacade.saveObject(copy);

		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeItem(FRItem item) {
		// TODO Auto-generated method stub
		FRDBFacade.deleteObject(item);
		return false;
	}

	public boolean removeLendable(FRLendableItem copy) {
		// TODO Auto-generated method stub
		FRDBFacade.deleteObject(copy);
		return false;
	}

	public void rentItem(FRCustomer c, FRItem item, LocalDate dueDate) {
		LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		c.checkOut(item, localDate, dueDate);
	}

	public void returnItem(FRCustomer c, FRLendableItem copy) {
		c.checkIn(copy);
	}

	@Override
	public String getKey() {
		return username;
	}

	// logout session
	public final void logout() {
		FRSession.INSTANCE.setAdmin(this);
	}
	
	public void addCustomer(FRCustomer c){
		c.save();
	}
}
