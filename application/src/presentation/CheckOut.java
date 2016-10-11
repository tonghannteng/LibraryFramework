package presentation;

import java.beans.EventHandler;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.time.temporal.ChronoUnit;
import business.Book;
import business.Booking;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LendableCopy;
import business.LibraryMember;
import business.UILib;
import dataaccess.DataAccessFacade;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CheckOut extends Application implements Initializable
{

	private Book book;
	private CheckoutRecord rc;
	@FXML private Button checkin;
	@FXML private Button finerecords;
	private LibraryMember currentMem;
	@FXML private Label memberName;
	@FXML
	private TextField mem_id;
	@FXML
	private TextField isbn_num;

	@FXML
	private TableView<CheckoutRecordEntry> checkout_records;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> due_date;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> checkout_date;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> title;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> rtstatus;


	@FXML private Label isbn, btitle,  copynum, author, popular, availablenum;
	@FXML private Label bid;

	public void setBookInfo(){
		isbn.setText(book.getISBN());
		btitle.setText(book.getTitle());
		bid.setText(Long.toString(book.getId()));
		copynum.setText(Integer.toString(book.numberOfCopies()));
		author.setText(book.getAuthor());
		availablenum.setText(Integer.toString(book.getAvailableNumber()));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("CheckOut.fxml"));
		Scene scene = new Scene(root, 589, 720);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Book Check out");
		primaryStage.setScene(scene);
		primaryStage.show();

		}

	@FXML protected void checkFineRecords(ActionEvent event) throws Exception {
		FineRecordsController rc = new FineRecordsController();
		Stage stage = new Stage();
	     rc.start(stage);
	}

	@FXML protected void handleCheckin(ActionEvent event) throws Exception {
		CheckoutRecordEntry entry =  checkout_records.getSelectionModel().getSelectedItem();
		if(entry == null){
			System.out.println("Entry is null");
			return;
		}
		if(entry.isReturned()){
			UILib.toast("This book has been returned!");
			return;
		}
		entry.setReturnedDate(LocalDate.now());;
		entry.setReturned(true);
		//rc.save();
		rc.customer.save();
		Book book = (Book)entry.getCopy().getItem();
		book.checkinCopy(entry.getCopy());
		checkout_records.refresh();
		Booking bk = (Booking) Booking.sharedBooking();
		bk.notifyCustomer(book);
		if(entry.getDueDate().isBefore(entry.getReturnedDate())){
			//fine
			double fineperday = 1.0;
			int days = entry.getReturnedDate().getDayOfYear() - entry.getDueDate().getDayOfYear();
			if(days > 0){
				currentMem.addFineEntry(entry, fineperday * days);
			}
		}
	}
	@FXML protected void handleSearchMem(ActionEvent event) throws Exception {

		String memberId = mem_id.getText();
		LibraryMember lm = (LibraryMember)LibraryMember.getInstance(LibraryMember.class.getName(), memberId);
		if(lm!=null){
			memberName.setText(lm.getFirstName() + " " + lm.getLastName());
			populateTable(memberId, lm);
		}else{
			UILib.toast("No such Library Member!");
		}
	}

	ObservableList<CheckoutRecordEntry> b;

	private void populateTable(String memberId, LibraryMember lm) {
		currentMem=lm;
		rc = lm.getCheckoutRecord();
		if(rc!=null)
		{
			b = FXCollections.observableArrayList();

			List r = rc.getRecords();
			for(int i = 0; i < r.size(); i++){
				CheckoutRecordEntry e = (CheckoutRecordEntry)r.get(i);
				b.add(e);
			}

			title.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry,String>("title"));
			due_date.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry,String>("dueDate"));
			checkout_date.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry,String>("checkoutDate"));
			rtstatus.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry,String>("returnedDate"));
			checkout_records.setItems(b);

		}
		else
			memberName.setText("No Checkout Record Data");
			}

	@FXML protected void handleSearchBookCopy(ActionEvent event) throws Exception {

		String isbn_number = isbn_num.getText();

		book = Book.bookWithISBN(isbn_number);
		if(book == null){
			UILib.toast("No such book");
			return;
		}

		this.setBookInfo();

	}

	@FXML protected void handleResetButtonAction(ActionEvent event) throws Exception {

		mem_id.setText("");
		isbn_num.setText("");

		checkout_records.setItems(null);

		isbn.setText("");
		btitle.setText("");
		bid.setText("");
		copynum.setText("");
		author.setText("");
		availablenum.setText("");
	}

	@FXML protected void handleDoneButtonAction(ActionEvent event) throws Exception {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@FXML protected void handleCheckOutButton(ActionEvent event) throws Exception {

		if(isbn.getText().length() == 0){
			UILib.toast("Please input the ISBN first");
			return;
		}
		LendableCopy copy = book.checkoutCopy();
		if(copy != null){
			Book b = (Book)copy.getItem();
			int d = b.getMaxCheckoutLength();
			System.out.printf("day=%d\n", d);
			currentMem.checkOut(copy,  LocalDate.now(), LocalDate.now().plusDays(d));
			currentMem.save();
			UILib.toast("Successfull!");
		}
		else{
			Booking booking = (Booking)Booking.sharedBooking();
			booking.attach(currentMem, book);
			UILib.toast("Sorry, No Available Copies for this Book!We'll notify you when this book avaibale");
		}
		populateTable(currentMem.getMemberId(), currentMem);
		this.setBookInfo();
	}

	@FXML
	public void printToConsole(ActionEvent event) throws Exception{
		System.out.println("  	  |     "+"Book Title"+"  	  |     "+"Checkout Date"+"         |    "+"Due Date"+"         |     \n\n");
		for(CheckoutRecordEntry e: b)
		{
			System.out.println("  	  |         "+e.getTitle()+"  	  |         "+e.getCheckoutDate()+"         |     "+e.getDueDate()+"     |     \n");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}