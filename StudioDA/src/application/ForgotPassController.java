package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ForgotPassController implements Initializable {

	@FXML
	private TextField txtEmail, txtCodeAuth;

	@FXML
	private PasswordField txtConfirm, txtNewPass;

	@FXML
	private Button btnAuth;

	@FXML
	private ImageView imgView;

	@FXML
	private Label lblTitle, lblOTP;

	@FXML
	private Pane paneAuth;

	@FXML
	private AnchorPane root;

	@FXML
	private Label lblSecond;

	@FXML
	private Rectangle recBackground;

	public int result;

	public PreparedStatement pst = null;

	public void validate() {

		if (txtEmail.getText().equals("")) {
			txtEmail.setPromptText("Nhập Email");
			txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
		}

		if (txtCodeAuth.getText().equals("")) {
			txtCodeAuth.setPromptText("Nhập Mã Xác Nhận");
			txtCodeAuth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
		}

		if (txtNewPass.getText().equals("")) {
			txtNewPass.setPromptText("Nhập Mật Khẩu Mới");
			txtNewPass.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			return;
		}

		if (!txtConfirm.getText().equalsIgnoreCase(txtNewPass.getText())) {
			txtConfirm.setPromptText("Mật khẩu không trùng");
			txtConfirm.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			return;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\application\\backgroundFrom.png");
		recBackground.setFill(new ImagePattern(img));
		
		btnAuth.setOnAction(e -> {
			validate();
		});
	}

	public void Back() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();

		try {
			Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(login);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			String css = this.getClass().getResource("styleLogin.css").toExternalForm();
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.setTitle("Đăng nhập Studio Breakfast");
			scene.getStylesheets().add(css);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
