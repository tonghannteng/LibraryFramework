package business;

import framework.customer.FRAddress;

public class Address extends FRAddress{

	public Address(String state, String city, String street, String zip) {
		super(state, city, street, zip);
	}

	public Address(){
		super();
	}
	
}
