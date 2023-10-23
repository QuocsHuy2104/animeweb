package application;

import java.io.IOException;

import IDAO.INhanVien;
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

public class ChangePassController {

	@FXML
	private TextField txtUser;

	@FXML
	private PasswordField txtCurrentPass, txtNewPass, txtAuth;
	
	@FXML
	private Button btnOK;
	
	@FXML
	private AnchorPane parent;
	
	public void changePass(ActionEvent event) {
		String user = txtUser.getText().trim();
		String currentPass = txtCurrentPass.getText().trim();
		String newPass = txtNewPass.getText().trim();
		String authPass = txtAuth.getText().trim();
		
		// check input textfield
		
		if (user.equals("")) {
			txtUser.setPromptText("Nhập tên tài khoản");
		}
		
		if (currentPass.equals("")) {
			txtCurrentPass.setPromptText("Nhập mật khẩu cũ");
		}
		
		if (newPass.equals("")) {
			txtCurrentPass.setPromptText("Nhập mật khẩu mới");
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
		System.out.println("Update successfull");
		
		Stage stage = (Stage) parent.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
