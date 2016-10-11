package business;

import presentation.*;
import javafx.application.Application;

import javafx.stage.Stage;
public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

//		LibraryMember member = new LibraryMember("323", "John4");
//		LibraryMember member = new LibraryMember("3434", "John4");
//
//		ArrayList<Author> authors = new ArrayList();
//		authors.add(new Author("Misss", "Guuu", "crreeee", "TOTOTOTTO"));
//
//		Book b = new Book("COBY", "0913", authors);
//		Publication p = b;
//
//		LendableCopy c = new LendableCopy(343, p);
//		c.setPublication(p);
//		c.setCopyId(1);
		//member.checkout(c, LocalDate.now(), LocalDate.now().plus(30, ChronoUnit.DAYS));

//		DataAccessFacade accessFacade = new DataAccessFacade();
//		accessFacade.saveBook("bukk", b);

//		accessFacade.saveLibraryMember(member.getMemberId(), member);
//		accessFacade.saveLendableCopy(4455, c);
		//System.out.println(accessFacade.readLendableCopy(4455));
		//accessFacade.saveLendableCopy(4455, c);
//		System.out.println(accessFacade.readLendableCopy(4455));

		//LibraryMember m = accessFacade.readLibraryMember("john.ser");
		//System.out.println(m.toString());


//		LoginForm lf = new LoginForm();
//		lf.start(primaryStage);

//		LoginForm lf = new LoginForm();
//		lf.start(primaryStage);

//		LoginForm lf = new LoginForm();
//		lf.start(primaryStage);

		//ArrayList<FineEntry> records = new ArrayList();
		//records.add(new FineEntry(c, LocalDate.now(),LocalDate.now(),80, new CheckoutRecordEntry(c, LocalDate.now(), LocalDate.now())));

		//FineRecord  rec = new FineRecord(member);
		//accessFacade.saveFineRecord(member.getMemberId(), rec);
		LoginForm lf = new LoginForm();
		lf.start(primaryStage);

	}

}
