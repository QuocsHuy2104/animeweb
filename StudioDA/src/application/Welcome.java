package application;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Welcome extends Application implements Initializable {

	@FXML
	private Circle circle1, circle2;

	@FXML
	private ProgressBar myProgressBar;

	@FXML
	private Label lblValue;

	@FXML
	private AnchorPane root;

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

	public void transition() {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(circle1);
		rotate.setDuration(Duration.millis(3000));
		rotate.setCycleCount(TranslateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByAngle(360);
		rotate.play();

		RotateTransition rotate1 = new RotateTransition();
		rotate1.setNode(circle2);
		rotate1.setDuration(Duration.millis(3000));
		rotate1.setCycleCount(TranslateTransition.INDEFINITE);
		rotate1.setInterpolator(Interpolator.LINEAR);
		rotate1.setByAngle(-360);
		rotate1.play();
	}

	double i;

	public void setProgress() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (i = 0; i <= 1.01; i += 0.02) {
					int value = (int) (i * 100);

					myProgressBar.setProgress(i);
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							lblValue.setText(value + "%");
						}
					});
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						Stage stage = (Stage) root.getScene().getWindow();
						stage.close();
						try {
							openLogin();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				});
			}
		}).start();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transition();
		setProgress();
	}

	public void openLogin() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
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
						// TODO Auto-generated catch block
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
	}

}
