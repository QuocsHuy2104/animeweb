package application;

import IDAO.INhanVien;
import IDAO.ISanPham;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.SanPhamModel;
import utilities.Notification;

public class ProductController {

	@FXML
	private TextField txtID, txtName, txtTradeMark, txtPrice, txtService, IDStaff;

	@FXML
	private TextArea txtDescribe;
	
	@FXML
	private AnchorPane root;

	public void addProduct() {
		String id = txtID.getText();
		String name = txtName.getText();
		String trademark = txtTradeMark.getText();
		Float price = Float.parseFloat(txtPrice.getText());
		String describe = txtDescribe.getText();
		String service = txtService.getText();
		int idStaff = Integer.parseInt(IDStaff.getText());

		if (id.equals("") || name.equals("") || trademark.equals("") || txtPrice.getText().equals("") || describe.equals("")
				|| service.equals("") || IDStaff.getText().equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}
		
		SanPhamModel sp = new SanPhamModel(id, name, trademark, price, describe, service, idStaff);
		ISanPham.getInstance().insert(sp);
	}
	
	public void updateProduct() {
		String id = txtID.getText();
		String name = txtName.getText();
		String trademark = txtTradeMark.getText();
		Float price = Float.parseFloat(txtPrice.getText());
		String describe = txtDescribe.getText();
		String service = txtService.getText();
		int idStaff = Integer.parseInt(IDStaff.getText());

		if (id.equals("") || name.equals("") || trademark.equals("") || txtPrice.getText().equals("") || describe.equals("")
				|| service.equals("") || IDStaff.getText().equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}
		
		SanPhamModel sp = new SanPhamModel(id, name, trademark, price, describe, service, idStaff);
		ISanPham.getInstance().update(sp);
		

		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}
	
	public void clean() {
		txtID.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtService.setText("");
		txtTradeMark.setText("");
		txtDescribe.setText("");
	}
	
}
