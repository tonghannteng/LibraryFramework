package presentation;

import business.Book;
import business.UILib;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class AddCopyForm extends Application {
	private Book book = null;
	@FXML private TextField isbnText;
	@FXML private Label isbn, title,  copynum, author, popular;
	@FXML private Label bid;

	@FXML protected void handleAddButtonAction(ActionEvent event) {
		if(book == null){
			UILib.toast("No book selected");
			return;
		}

		boolean ret = book.addCopy(book.numberOfCopies()+1);
		if(ret == false){
			UILib.toast("Add copy faield");
			return;
		}
		book.save();
		this.setBookInfo();
	}
	@FXML protected void handleSearchButtonAction(ActionEvent event) {

		book = Book.bookWithISBN(this.isbnText.getText());
		if(book == null){
			UILib.toast("No such book");
			return;
		}

		this.setBookInfo();
	}
	public void setBookInfo(){
		isbn.setText(book.getISBN());
		title.setText(book.getTitle());
		bid.setText(Long.toString(book.getId()));
		copynum.setText(Integer.toString(book.numberOfCopies()));
		author.setText(book.getAuthor());
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AddCopyForm.fxml"));
		Scene scene = new Scene(root, 550, 400);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Add Lendable Copy");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
