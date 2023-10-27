package application;

import java.net.URL;
import java.util.ResourceBundle;

import IDAO.IKhachHang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.KhachHangModel;
import utilities.Notification;

public class ClientController implements Initializable {
	
	@FXML
	private TextField txtID, txtName, txtPhone, IDProduct;
	
	@FXML
	private TextArea txtAddress;
	
	@FXML
	private RadioButton rbtnMan, rbtnWoman;
	
	public void addClient() {
		String id = txtID.getText();
		String name = txtName.getText();
		String address = txtAddress.getText();
		String phone = txtPhone.getText();
		boolean gender = rbtnMan.isSelected() ? true : false;
		int idProduct = Integer.parseInt(IDProduct.getText());
		
		if (id.equals("") || name.equals("") || address.equals("") || phone.equals("") || IDProduct.getText().equals("")) {
			Notification.alert(AlertType.WARNING, "Nhập đầy đủ thông tin");
			return;
		}
		
		KhachHangModel kh = new KhachHangModel(id, name, address, phone, gender, idProduct);
		IKhachHang.getInstance().insert(kh);
	}
	
	public void updataClient() {
		String id = txtID.getText();
		String name = txtName.getText();
		String address = txtAddress.getText();
		String phone = txtPhone.getText();
		boolean gender = rbtnMan.isSelected() ? true : false;
		int idProduct = Integer.parseInt(IDProduct.getText());
		
		if (id.equals("") || name.equals("") || address.equals("") || phone.equals("") || IDProduct.getText().equals("")) {
			Notification.alert(AlertType.WARNING, "Nhập đầy đủ thông tin");
			return;
		}
		
		KhachHangModel kh = new KhachHangModel(id, name, address, phone, gender, idProduct);
		IKhachHang.getInstance().update(kh);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleGroup group = new ToggleGroup();
		rbtnMan.setToggleGroup(group);
		rbtnWoman.setToggleGroup(group);
	}

}
