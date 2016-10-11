package security;

import java.security.NoSuchAlgorithmException;

import org.mindrot.BCrypt;

public class FRJBcryptHasher implements FRHashingMethod {
	
	protected FRJBcryptHasher() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String hash(String data) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		return BCrypt.hashpw(data, BCrypt.gensalt(12));
	}

}
