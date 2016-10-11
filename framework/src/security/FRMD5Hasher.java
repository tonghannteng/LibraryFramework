package security;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class FRMD5Hasher implements FRHashingMethod, Serializable {
	
	protected FRMD5Hasher() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String hash(String data) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] digest = messageDigest.digest(data.getBytes());
		return Base64.getEncoder().encodeToString(digest);
	}

}
