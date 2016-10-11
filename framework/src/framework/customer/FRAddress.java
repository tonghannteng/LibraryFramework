package framework.customer;

import common.FRIModel;

public class FRAddress extends FRIModel{
	
	private static final long serialVersionUID = 2240133962942272173L;
	private String street ;
	private String city;
	private String state ;
	private String zipCode ;

	//package level access
	public FRAddress(String street, String city, String state, String zip){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zip;
	}

	public FRAddress(){

	}

	public String getStreet(){
		return this.street;
	}

	void setStreet(String street){
		this.street = street;
	}

	public String getCity(){
		return this.city;
	}

	void setCity(String city){
		this.city = city;
	}

	public String getState(){
		return this.state;
	}

	void setState(String state){
		this.state = state;
	}

	public String getZipcode(){
		return this.zipCode;
	}

	void setZipcode(String zip){
		this.zipCode = zip;
	}

	@Override
	public String toString(){
		return street + " " + city + " " + state + " " + zipCode;
	}

	@Override
	public String getKey() {
		return null;
	}
}
