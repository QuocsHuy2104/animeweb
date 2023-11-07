package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Alert extends Application {
	
	@FXML
	private Button btnOK, btnCancel;
	
	@FXML 
	public static Label lblHeader, lblContent, lblType;
	
	@FXML
	private ImageView img;
	
	@FXML
	private VBox root;
	
	private Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Error.fxml"));
			primaryStage = new Stage(StageStyle.UNDECORATED);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Studio Application");
			primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			primaryStage.show();
			primaryStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void close(ActionEvent e) {
		stage = (Stage) root.getScene().getWindow();
		stage.close();
	}
}
