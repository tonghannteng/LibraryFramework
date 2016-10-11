package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public final class FRSHA1Hasher implements FRHashingMethod {
	
	protected FRSHA1Hasher() {
	}

	@Override
	public String hash(String data) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
		byte[] digest = messageDigest.digest(data.getBytes());
		return Base64.getEncoder().encodeToString(digest);
	}

}
