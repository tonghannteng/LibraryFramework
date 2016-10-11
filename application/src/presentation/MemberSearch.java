package presentation;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import business.Address;
import business.LibraryMember;
import business.SystemUser;
import business.UILib;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MemberSearch extends Application{
	private static Object sysUser = null;
	@FXML private TextField txtMemberID;
	@FXML private TextField txtmember_id;
	@FXML private TextField txtFirstName;
	@FXML private TextField txtLastName;
	@FXML private TextField txtStreet;
	@FXML private TextField txtCity;
	@FXML private TextField txtState;
	@FXML private TextField txtZip;
	@FXML private TextField txtPhone;
	public static LibraryMember member;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("before created");
		Parent root = FXMLLoader.load(getClass().getResource("MemberSearch.fxml"));
		System.out.println("created");
		Scene scene = new Scene(root,400,600);
		sysUser = primaryStage.getUserData();
		//primaryStage.setResizable(false);
		primaryStage.setTitle("Search Library Members");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/*@FXML protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{

	}*/
	private void test() throws Exception{
		search("1");
	}
	private void search(String id) throws Exception{
		LibraryMember member = new LibraryMember();
		member = member.getLiberaryMemberByID(id);
		if(member == null){
			UILib.toast("Can not find the Member!");
		}
		else{//the member is found
			startEditStage(member);
		}
	}
	private void startEditStage(LibraryMember member) throws Exception{
		//txtmember_id= new TextField();
		txtmember_id.setText(member.getMemberId());
		//txtmember_id.commitValue();
		//System.out.println("1");
		//txtFirstName= new TextField();
		txtFirstName.setText(member.getFirstName());
		//System.out.println(member.getFirstName());
		//System.out.println("1");
		//txtLastName= new TextField();
		txtLastName.setText(member.getLastName());
		//System.out.println("1");

		Address add = member.getAddress();
		//txtStreet= new TextField();
		txtStreet.setText(add.getStreet());
		//txtState= new TextField();
		txtState.setText(add.getState());
		//txtCity= new TextField();
		txtCity.setText(add.getCity());
		//txtZip= new TextField();
		txtZip.setText(add.getZipcode());
		//txtPhone= new TextField();
		txtPhone.setText(member.getPhone());
		txtmember_id.setDisable(true);

	}
	@FXML protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{
		try{
			String memberId = txtmember_id.getText();
			String firstName = txtFirstName.getText();
			String lastName  = txtLastName.getText();
			String street = txtStreet.getText();
			String state = txtState.getText();
			String city = txtCity.getText();
			String zipCode = txtZip.getText();
			String phone = txtPhone.getText();

			if(memberId.length() == 0 || firstName.length() == 0 || lastName.length() == 0){
				toast("MemberId, FirstName and LastName fields should not be empty!");
				return;
			}
			if(zipCode == "" || Integer.parseInt(zipCode) == 0 ){
				toast("Please, enter valid zipcode!");
				return;
			}
			try{
				if(phone=="" || Integer.parseInt(phone) == 0)
				{
					toast("Please, enter valid phone number");
					return;
				}
			}	
			catch(NumberFormatException e){
				toast("Please, enter valid phone number!");
				return;
			}


			LibraryMember member = new LibraryMember(memberId, firstName, lastName, phone, street, city, state, zipCode);

			//This user will be the logged in user
			SystemUser user = (SystemUser)sysUser;
			if(user != null){
				user.addMember(member);
			}else{
				toast("The logged in user is expired!");
				return;
			}

			alertSuccess("Library member saved successfully!");
			clearFields();
		}
		catch(NumberFormatException e){
			toast("Please, enter valid zipcode!");
		}

		catch(Exception e){
			e.printStackTrace();
			toast("An error occurred!");
		}


	}
	public void alertSuccess(String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(msg);
		alert.showAndWait();
	}

	public void toast(String msg){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(msg);
		alert.showAndWait();
	}

	@FXML protected void handleResetButtonAction(ActionEvent event) throws Exception {

		clearFields();
	}

	private void clearFields(){
		txtmember_id.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtStreet.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtZip.setText("");
		txtPhone.setText("");

	}
	@FXML protected void btnSearchClicked (ActionEvent e) throws Exception {
		try{
			String memberID = txtMemberID.getText();

		if(memberID=="")
		{
			UILib.toast("Please enter the Library Member ID!");
		}
		else{
			System.out.println("searching");
			search(memberID);
			//((Node)(e.getSource())).getScene().getWindow().hide();
		}
		}
		catch(Exception e1){
			e1.printStackTrace();
			//toast("An error occurred!");
		}
		//UILib.toast("clicked!");

	}


}
