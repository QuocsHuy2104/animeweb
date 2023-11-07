package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

public class Welcome extends Application {
	
	@Override
	public void start(Stage arg0) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		Scene scene = new Scene(root);
		arg0.setScene(scene);
		arg0.show();
		arg0.setResizable(false);

	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
