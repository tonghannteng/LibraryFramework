package presentation;

import business.Address;
import business.LibraryMember;
import business.SystemUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMember extends Application {

	private static Object sysUser = null;
	public static String firstName;
	@FXML
	private TextField txtmember_id;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtZip;
	@FXML
	private TextField txtPhone;
	public static LibraryMember member;

	public AddMember() {
		member = null;
	}

	public AddMember(LibraryMember member) {
		this.member = member;
		firstName = member.getFirstName();
	}

	private void initMember() {

		System.out.println(firstName);
		txtmember_id = new TextField();
		txtmember_id.setText(firstName);
		txtmember_id.commitValue();
		System.out.println("1");
		txtFirstName = new TextField();
		txtFirstName.setText(member.getFirstName());
		System.out.println(member.getFirstName());
		txtLastName = new TextField();
		txtLastName.setText(member.getLastName());

		Address add = member.getAddress();
		txtStreet = new TextField();
		txtStreet.setText(add.getStreet());
		txtState = new TextField();
		txtState.setText(add.getState());
		txtCity = new TextField();
		txtCity.setText(add.getCity());
		txtZip = new TextField();
		txtZip.setText(add.getZipcode());
		txtPhone = new TextField();
		txtPhone.setText(member.getPhone());
		txtmember_id.setDisable(true);
	}

	@Override

	public void init() throws Exception {
		System.out.println("init!");
		if (member != null) {
			initMember();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
		Scene scene = new Scene(root, 500, 700);
		sysUser = primaryStage.getUserData();
		primaryStage.show();
		// if(member!=null){
		// initMember();
		// }
		primaryStage.setResizable(false);
		primaryStage.setTitle("Add Library Member");
		primaryStage.setScene(scene);
		if (member != null) {
			initMember();
		}

		if (txtmember_id != null)
			txtmember_id.setText("someText");
		if (member != null) {
			initMember();
		}
		primaryStage.setResizable(false);
		primaryStage.setTitle("Add Library Member");
		primaryStage.setScene(scene);
		// if(member!=null){
		// initMember();
		// }

	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception {
		try {
			String memberId = txtmember_id.getText();
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String street = txtStreet.getText();
			String state = txtState.getText();
			String city = txtCity.getText();
			String zipCode = txtZip.getText();
			String phone = txtPhone.getText();

			if (memberId.length() == 0 || firstName.length() == 0 || lastName.length() == 0) {
				toast("MemberId, FirstName and LastName fields should not be empty!");
				return;
			}
			// System.out.println(Integer.parseInt(zipCode) + zipCode);
			if (zipCode == "" || Integer.parseInt(zipCode) == 0) {
				toast("Please, enter valid zipcode!");
				return;
			}
			try {
				if (phone == "" || Integer.parseInt(phone) == 0) {
					toast("Please, enter valid phone number");
					return;
				}
			} catch (NumberFormatException e) {
				toast("Please, enter valid phone number!");
				return;
			}
			LibraryMember member = new LibraryMember(memberId, firstName, lastName, phone, street, city, state,
					zipCode);
			System.out.println("Member id:"+memberId);
			member.save();
			alertSuccess("Library member saved successfully!");
			clearFields();
		} catch (NumberFormatException e) {
			toast("Please, enter valid zipcode!");
		}

		catch (Exception e) {
			e.printStackTrace();
			toast("An error occurred!");
		}
	}

	public void alertSuccess(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(msg);
		alert.showAndWait();
	}

	public void toast(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(msg);
		alert.showAndWait();
	}

	@FXML
	protected void handleResetButtonAction(ActionEvent event) throws Exception {

		clearFields();
	}

	private void clearFields() {
		txtmember_id.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtStreet.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtZip.setText("");
		txtPhone.setText("");

	}
}
