package business;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UILib {
	public static void toast(String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(msg);
		alert.showAndWait();
	}
}
