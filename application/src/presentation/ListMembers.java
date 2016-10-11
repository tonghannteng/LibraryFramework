package presentation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.Address;
import business.LibraryMember;
import business.PersonalInfo;
import dataaccess.LibraryMemberDataAccess;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListMembers extends Application implements Initializable{

	@FXML
	private TableColumn<PersonalInfo, String> name;

	@FXML
	private TableColumn<PersonalInfo, String> address;

	@FXML
	private TableColumn<LibraryMember, String> id;

	@FXML
	private TableColumn<PersonalInfo, String> phone;

	@FXML
	private TableView<LibraryMember> members_table;

	ObservableList<LibraryMember> lml = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List members =LibraryMember.getAllInstances(LibraryMember.class.getName());
		this.lml.addAll(members);
		populateTable();
	}

	private void populateTable() {

				name.setCellValueFactory(new PropertyValueFactory<PersonalInfo,String>("firstName"));
				id.setCellValueFactory(new PropertyValueFactory<LibraryMember,String>("memberId"));
				address.setCellValueFactory(new PropertyValueFactory<PersonalInfo,String>("address"));
				phone.setCellValueFactory(new PropertyValueFactory<PersonalInfo,String>("phone"));

				members_table.setItems(lml);
		}

	@Override
	public void start(Stage arg0) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ListMembers.fxml"));
		Scene scene = new Scene(root);
		arg0.setResizable(false);
		arg0.setTitle("Library Members List");
		arg0.setScene(scene);
		arg0.show();
	}

}
