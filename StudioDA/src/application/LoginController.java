package application;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import IDAO.INhanVien;
import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.NhanVienModel;
import utilities.MessageDigest;
import utilities.Notification;
import javafx.scene.Node;

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

	public static int roles;

	public static String server, database, passDB, user, nameStaff, email, password;

	public void LoginEvent() throws NoSuchAlgorithmException {
		server = txtServer.getText();
		database = txtDatabase.getText();
		passDB = txtPassDB.getText();
		user = txtUser.getText();
		password = txtPass.getText();

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
			PreparedStatement pst = conn.prepareStatement("select * from nhanvien where manv = ?");
			pst.setString(1, txtUser.getText());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				roles = rs.getInt("vaitro");

				nameStaff = rs.getString("TenNV");
				email = rs.getString("email");

				String manv = rs.getString("manv");
				String matkhau = rs.getString("pass");

				if (txtUser.getText().equals(manv) && (MessageDigest.verify(txtPass.getText(), matkhau) || txtPass.getText().equals(matkhau))) {
					stage = (Stage) root.getScene().getWindow();
					stage.close();

					try {
						Parent forgot = FXMLLoader.load(getClass().getResource("Home.fxml"));
						stage = new Stage();
						Scene scene = new Scene(forgot);
						stage.setScene(scene);
						stage.show();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					return;
				}

				// "ten dang nhap khong ton tai"
				if (!manv.equals(txtUser.getText())) {
					Notification.alert(AlertType.WARNING, "Ten đăng nhập không tồn tại");
				}

				// sai mat khau
				if (!txtPass.getText().equals(matkhau)) {
					Notification.alert(AlertType.WARNING, "Mật khẩu không chính xác");
					return;
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
