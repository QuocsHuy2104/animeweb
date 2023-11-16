package application;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import IDAO.INhanVien;
import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import utilities.Email;
import utilities.MessageDigest;
import utilities.Notification;
import utilities.PasswordRegex;

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
	private Pane pane1, pane2;

	@FXML
	private AnchorPane root;

	@FXML
	private Label lblSecond;

	@FXML
	private Rectangle recBackground;

	public int result;

	public PreparedStatement pst = null;

	public void sendMail() {

		if (txtEmail.getText().equals("")) {
			txtEmail.setPromptText("Nhập Email");
			txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			return;
		}
		Email email = new Email();
		if (txtCodeAuth.getText().equals("")) {
			try {
				email.mail(txtEmail.getText(), "Mã Xác Thực");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else if (txtCodeAuth.getText().equals(email.messageMail)){
			pane1.setVisible(false);
			pane2.setVisible(true);
		}

	}
	
	public void cancel() {
		Welcome xc = new Welcome();
		try {
			xc.openLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void auth() throws IOException {
		if (txtNewPass.getText().equals("") || txtConfirm.getText().equals("")) {
			txtNewPass.setPromptText("Nhập Password");
			txtNewPass.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			
			txtConfirm.setPromptText("Nhập Password");
			txtConfirm.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			return;
		}
		
		if (!txtConfirm.getText().equals(txtNewPass.getText())) {
			Notification.alert(AlertType.INFORMATION, "Mật khẩu không trùng khớp");
		} else {
			PasswordRegex regex = new PasswordRegex();
			if (regex.validate(txtNewPass.getText())) {
				try {
					String passMD = MessageDigest.getMD5(txtConfirm.getText());
					Connection conn = JDBCUtil.getConnection();
					try {
						PreparedStatement pst = conn.prepareStatement("select manv from nhanvien where email = ?");
						pst.setString(1, txtEmail.getText());
						ResultSet rs = pst.executeQuery();
						while (rs.next()) {
							String manv = rs.getString("manv");
							INhanVien.getInstance().updatePass(passMD, manv);
						}
						rs.close();
						pst.close();
						JDBCUtil.closeConnection(conn);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					Notification.alert(AlertType.INFORMATION, "Đổi mật khẩu thành công");
					
					Stage stage = (Stage) root.getScene().getWindow();
					stage.close();
					
					Welcome xc = new Welcome();
					xc.openLogin();
					
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		if (!txtNewPass.getText().equals("") || !txtConfirm.getText().equals("")) {
		}
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\application\\backgroundFrom.png");
		recBackground.setFill(new ImagePattern(img));
		
		btnAuth.setOnAction(e -> {
			sendMail();
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
