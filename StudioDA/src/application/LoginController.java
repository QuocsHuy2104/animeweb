package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IDAO.INhanVien;
import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.NhanVienModel;
import utilities.Auth;
import javafx.scene.Node;

public class LoginController {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPass;
	@FXML
	private Button btnLogin;

	@FXML
	private AnchorPane root;

	Stage stage;
	Scene scene;
	Parent root1;

	public void LoginEvent(ActionEvent e) {
		// check username
		if (txtUser.getText().equals("")) {
			txtUser.setPromptText("Vui lòng nhập tên đăng nhập");
			return;
		}
		// check password
		if (txtPass.getText().equals("")) {
			txtPass.setPromptText("Vui lòng nhập mật khẩu");
			return;
		}
		
		NhanVienModel nv = new NhanVienModel();
		INhanVien.getInstance().selectByID(nv);

		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement("select manv, pass, vaitro from nhanvien");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String manv = rs.getString("manv");
				String matkhau = rs.getString("pass");
				int vaitro = rs.getInt("vaitro");
				
				Auth.user = vaitro;
				if (txtUser.getText().equals(manv) && txtPass.getText().equals(matkhau)) {
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
				} else if (!txtPass.getText().equals(matkhau)){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Message error");
					alert.setContentText("Mật khẩu không trùng khớp");
					alert.show();
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
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
