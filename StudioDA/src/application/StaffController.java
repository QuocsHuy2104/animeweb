package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import IDAO.INhanVien;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.NhanVienModel;
import utilities.Notification;

public class StaffController implements Initializable {
	
	@FXML
	private TextField txtID, txtName, txtWage, txtPass, txtPhone;
	
	@FXML
	private TextArea txtAddress;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private RadioButton rbtnTP, rbtnNV;
	
	@FXML
	private Button addStaff, updateStaff, clean;
	
	@FXML
	private HBox hboxRoles, hboxPass, hboxDate;
	
	@FXML
	private AnchorPane root;
	
	public void save() {
	
		String id = txtID.getText().trim();
		String ten = txtName.getText().trim();
		String diachi = txtAddress.getText();
		String sdt = txtPhone.getText().trim();
		Float luong = Float.valueOf(txtWage.getText());
		LocalDate local = datePicker.getValue();
		String ngayNhan = local.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		String pass = txtPass.getText();
		boolean roles = rbtnTP.isSelected() ? true : false;
		
		if (id.equals("") || ten.equals("") || diachi.equals("") || txtWage.getText().equals("") || ngayNhan.equals("") || sdt.equals("") || pass.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}
		
		NhanVienModel nv = new NhanVienModel(id, ten, diachi, sdt, luong, ngayNhan, pass, roles);
		INhanVien.getInstance().insert(nv);
		
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}
	
	public void update() {
		String id = txtID.getText().trim();
		String ten = txtName.getText().trim();
		String diachi = txtAddress.getText();
		String sdt = txtPhone.getText().trim();
		Float luong = Float.valueOf(txtWage.getText());
		LocalDate local = datePicker.getValue();
		String ngayNhan = local.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		String pass = txtPass.getText();
		boolean roles = rbtnTP.isSelected() ? true : false;
		
		if (id.equals("") || ten.equals("") || diachi.equals("") || txtWage.getText().equals("") || sdt.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}
		
		NhanVienModel model = new NhanVienModel(id, ten, diachi, sdt, luong, ngayNhan, pass, roles);
		INhanVien.getInstance().update(model);
		
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
		
	}
	
	public void clean() {
		txtID.setText("");
		txtName.setText("");
		txtAddress.setText("");
		txtPhone.setText("");
		txtWage.setText("");
		rbtnNV.setSelected(true);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleGroup group = new ToggleGroup();
		rbtnTP.setToggleGroup(group);
		rbtnNV.setToggleGroup(group);
		rbtnNV.setSelected(true);
	}
}
