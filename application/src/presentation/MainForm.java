package presentation;

import business.SystemUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainForm extends Application {

	private static Object sysUser = null;
	@FXML private Button btnAddMember;
	@FXML private Button btnAddBoook;
	@FXML private Button btnAddCopy;
	@FXML private Button btnListMember;
	@FXML private Button btnSearchMember;
	@FXML private Button btnFineRecords;

	Stage prim;
	@Override
	public void start(Stage primaryStage) throws Exception {

		sysUser = primaryStage.getUserData();
		SystemUser member = (SystemUser) sysUser;
		Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));

		if(!member.isAdmin()){
			root = FXMLLoader.load(getClass().getResource("MainFormLibrarian.fxml"));
		}

		if(!member.isLibrarian()){
			root = FXMLLoader.load(getClass().getResource("MainFormAdmin.fxml"));
		}


		Scene scene = new Scene(root, 693, 633);
		primaryStage.setResizable(false);

		String windowTitle = "Welcome - " + member .getName() + "!";

		primaryStage.close();
		primaryStage.setTitle(windowTitle);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	@FXML protected void handleFineRecordsButtonAction(ActionEvent event) throws Exception {
	       FineRecordsController am = new FineRecordsController();
	       Stage stage = new Stage();
	       stage.setUserData(sysUser);
	       am.start(stage);
 }
	@FXML protected void handleAddMemberButtonAction(ActionEvent event) throws Exception {
	       AddMember am = new AddMember();
	       Stage stage = new Stage();
	       stage.setUserData(sysUser);
	       am.start(stage);
    }

	@FXML protected void handleEditMemberButtonAction(ActionEvent event) throws Exception {
	       MemberSearch ms = new MemberSearch();
	       Stage stage = new Stage();
	       stage.setUserData(sysUser);
	       ms.start(stage);
 }
	@FXML protected void handleListMemberButtonAction(ActionEvent event) throws Exception{
	       ListMembers ms = new ListMembers();
	       Stage stage = new Stage();
	       ms.start(stage);
    }
	@FXML protected void handleSearchMemberButtonAction(ActionEvent event)  throws Exception {
	       CheckOut co = new CheckOut();
	       Stage stage = new Stage();
				co.start(stage);
    }
	@FXML protected void handleAddBookButtonAction(ActionEvent event) throws Exception {
		BookForm am = new BookForm();
	       Stage stage = new Stage();
			am.start(stage);
    }
	@FXML protected void handleAddCopyButtonAction(ActionEvent event) throws Exception {
	       AddCopyForm ac = new AddCopyForm();
	       Stage stage = new Stage();
	       ac.start(stage);
    }

	@FXML protected void HandleLogout(ActionEvent event) throws Exception {
		((Node)(event.getSource())).getScene().getWindow().hide();

		LoginForm lf = new LoginForm();
		Stage stage = new Stage();
		lf.start(stage);
	}

}
