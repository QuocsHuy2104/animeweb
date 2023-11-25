package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageLocked extends Application implements Initializable {

	@FXML
	private AnchorPane root;

	@FXML
	private Button btnRetry, btnAuth;

	@FXML

	private Stage stage;

	@Override
	public void start(Stage arg0) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MessageLock.fxml"));
		arg0 = new Stage(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void close() {
		stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnAuth.setOnAction(event -> {
			close();
		});

		btnRetry.setOnAction(event -> {
			close();
		});
	}

}
