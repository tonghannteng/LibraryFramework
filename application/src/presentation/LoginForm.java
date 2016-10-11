package presentation;

import business.LoginHelper;
import business.SystemUser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginForm extends Application{

	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label txtLoginError;
	@FXML private Button login;
	private static final String LOGIN_FAILED_MSG = "Login failed - username or password is incorrect!";


	@Override
	public void start(Stage primaryStage) throws Exception {

		LoginHelper helper = new LoginHelper();
		Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		Scene scene = new Scene(root, 400, 400);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void startMainForm(Object obj) throws Exception{
		MainForm mf = new MainForm();
		Stage stage = new Stage();
		stage.setUserData(obj);
		mf.start(stage);
	}
	@FXML protected void btnLoginAction(ActionEvent event) throws Exception {
		String user = txtUsername.getText();
		String pass = txtPassword.getText();

		LoginHelper helper = new LoginHelper();
		SystemUser sysUser = new SystemUser();
		if(sysUser.login(user, pass) == true){
			txtLoginError.setText("");
			this.startMainForm(sysUser);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else{
			txtLoginError.setText(LOGIN_FAILED_MSG);
		}


    }
}

