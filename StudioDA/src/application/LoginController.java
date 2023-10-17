package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			txtUser.setPromptText("Please input username");
			return;
		} 
		// check password
		if (txtPass.getText().equals("")) {
			txtPass.setPromptText("Please input Password");
		}
		
//		Connection conn = JDBCUtil.getConnection();
//		try {
//			PreparedStatement pst = conn.prepareStatement("select * from login");
//			ResultSet rs = pst.executeQuery();
//			while (rs.next()) {
//				if (txtUser.getText().equals(rs.getString("username")) && txtPass.getText().equals(rs.getString("password")) && rs.getByte("role") == 0) {
//					try {
//						Parent staff = FXMLLoader.load(getClass().getResource("Staff.fxml"));
//						stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//						Scene scene = new Scene(staff);
//						stage.setScene(scene);
//						stage.show();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				} else if (txtUser.getText().equals(rs.getString("username")) && txtPass.getText().equals(rs.getString("password")) && rs.getByte("role") == 1) {
//					try {
//						Parent admin = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
//						stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//						Scene scene = new Scene(admin);
//						stage.setScene(scene);
//						stage.show();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		
	}
	
	public void LoginWithGG() {
		Alert adverties = new Alert(AlertType.CONFIRMATION);
		adverties.setTitle("Adverties");
		adverties.setContentText("Login functionality is being updated");
		adverties.setHeaderText("Adverties");
		adverties.setResizable(false);
		adverties.show();
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
