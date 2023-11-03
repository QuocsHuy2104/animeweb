package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import IDAO.IHoaDon;
import connectJDBC.JDBCUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.HoaDonModel;

public class BillController implements Initializable {
	@FXML
	private TextField txtID, IDStaff;

	@FXML
	private DatePicker date;

	@FXML
	private ChoiceBox<Integer> choiceBox;

	@FXML
	private ChoiceBox<Float> choicePaid;
	
	@FXML
	private AnchorPane root;

	public void loadChoiceBox() {
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select id_khachhang from hoadon");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_khachhang");
				choiceBox.getItems().add(id);
			}
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		choiceBox.setOnAction(this::getPaid);
	}

	public void getPaid(ActionEvent event) {
		choicePaid.getItems().clear();
		int idClient = choiceBox.getValue();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select giadichvu from SANPHAM \r\n"
					+ "inner join KHACHHANG on KHACHHANG.id_sanpham = SANPHAM.id_sanpham\r\n"
					+ "where ID_KhachHang = ? ");
			pst.setInt(1, idClient);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				float paid = rs.getFloat("giadichvu");
				choicePaid.getItems().add(paid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBill() {
		String mahd = txtID.getText();
		LocalDate local = date.getValue();
		String ngay = local.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		float thanhtoan = choicePaid.getValue();
		int idClient = choiceBox.getValue();
		int idStaff = Integer.parseInt(IDStaff.getText());
		
		HoaDonModel model = new HoaDonModel(mahd, ngay, thanhtoan, idClient, idStaff);
		IHoaDon.getInstance().insert(model);
	}
	
	public void updateBill() {
		String mahd = txtID.getText();
		LocalDate local = date.getValue();
		String ngay = local.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		float thanhtoan = choicePaid.getValue();
		int idClient = choiceBox.getValue();
		int idStaff = Integer.parseInt(IDStaff.getText());
		
		HoaDonModel model = new HoaDonModel(mahd, ngay, thanhtoan, idClient, idStaff);
		IHoaDon.getInstance().update(model);
		

		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
		
	}
	
	public void clean() {
		txtID.setText("");
		IDStaff.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadChoiceBox();
	}

}
