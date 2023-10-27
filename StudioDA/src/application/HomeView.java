package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeView extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Studio Application");
			primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			primaryStage.show();
			primaryStage.setResizable(false);
			String css = this.getClass().getResource("style.css").toExternalForm();
			scene.getStylesheets().add(css);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
