package utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Notification {
	
	public static void alert(AlertType type,String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setAlertType(type);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText(content);
		alert.show();
	}

}
