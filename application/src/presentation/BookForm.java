package presentation;

import business.Author;
import business.Book;
import business.PublicationFacotry;
import business.UILib;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



public class BookForm extends Application {

	private Book book;
	@FXML private TextField credential, bio;
	@FXML private TextField txtFirstName;
	@FXML private TextField txtLastName;
	@FXML private TextField txtStreet;
	@FXML private TextField txtCity;
	@FXML private TextField txtState;
	@FXML private TextField txtZip;
	@FXML private TextField txtPhone;

	@FXML private TextField title, isbn, author, maxCheckoutLength, copyid;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("BookForm.fxml"));
		Scene scene = new Scene(root, 450, 750);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Add/Edit Book");
		primaryStage.setScene(scene);
		primaryStage.show();
		book = (Book) PublicationFacotry.createPublication("book");;
	}
	public void toast(String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(msg);
		alert.showAndWait();
	}
	@FXML protected void submitAction(ActionEvent event) throws Exception {
		if(title.getText().length() == 0 || isbn.getText().length() == 0 || author.getText().length() == 0 || maxCheckoutLength.getText().length() == 0 || copyid.getText().length() == 0){
			toast("All fields should not be empty!");
			return;
		}
		if(Integer.parseInt(maxCheckoutLength.getText()) == 0 || Integer.parseInt(copyid.getText()) == 0){
			toast("Maximum checkout length and Copy ID should be a number!");
		}
		char[] isb = isbn.getText().toCharArray();
		for(int i = 0; i < isb.length; i++){
			int c = isb[i];
			if(c < '0' || c > '9'){
				toast("ISBN can only contains numbers!");
				return;
			}
		}
		if(book == null){
			book = (Book) PublicationFacotry.createPublication("book");;
		}

	       book.setTitle(title.getText());
	       book.setISBN(isbn.getText());
	       book.setId(System.nanoTime()/1000);
	       book.setMaxCheckoutLength(Integer.parseInt(maxCheckoutLength.getText()));
	       for(int i = 0; i < Integer.parseInt(copyid.getText()); i++){
	    	   book.addCopy(i+1);
	       }
	       book.save();
	       toast("Add book success!");
	       handleResetButtonAction(null);
	}

	@FXML public void handleResetButtonAction(ActionEvent event) {
	        title.setText("");
	        isbn.setText("");
	        author.setText("");
	        maxCheckoutLength.setText("");
	        copyid.setText("");
	        this.clearFields();
	}
	@FXML public void handleAddAuthorButtonAction(ActionEvent event) throws Exception {
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
		if(book == null){
			book = (Book) PublicationFacotry.createPublication("book");
		}
		book.addAuthor(a);
		author.setText(book.getAuthorsName());
		this.clearFields();
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


