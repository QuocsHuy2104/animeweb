package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ForgotPassController {
	
	@FXML
	private TextField txtUserForgot;
	
	@FXML
	private PasswordField txtPassForgot, txtAuth;
	
	@FXML
	private Button btnAuth;
	
	@FXML
	private ImageView imgView;
	
	@FXML
	private Label lblTitle;
	
	public void validate(ActionEvent e) {
		
		if (!txtUserForgot.getText().equals("") || !txtPassForgot.getText().equals("") || !txtAuth.getText().equals("")) {
			txtUserForgot.setStyle("-fx-border-color: white ; -fx-border-width: 2px ; -fx-text-inner-color: white;");
			txtPassForgot.setStyle("-fx-border-color: white ; -fx-border-width: 2px ; -fx-text-inner-color: white;");
			txtAuth.setStyle("-fx-border-color: white ; -fx-border-width: 2px ; -fx-text-inner-color: white;");
		}
		
		if (txtUserForgot.getText().equals("")) {
			txtUserForgot.setPromptText("Nhập tên đăng nhập");
			txtUserForgot.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
		}
		
		if (txtPassForgot.getText().equals("")) {
			txtPassForgot.setPromptText("Nhập mật khẩu mới");
			txtPassForgot.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
		}
		
		if (txtAuth.getText().equals("")) {
			txtAuth.setPromptText("Nhập lại mật khẩu");
			txtAuth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
		}	
			
		if (!txtAuth.getText().equalsIgnoreCase(txtPassForgot.getText())) {
			txtAuth.setPromptText("Mật khẩu không trùng");
			txtAuth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
		}
		
//		Connection conn = JDBCUtil.getConnection();
//		try {
//			PreparedStatement pst = conn.prepareStatement("select * from login where username = ?");
//			pst.setString(1, txtUserForgot.getText());
//			ResultSet rs = pst.executeQuery();
//			while (rs.next()) {
//				if (!txtUserForgot.getText().equals(rs.getString("username"))) {
//					lblError.setText("Tên đăng nhập không tồn tại");
//					return;
//				}
				
//				if (txtUserForgot.getText().equals(rs.getString("username"))) {
//					new Thread(new Runnable() {
//						
//						@Override
//						public void run() {
//							for (int i = 40; i <= 300; i++) {
//								imgView.setLayoutX(i);
//								Thread.sleep(3);
//							}
//						}
//					}).start();
//					
//					lblTitle.setVisible(false);
//					txtUserForgot.setVisible(false);
//					txtPassForgot.setVisible(false);
//					txtAuth.setVisible(false);
//					btnAuth.setVisible(false);
//				}	
		
//			}
//			
//			pst.close();
//			rs.close();
//			JDBCUtil.closeConnection(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		
		
		
	}
	
}
