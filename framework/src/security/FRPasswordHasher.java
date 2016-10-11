package security;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class FRPasswordHasher implements Serializable{
	
	private FRHashingMethod hashStrategy;
	
	public FRPasswordHasher(FRHashingMethod hashStrategy){
		this.hashStrategy = hashStrategy;
	}
	
	public void setHashingStrategy(FRHashingMethod hashStrategy){
		this.hashStrategy = hashStrategy;
	}
	
	public String encrypt(String password) throws NoSuchAlgorithmException{
		return hashStrategy.hash(password);
	}
	
	public static FRHashingMethod getSHA1HashMethod(){
		return new FRSHA1Hasher();
	}
	
	public static FRHashingMethod getMD5HashMethod(){
		return new FRMD5Hasher();
	}
	
	public static FRHashingMethod getBcryptHashMethod() {
		return new FRJBcryptHasher();
	}

}
