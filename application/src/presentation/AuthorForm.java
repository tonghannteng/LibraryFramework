package presentation;

import business.Author;
import business.UILib;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorForm extends Application{

	@FXML private TextField credential, bio;
	@FXML private TextField txtFirstName;
	@FXML private TextField txtLastName;
	@FXML private TextField txtStreet;
	@FXML private TextField txtCity;
	@FXML private TextField txtState;
	@FXML private TextField txtZip;
	@FXML private TextField txtPhone;


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("AuthorForm.fxml"));
		Scene scene = new Scene(root, 600, 475);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Add Author");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML protected void handleSubmitButtonAction(ActionEvent event) throws Exception
	{
		String firstName = txtFirstName.getText();
		String lastName  = txtLastName.getText();
		String street = txtStreet.getText();
		String state = txtState.getText();
		String city = txtCity.getText();
		String zipCode = txtZip.getText();
		String phone = txtPhone.getText();
		String cred = credential.getText();
		String sbio = bio.getText();

		if(firstName.length() == 0 || lastName.length() == 0){
			UILib.toast("MemberId, FirstName and LastName fields should not be empty!");
			return;
		}
		if(zipCode != "" && Integer.parseInt(zipCode) == 0 ){
			UILib.toast("Please, enter valid zipcode!");
			return;
		}
		Author a = new Author(firstName, lastName, cred, sbio, street, state, city, zipCode, phone);
		//todo: need fix
	}
	@FXML protected void handleResetButtonAction(ActionEvent event) throws Exception {

		clearFields();
	}
	
	private void clearFields(){
		credential.setText("");
		bio.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtStreet.setText("");
		txtState.setText("");
		txtCity.setText("");
		txtZip.setText("");
		txtPhone.setText("");

	}
}
