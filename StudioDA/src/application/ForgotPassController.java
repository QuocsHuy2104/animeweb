package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ForgotPassController {

	@FXML
	private TextField txtUserForgot, txtsend;

	@FXML
	private PasswordField txtPassForgot, txtAuth;

	@FXML
	private Button btnAuth;

	@FXML
	private ImageView imgView;

	@FXML
	private Label lblTitle, lblOTP;

	@FXML
	private Pane paneAuth;

	public int result;
	
	public PreparedStatement pst = null;

	public void validate(ActionEvent e) {
		
		if (!txtUserForgot.getText().equals("")) {
			txtUserForgot.setStyle("-fx-border-color: white ; -fx-border-width: 2px ; -fx-text-inner-color: white;");
		}

		if (!txtPassForgot.getText().equals("")) {
			txtPassForgot.setStyle("-fx-border-color: white ; -fx-border-width: 2px ; -fx-text-inner-color: white;");

		}

		if (!txtAuth.getText().equals("")) {
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
			return;
		}

		if (!txtAuth.getText().equalsIgnoreCase(txtPassForgot.getText())) {
			txtAuth.setPromptText("Mật khẩu không trùng");
			txtAuth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			return;
		}

		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from nhanvien";
		String user = txtUserForgot.getText();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (user.trim().equals(rs.getString("manv"))) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							for (int i = 40; i <= 300; i++) {
								imgView.setLayoutX(i);
								try {
									Thread.sleep(3);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							paneAuth.setVisible(true);
						}
					}).start();

					lblTitle.setVisible(false);
					txtUserForgot.setVisible(false);
					txtPassForgot.setVisible(false);
					txtAuth.setVisible(false);
					btnAuth.setVisible(false);
					
					pst = conn.prepareStatement("update nhanvien set pass = ? where manv = ?");
					pst.setString(1, txtAuth.getText().trim());
					pst.setString(2, user.trim());
					
					random();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
	
	public void checkOTP(ActionEvent ex) {
		if (txtsend.getText().equals(Float.valueOf(result).toString())) {
			try {
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Update successfull");
		} else {
			System.out.println("Update fail");
		}
	}

	public void random() {
		while (true) {
			result = (int) Math.floor(Math.random() * 100000);
			lblOTP.setText("Mã OTP :: " + result);
			if (result > 2000) {
				return;
			}
		}

	}
}
