package business;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class LoginHelper {

	List<SystemUser> userList = new ArrayList();

	public LoginHelper() throws NoSuchAlgorithmException{
		SystemUser lib = (SystemUser) new SystemUser("lib", "1");
		lib.setLibrarian();
		lib.save();
		
		SystemUser p = (SystemUser) new SystemUser("peng", "dong");
		p.save();
	}
}
