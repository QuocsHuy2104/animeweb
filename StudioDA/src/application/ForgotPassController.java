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

	public static String mail;

	private Stage stage;

	public void sendMail() {

		int cnt = 0;

		if (txtEmail.getText().equals("")) {
			txtEmail.setPromptText("Nhập Email");
			txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-text-inner-color: white;");
			return;
		}

		mail = txtEmail.getText();

		if (INhanVien.getInstance().existMail()) {

			Email email = new Email();
			if (txtCodeAuth.getText().equals("")) {
				try {
					email.mail(txtEmail.getText(), "Mã Xác Thực");
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}

			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 60; i >= 0; i--) {

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (i == 0) {
							email.messageMail = null;
						}
					}
				}
			}).start();

			if (txtCodeAuth.getText().equals(email.messageMail)) {
				pane1.setVisible(false);
				pane2.setVisible(true);
			} else if (email.messageMail == null) {
				Notification.alert(AlertType.CONFIRMATION, "Mã xác đã hết hạn " + cnt);
				return;
			}

		} else {
			Notification.alert(AlertType.ERROR, "Không thuộc email nhân viên");
		}

	}

	public void cancel() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();

		Main main = new Main();
		main.start(stage);
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
			return;
		}

		PasswordRegex regex = new PasswordRegex();
		if (regex.validate(txtConfirm.getText()) == false) {
			Notification.alert(AlertType.ERROR, "Mật khẩu không đúng định dạng");
			return;
		}
		
		try {
			String passMD = MessageDigest.getMD5(txtConfirm.getText());
			Connection conn = JDBCUtil.getConnectionDefault();
			try {
				PreparedStatement pst = conn.prepareStatement("select manv from nhanvien where email like ?");
				pst.setString(1, txtEmail.getText());
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String manv = rs.getString("manv");
					INhanVien.getInstance().forgotPassword(passMD, txtEmail.getText(), manv);
				}
				rs.close();
				pst.close();
				JDBCUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();

			Main main = new Main();
			main.start(stage);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\application\\backgroundFrom.png");
		recBackground.setFill(new ImagePattern(img));
	}

	public void Back() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();

		Main main = new Main();
		main.start(stage);
	}
}
