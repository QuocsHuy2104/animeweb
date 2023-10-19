package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutControler {
	
	@FXML
	private Button btnClose;
	
	@FXML
	private AnchorPane root;
	
	public void close() {
		try {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
