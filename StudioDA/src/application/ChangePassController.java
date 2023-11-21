package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import IDAO.INhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.PasswordRegex;
import application.ErrorForm;

public class ChangePassController implements Initializable {

	@FXML
	private PasswordField txtCurrentPass, txtNewPass, txtAuthPass;

	@FXML
	private Button btnOK;

	@FXML
	private Rectangle recBackground;

	@FXML
	private AnchorPane parent;

	private Stage stage;

	public void changePass(ActionEvent event) {
		String user = LoginController.user;
		String currentPass = txtCurrentPass.getText().trim();
		String newPass = txtNewPass.getText().trim();
		String authPass = txtAuthPass.getText().trim();

		// check input textfield

		if (currentPass.equals("")) {
			txtCurrentPass.setPromptText("Nhập mật khẩu cũ");
		}

		if (newPass.equals("")) {
			txtCurrentPass.setPromptText("Nhập mật khẩu mới");
		}

		PasswordRegex passwordRegex = new PasswordRegex();
		if (passwordRegex.validate(newPass) == false) {
			WarningForm wn = new WarningForm();
			wn.start(stage);
			return;
		}

		if (authPass.equals("")) {
			txtCurrentPass.setPromptText("Xác nhận mật khẩu");
			return;
		}

		if (!newPass.equals(authPass)) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Thông Báo");
			alert.setHeaderText("Thông Báo");
			alert.setContentText("Mật khẩu không trùng khớp");
			return;
		}

		INhanVien.getInstance().updatePass(user, authPass);

		stage = (Stage) parent.getScene().getWindow();
		stage.close();

		Main main = new Main();
		main.start(stage);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recBackground.setFill(new ImagePattern(
				new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\application\\backgroundFrom.png")));
	}

	public void back() {
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
