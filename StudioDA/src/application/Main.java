package application;
	
import java.security.NoSuchAlgorithmException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			Parent root = loader.load();
			primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			LoginController controller = loader.getController();
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent arg0) {
					if (arg0.getCode() == KeyCode.ENTER) {
						try {
							controller.LoginEvent();
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
					} else if (arg0.getCode() == KeyCode.ESCAPE)
						controller.exitForm();
				}

			});
			primaryStage.show();
			primaryStage.setResizable(false);
			String css = this.getClass().getResource("styleLogin.css").toExternalForm();
			primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			primaryStage.setTitle("Đăng nhập Studio Breakfast");
			scene.getStylesheets().add(css);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
