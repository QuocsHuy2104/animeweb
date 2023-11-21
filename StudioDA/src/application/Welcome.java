package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
		arg0 = new Stage(StageStyle.UNDECORATED);
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
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Studio Application");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
			String css = this.getClass().getResource("style.css").toExternalForm();
			scene.getStylesheets().add(css);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
