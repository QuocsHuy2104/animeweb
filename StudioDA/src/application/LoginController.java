package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import IDAO.INhanVien;
import connectJDBC.JDBCUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utilities.MessageDigest;

public class LoginController implements Initializable {

	@FXML
	private MediaView mediaView;

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

	Set<String> posibles = new HashSet<String>();
	private AutoCompletionBinding<String> autoCompletionBindings;

	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;

	String[] users = {};

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
			PreparedStatement pst = conn.prepareStatement("select * from nhanvien where manv = ?");
			pst.setString(1, txtUser.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				if (!password.equals(rs.getString("pass"))) {
					cnt++;

					if (cnt < 5) {
						MessageLocked ml = new MessageLocked();
						ml.start(stage);
						return;
					}

					INhanVien.getInstance().locked(user);
					locked();
					return;
				}
				
				if (rs.getInt("trangthai") == 0) {
					INhanVien.getInstance().locked(user);
					locked();
					return;
				}

				roles = rs.getInt("vaitro");
				nameStaff = rs.getString("TenNV");
				email = rs.getString("email");

				mediaPlayer.pause();

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
				MessageLocked ml = new MessageLocked();
				ml.start(stage);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void openForgotPassword() {
		mediaPlayer.pause();
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

	public void saveLogin() {
		Collections.addAll(posibles, users);
		autoCompletionBindings = TextFields.bindAutoCompletion(txtUser, posibles);

		txtUser.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case TAB:
				learner(txtUser.getText());
				break;
			default:
				break;
			}
		});
	}

	public void learner(String text) {
		posibles.add(text);

		if (autoCompletionBindings != null) {
			autoCompletionBindings.dispose();
		}

		autoCompletionBindings = TextFields.bindAutoCompletion(txtUser, posibles);
	}

	public void check() {
		if (myCheckBox.isSelected()) {
			saveLogin();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogout.setOnAction(event -> {
			exitForm();
		});

		myCheckBox.setOnAction(event -> {
			check();
		});

		file = new File("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\video2.mp4");
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);

		try {
			playMedia();
		} catch (IOException e) {
		}
	}

	public void locked() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Locked.fxml"));
			stage = new Stage(StageStyle.UNDECORATED);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playMedia() throws IOException {
		mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public void rememberPassword() {
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("RememberPassword.txt"));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
