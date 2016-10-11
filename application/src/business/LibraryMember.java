package business;

import java.time.LocalDate;
import java.util.Date;

import framework.customer.FRCustomer;
import framework.item.FRItem;
import framework.item.FRLendableItem;
import framework.rentrecord.FRRentRecordEntry;

public class LibraryMember extends FRCustomer {

	private static final long serialVersionUID = -2226197306790714013L;

	private PersonalInfo personalInfo;
	private String memberId;

	public LibraryMember(){
		
	}
	public LibraryMember(String memberId, String firstName, String lastName, String phone, String street, String city, String state, String zip) {

		this.personalInfo = new PersonalInfo(firstName, lastName, phone, street, city, state, zip);
		this.memberId = memberId;
		init();
	}

	public LibraryMember(String memberId, String firstName, String lastName){
		this.personalInfo = new PersonalInfo(firstName, lastName);
		this.memberId = memberId;
		init();
	}

	public CheckoutRecord getRecord() {
		return (CheckoutRecord)record;
	}


	public void setRecord(CheckoutRecord record) {
		this.record = record;
	}


	public String getName() {
		return personalInfo.getName();
	}

	public String getFirstName() {
		return personalInfo.getFirstName();
	}

	public String getLastName() {
		return personalInfo.getLastName();
	}
	public String getMemberId() {
		return memberId;
	}


	public Address getAddress() {
		return personalInfo.getAddressObject();
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void checkOut(LendableCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		FRRentRecordEntry entry = new CheckoutRecordEntry(copy, checkoutDate, dueDate);
		record.addEntry(entry);
	}

	public CheckoutRecord getCheckoutRecord(){
		return (CheckoutRecord)this.record;
	}

	public String getKey(){
		return memberId;
	}
	public String toString() {
		return "Checkout record for library member " + personalInfo.getName() + ": " + record;
	}
	public LibraryMember getLiberaryMemberByID(String id)
	{
		return (LibraryMember)getInstance(LibraryMember.class.getName(), id);
	}
	public String getPhone(){
		return this.personalInfo.getPhone();
	}
	public void addFineEntry(CheckoutRecordEntry re, double fine){
		FineRecord fr = FineRecord.readFineRecord(this.memberId);
		if(fr == null){
			fr = new FineRecord(this);
		}
		if(re == null){
			System.out.println("re is null");
		}
		if(re.getReturnedDate() == null){
			System.out.println("returneddate is null");
		}
		FineEntry fe = new FineEntry(null, re.getReturnedDate(), fine, re);
		fr.addEntry(fe);
	//	fr.save(this.memberId);
		fr.save();
	}

	public FRRentRecordEntry createRentRecord(FRItem arg0, FRLendableItem arg1, LocalDate arg2, LocalDate arg3) {
		FRRentRecordEntry entry = new CheckoutRecordEntry((LendableCopy)arg1, arg2, arg3);
		record.addEntry(entry);
		return entry;
	}
	
	public void init(){
		createFineRecord();
		createRentRecord();
	}
	@Override
	public void createFineRecord() {
		//TODO 
		
	}
	public void save(){
		super.save();
		record.save();
	}
	@Override
	public void createRentRecord() {
		if(record == null){
			record =  (CheckoutRecord)CheckoutRecord.getInstance(CheckoutRecord.class.getName(), memberId);
			if(record == null){
				record = new CheckoutRecord(this);
			}else{
				System.out.println("Record num:"+record.getRecords().size());
			}
		}
		System.out.println("Create Rent Record for member:" + memberId);
	}
}
