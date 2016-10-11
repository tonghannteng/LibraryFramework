package business;

import java.io.Serializable;

import common.FRIModel;

public class PersonalInfo extends FRIModel{

	/**
	 *
	 */
	private static final long serialVersionUID = 3264363012254278454L;
	private String firstName;
	private String lastName;
	private String phone = "";
	private Address address;

	PersonalInfo(String firstName, String lName, String phone, String street, String city, String state, String zip){
		this.firstName = firstName;
		this.lastName = lName;
		this.phone = phone;
		address = new Address(street, city, state, zip);

	}

	public PersonalInfo(String firstName, String lName){
		this.firstName = firstName;
		this.lastName = lName;
		address = new Address();

	}

	public String getFirstName(){
		if(this.firstName == null){
			return "";
		}
		return this.firstName;
	}

	public String getLastName(){
		if(this.lastName == null){
			return "";
		}
		return this.lastName;
	}

	public String getPhone(){
		return this.phone;
	}

	public String getName(){
		return firstName + " " + lastName;
	}

	public String getAddress(){
		return this.address.toString();
	}
	public Address getAddressObject(){
		return this.address;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
