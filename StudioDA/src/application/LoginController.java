package application;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import connectJDBC.JDBCUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.MessageDigest;

public class LoginController implements Initializable {

	@FXML
	public TextField txtUser;

	@FXML
	public TextField txtDatabase, txtServer, txtPassDB;

	@FXML
	private PasswordField txtPass;

	@FXML
	private Button btnLogin, btnLogout;

	@FXML
	private javafx.scene.shape.Rectangle recShow;

	@FXML
	private Pane paneConnect;

	@FXML
	private CheckBox myCheckBox;

	@FXML
	private AnchorPane root;

	Stage stage;
	Scene scene;
	Parent root1;
	
	int cnt = 0;

	public static int roles;

	public static String server, database, passDB, user, nameStaff, email, password;

	public void LoginEvent() throws NoSuchAlgorithmException {
		server = txtServer.getText();
		database = txtDatabase.getText();
		passDB = txtPassDB.getText();
		user = txtUser.getText();
		password = MessageDigest.getMD5(txtPass.getText());

		// check username
		if (txtUser.getText().equals("")) {
			txtUser.setPromptText("Vui lòng nhập tên đăng nhập");
		}
		// check password
		if (txtPass.getText().equals("")) {
			txtPass.setPromptText("Vui lòng nhập mật khẩu");
			return;
		}

		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement("select * from nhanvien where manv = ? and pass = ?");
			pst.setString(1, txtUser.getText());
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				roles = rs.getInt("vaitro");

				nameStaff = rs.getString("TenNV");
				email = rs.getString("email");

				String matkhau = rs.getString("pass");

				stage = (Stage) root.getScene().getWindow();
				stage.close();

				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
					stage = new Stage(StageStyle.UNDECORATED);
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
					stage.show();
					stage.setResizable(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;

			} else {
				try {
					cnt ++;
					
					MessageLocked ml = new MessageLocked();
					ml.start(stage);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void openForgotPassword() {
		stage = (Stage) root.getScene().getWindow();
		stage.close();

		try {
			Parent forgot = FXMLLoader.load(getClass().getResource("forgotPass.fxml"));
			stage = new Stage();
			Scene scene = new Scene(forgot);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Quên mật khẩu");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/logo.png")));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openPaneConnect() {
		paneConnect.setVisible(true);
	}

	public void exitForm() {
		stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogout.setOnAction(event -> {
			exitForm();
		});
	}
}
