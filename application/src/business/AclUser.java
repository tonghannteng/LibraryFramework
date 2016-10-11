package business;

public class AclUser {

	private String username;
	private String password;
	private String passwordHash;
	
	AclUser(String username, String pass){
		this.username = username;
		this.password = pass;
		
	}
	
	public String getUername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
}
