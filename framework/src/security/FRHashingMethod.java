package security;

import java.security.NoSuchAlgorithmException;

public interface FRHashingMethod {
	
	public String hash(String data) throws NoSuchAlgorithmException;

}
