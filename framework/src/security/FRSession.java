package security;

import framework.customer.FRAdmin;

public enum FRSession {
	
	INSTANCE;
	
	private FRAdmin admin;
	
	public void setAdmin(FRAdmin admin){
		this.admin = admin;
	}
	public FRAdmin getAdmin(){
		return admin;
	}
	
	public boolean hasLoggedInAdmin(){
		return admin != null;
	}

}
