package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import IDAO.IHoaDon;
import IDAO.IKhachHang;
import IDAO.INhanVien;
import IDAO.ISanPham;
import connectJDBC.JDBCUtil;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.HoaDonModel;
import model.KhachHangModel;
import model.NhanVienModel;
import model.SanPhamModel;
import utilities.Notification;

public class HomeController implements Initializable {

	@FXML
	private ImageView imageSignout, imageHeart, imgHome, imgStaff, imgClient, imgProduct, imgRevenue, imgBill, imgPaid,
			setting;

	@FXML
	private Pane MenuPane, ContentPane, paneSetting, paneStaff, paneHome, paneClient, paneProduct, paneRevenue,
			paneBill, paneDonate, pane1, pane2, pane3, pane4, pane5;

	@FXML
	private Rectangle recHome, recStaff, recPaid, recProduct, recRevenue, recBill, recClient, recPoster, recWebsite,
			recMusic, rec1, rec2, rec3, rec4, recPoster1, recPoster2, recPoster3, recPoster4;

	@FXML
	private Button btnHome, btnStaff, btnClient, btnProduct, btnRevenue, btnBill, btnPaid;

	@FXML
	private Line lineMenu;

	@FXML
	private PieChart pieChart;

	@FXML
	private AnchorPane root;

	@FXML
	private Circle circleLogo;

	@FXML
	private Button btnChangePass, btnAddStaff, btnEditStaff, btnDellStaff, addStaff, updateStaff, removeStaff, btnAbout,
			refreshTableStaff, btnInsertPro;

	@FXML
	private ComboBox<String> cbbBackground;

	// Table Staff

	@FXML
	private TableView<NhanVienModel> tableStaff;

	@FXML
	private TableColumn<NhanVienModel, String> idCol;

	@FXML
	private TableColumn<NhanVienModel, String> nameCol;

	@FXML
	private TableColumn<NhanVienModel, String> addressCol;

	@FXML
	private TableColumn<NhanVienModel, String> phoneCol;

	@FXML
	private TableColumn<NhanVienModel, Float> wageCol;

	// Table Client

	@FXML
	private TableView<KhachHangModel> tableClient;

	@FXML
	private TableColumn<KhachHangModel, String> idClientCol;

	@FXML
	private TableColumn<KhachHangModel, String> nameClientCol;

	@FXML
	private TableColumn<KhachHangModel, String> addressClientCol;

	@FXML
	private TableColumn<KhachHangModel, String> phoneClientCol;

	@FXML
	private TableColumn<KhachHangModel, Boolean> genderClientCol;

	// Table Product

	@FXML
	private TableView<SanPhamModel> tableProduct;

	@FXML
	private TableColumn<SanPhamModel, String> idProCol;

	@FXML
	private TableColumn<SanPhamModel, String> nameProCol;

	@FXML
	private TableColumn<SanPhamModel, Float> wageProCol;

	@FXML
	private TableColumn<SanPhamModel, String> markProCol;

	@FXML
	private TableColumn<SanPhamModel, String> serviceProCol;

	// Table Bill

	@FXML
	private TableView<HoaDonModel> tableBill;

	@FXML
	private TableColumn<HoaDonModel, String> idBillCol;

	@FXML
	private TableColumn<HoaDonModel, Date> dayBillCol;

	@FXML
	private TableColumn<HoaDonModel, Float> paidBillCol;

	@FXML
	private TableColumn<HoaDonModel, Integer> idClientCols;

	@FXML
	private TableColumn<HoaDonModel, Integer> idStaffCols;

	@FXML
	private LineChart myLineChart;

	@FXML
	private Label lblBill, lblDate, lblName, lblService, lblPrice, lblMoney, lblPaid;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabBill;

	@FXML
	private TextField txtFindStaff, txtFindClient, txtFindProduct, txtFindBill;

	private ObservableList<NhanVienModel> staff;

	private ObservableList<KhachHangModel> client;

	private ObservableList<SanPhamModel> product;

	private ObservableList<HoaDonModel> bill;

	Stage stage;
	Scene scene;
	Parent root1;

	// Code Handle event from

	public void convertScene() {

	}

	public void convertHeart() {
		Image img = new Image("C:/Users/HP/workspage-udpm/StudioDA/src/image/love-you.png");
		imageHeart.setImage(img);
		paneDonate.setVisible(true);
	}

	public void setting() {
		paneSetting.setVisible(true);
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\cogwheel.png");
		setting.setImage(img);
	}

	public void closeSetting() {
		paneSetting.setVisible(false);
		paneDonate.setVisible(false);
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\metal-gear.png");
		setting.setImage(img);
	}

	public void changeBackground() {

	}

	public void openHome() {

		paneStaff.setVisible(false);
		paneHome.setVisible(true);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(false);
		paneBill.setVisible(false);

		hiddenRecControl();
		recHome.setVisible(true);
		btnHome.setStyle("-fx-text-fill: orange; -fx-background-color: none");
		imgHome.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\home-page.png"));

	}

	public void openStaff() {
		if (LoginController.roles == 1) {

			paneStaff.setVisible(true);
			paneHome.setVisible(false);
			paneClient.setVisible(false);
			paneProduct.setVisible(false);
			paneRevenue.setVisible(false);
			paneBill.setVisible(false);

			hiddenRecControl();
			recStaff.setVisible(true);
			btnStaff.setStyle("-fx-text-fill: orange; -fx-background-color: none");
			imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\team-management.png"));

		} else
			Notification.alert(AlertType.CONFIRMATION, "Đăng nhập tài khoản trưởng phòng để xem");

	}

	public void openProduct() {

		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(true);
		paneRevenue.setVisible(false);
		paneBill.setVisible(false);

		hiddenRecControl();
		recProduct.setVisible(true);
		btnProduct.setStyle("-fx-text-fill: orange; -fx-background-color: none");
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order1png.png"));

	}

	public void openBill() {

		paneBill.setVisible(true);
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(false);

		hiddenRecControl();
		recBill.setVisible(true);
		btnBill.setStyle("-fx-text-fill: orange; -fx-background-color: none");
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\mobile-receipt.png"));

	}

	public void openRevenue() {
		if (LoginController.roles == 1) {

			paneStaff.setVisible(false);
			paneHome.setVisible(false);
			paneClient.setVisible(false);
			paneProduct.setVisible(false);
			paneRevenue.setVisible(true);

			hiddenRecControl();
			recRevenue.setVisible(true);
			btnRevenue.setStyle("-fx-text-fill: orange; -fx-background-color: none");
			imgRevenue.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\data-analysis.png"));
		}

		else
			Notification.alert(AlertType.CONFIRMATION, "Đăng nhập tài khoản trưởng phòng để xem");
	}

	public void openClient() {

		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(true);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(false);

		hiddenRecControl();
		recClient.setVisible(true);
		btnClient.setStyle("-fx-text-fill: orange; -fx-background-color: none");
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer1.png"));

	}

	public void hiddenRecControl() {
		recHome.setVisible(false);
		recStaff.setVisible(false);
		recBill.setVisible(false);
		recClient.setVisible(false);
		recPaid.setVisible(false);
		recPaid.setVisible(false);
		recProduct.setVisible(false);

		btnHome.setTextFill(Color.WHITE);
		btnStaff.setTextFill(Color.WHITE);
		btnProduct.setTextFill(Color.WHITE);
		btnClient.setTextFill(Color.WHITE);
		btnRevenue.setTextFill(Color.WHITE);
		btnBill.setTextFill(Color.WHITE);
		btnPaid.setTextFill(Color.WHITE);

		imgHome.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\home.png"));
		imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\manager.png"));
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer.png"));
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill.png"));
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order.png"));
		imgRevenue.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\analytic.png"));
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\receipt.png"));
	}

	public void openPaid() {

		hiddenRecControl();
		recPaid.setVisible(true);
		btnPaid.setStyle("-fx-text-fill: orange; -fx-background-color: none");
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill1.png"));
	}

	// End Code Handle event from

	// Begin code show Stage

	// screen open about ('GIỚI THIỆU')
	public void openAbout() {
		try {
			Parent about = FXMLLoader.load(getClass().getResource("About.fxml"));
			stage = new Stage();
			Scene scene = new Scene(about);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("About Studio Breakfast Application");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// screen insert staff ('THÊM NHÂN VIÊN')
	public void openAddStaff() {
		try {
			Parent deltais = FXMLLoader.load(getClass().getResource("DeltaisStaff.fxml"));
			stage = new Stage();
			Scene scene = new Scene(deltais);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Nhân viên");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// screen insert staff ('THÊM Product')

	public void openAddProduct() {
		try {
			Parent deltais = FXMLLoader.load(getClass().getResource("DeltaisProduct.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(deltais);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Sản Phẩm");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// screen insert staff ('THÊM Client')

	public void openAddClient() {
		try {
			Parent deltais = FXMLLoader.load(getClass().getResource("DeltaisClient.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(deltais);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Khách Hàng");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// screen insert staff ('THÊM Bill')

	public void openAddBill() {
		try {
			Parent deltais = FXMLLoader.load(getClass().getResource("DeltaisBill.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(deltais);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Hóa Đơn");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// screen open Login ('ĐĂNG NHẬP')
	public void openLogin() {
		stage = (Stage) root.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			String css = this.getClass().getResource("styleLogin.css").toExternalForm();
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.setTitle("Đăng nhập Studio Breakfast");
			scene.getStylesheets().add(css);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// screen open Change password ('ĐỔI MẬT KHẨU')
	public void changepass() {

		stage = (Stage) root.getScene().getWindow();
		stage.close();

		try {
			Parent staff = FXMLLoader.load(getClass().getResource("ChangePass.fxml"));
			stage = new Stage();
			Scene scene = new Scene(staff);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Đổi Mật Khẩu");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/image/logo.png")));
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// open screen music

//	public void openMusic() {
//		
//		try {
//			Parent music = FXMLLoader.load(getClass().getResource("Music.fxml"));
//			stage = new Stage();
//			scene = new Scene(music);
//			stage.setScene(scene);
//			stage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//
//	}

	// End code show Stage

	// Begin handle code back end

	public void translateAnimation(double duration, Node node, double width) {

		TranslateTransition transition = new TranslateTransition(Duration.seconds(duration), node);
		transition.setByX(width);
		transition.play();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setPieChart();

		cbbBackground.getItems().setAll("Default", "White smoke");

		fillImage();

		setCellTable();
		loadDataStaff();

		setClientTable();
		loadDataClient();

		setProductTable();
		loadDataProduct();

		setBillTable();
		loadDataBill();

		loadLineChart();

		translateAnimation(0.5, pane2, 571);
		translateAnimation(0.5, pane3, 571);
		translateAnimation(0.5, pane4, 571);
		translateAnimation(0.5, pane5, 571);
	}

	// set column table Staff ('NHÂN VIÊN')
	public void setCellTable() {
		staff = FXCollections.observableArrayList();
		idCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("maNV"));
		nameCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("tenNV"));
		addressCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("diaChi"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("soDT"));
		wageCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, Float>("luong"));
	}

	// load data from database enter table in screen

	public void loadDataStaff() {

		if (tableStaff.getItems().size() >= 1) {
			tableStaff.getItems().clear();
		}

		ArrayList<NhanVienModel> list = INhanVien.getInstance().selectAll();
		for (NhanVienModel nhanVienModel : list) {
			staff.add(nhanVienModel);
		}
		tableStaff.setItems(staff);
	}

	// set column table Staff ('Khách Hàng')

	public void setClientTable() {
		client = FXCollections.observableArrayList();
		idClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("maKH"));
		nameClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("tenKH"));
		addressClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("diaChi"));
		phoneClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("SDT"));
		genderClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, Boolean>("gioiTinh"));
	}

	// load data from database enter table in screen

	public void loadDataClient() {
		if (tableClient.getItems().size() >= 1) {
			tableClient.getItems().clear();
		}

		ArrayList<KhachHangModel> list = IKhachHang.getInstance().selectAll();
		for (KhachHangModel khachHangModel : list) {
			client.add(khachHangModel);
		}
		tableClient.setItems(client);
	}

	// set column table Staff ('Sản Phẩm')

	public void setProductTable() {

		product = FXCollections.observableArrayList();
		idProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("maSP"));
		nameProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenSp"));
		wageProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("giaDichVu"));
		markProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("thuongHieu"));
		serviceProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("dichVu"));
	}

	// load data from database enter table in screen

	public void loadDataProduct() {
		if (tableProduct.getItems().size() >= 1) {
			tableProduct.getItems().clear();
		}

		ArrayList<SanPhamModel> list = ISanPham.getInstance().selectAll();
		for (SanPhamModel sanPhamModel : list) {
			product.add(sanPhamModel);
		}
		tableProduct.setItems(product);
	}

	// set column table Bill ('Hóa Đơn')

	public void setBillTable() {
		bill = FXCollections.observableArrayList();
		idBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, String>("mahd"));
		dayBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Date>("ngay"));
		paidBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Float>("thanhToan"));
		idClientCols.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Integer>("ID_KhachHang"));
		idStaffCols.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Integer>("ID_NV"));
	}

	// load data from database enter table in screen

	public void loadDataBill() {
		if (tableBill.getItems().size() >= 1) {
			tableBill.getItems().clear();
		}

		ArrayList<HoaDonModel> list = IHoaDon.getInstance().selectAll();
		for (HoaDonModel hoaDonModel : list) {
			bill.add(hoaDonModel);
		}

		tableBill.setItems(bill);
	}

	// set PieChart

	public void setPieChart() {

		int staff = INhanVien.getInstance().selectCount();
		int service = ISanPham.getInstance().selectCount();
		int client = IKhachHang.getInstance().selectCount();
		int bill = IHoaDon.getInstance().selectCount();

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Nhân Viên", staff), new PieChart.Data("Dịch Vụ", service),
				new PieChart.Data("Khách Hàng", client), new PieChart.Data("Hóa Đơn", bill));

		pieChartData.forEach(data -> data.nameProperty()
				.bind(Bindings.concat(data.getName(), " Số Lượng :: ", data.pieValueProperty())));

		pieChart.getData().addAll(pieChartData);

	}

	// DELETE Staff

	public void deleteStaff() {
		NhanVienModel col = tableStaff.getSelectionModel().getSelectedItem();

		NhanVienModel model = new NhanVienModel(col.getMaNV(), col.getTenNV(), col.getDiaChi(), col.getSoDT(),
				col.getLuong(), col.getNgayNhan(), col.getPass(), col.isRoles());
		INhanVien.getInstance().del(model);

		loadDataStaff();
	}

	// Delete Product

	public void deleteProduct() {
		SanPhamModel col = tableProduct.getSelectionModel().getSelectedItem();

		SanPhamModel model = new SanPhamModel(col.getMaSP(), col.getTenSp(), col.getThuongHieu(), col.getGiaDichVu(),
				col.getMoTa(), col.getDichVu(), col.getMaNV());
		ISanPham.getInstance().del(model);

		loadDataProduct();
	}

	// Delete client

	public void delClient() {
		KhachHangModel col = tableClient.getSelectionModel().getSelectedItem();

		KhachHangModel model = new KhachHangModel(col.getMaKH(), col.getTenKH(), col.getDiaChi(), col.getSDT(),
				col.isGioiTinh(), col.getIdSP());
		IKhachHang.getInstance().del(model);

		loadDataClient();
	}

	// Delete Bill

	public void delBill() {
		HoaDonModel col = tableBill.getSelectionModel().getSelectedItem();

		HoaDonModel model = new HoaDonModel(col.getMahd(), col.getNgay(), col.getThanhToan(), col.getID_KhachHang(),
				col.getID_NV());
		IHoaDon.getInstance().del(model);

		loadDataBill();
	}

	// fill tabpane bill

	public void fillTabPane() {
		HoaDonModel col = tableBill.getSelectionModel().getSelectedItem();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select MaHD, Ngay, TenKH, DichVu, GiaDichVu, thanhtoan from HOADON\r\n"
				+ "inner join KHACHHANG on HOADON.ID_KhachHang = KHACHHANG.ID_KhachHang\r\n"
				+ "inner join SANPHAM on KHACHHANG.id_sanpham = SANPHAM.ID_SANPHAM\r\n" + "where MaHD = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, col.getMahd());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String MaHD = rs.getString(1);
				String Ngay = rs.getString(2);
				String TenKH = rs.getString(3);
				String DichVu = rs.getString(4);
				Float GiaDichVu = rs.getFloat(5);
				Float ThanhToan = rs.getFloat(6);

				lblBill.setText(MaHD);
				lblDate.setText(Ngay);
				lblName.setText(TenKH);
				lblService.setText(DichVu);
				lblPrice.setText(Float.valueOf(GiaDichVu).toString());
				lblMoney.setText(Float.valueOf(GiaDichVu).toString());
				lblPaid.setText(Float.valueOf(GiaDichVu).toString());
			}

			SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
			selectionModel.select(tabBill);

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// load line chart

	public void loadLineChart() {
		XYChart.Series series = new XYChart.Series();
		series.setName("Hoa Don");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Thu Nhap");

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"select Ngay, SUM(thanhtoan) as thanhtoan, count(mahd) as soluong from HOADON group by Ngay");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt("soluong")));
				series1.getData().add(new XYChart.Data(rs.getString(1), rs.getFloat("thanhtoan")));
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		myLineChart.getData().add(series1);
		myLineChart.getData().add(series);
	}

	public void findStaff() {

		String manv = txtFindStaff.getText();
		NhanVienModel model = new NhanVienModel(manv, null, null, null, 0, null, null, false);

		if (tableStaff.getItems().size() >= 1) {
			tableStaff.getItems().clear();
		}

		NhanVienModel model1 = INhanVien.getInstance().selectByID(model);
		staff.add(model1);
		tableStaff.setItems(staff);

	}

	public void findClient() {
		String makh = txtFindClient.getText();

		KhachHangModel model = new KhachHangModel(makh, null, null, null, false, 0);

		if (tableClient.getItems().size() >= 1) {
			tableClient.getItems().clear();
		}

		KhachHangModel kh = IKhachHang.getInstance().selectByID(model);
		client.add(kh);
		tableClient.setItems(client);
	}

	public void findProduct() {

		String masp = txtFindProduct.getText();

		SanPhamModel model = new SanPhamModel(masp, null, null, 0, null, null, 0);
		SanPhamModel sp = ISanPham.getInstance().selectByID(model);

		if (tableProduct.getItems().size() >= 1) {
			tableProduct.getItems().clear();
		}
		product.add(sp);

		tableProduct.setItems(product);
	}

	public void findBill() {
		String mahd = txtFindBill.getText();

		HoaDonModel model = new HoaDonModel(mahd, null, 0, 0, 0);
		HoaDonModel hoadon = IHoaDon.getInstance().selectByID(model);

		if (tableBill.getItems().size() >= 1) {
			tableBill.getItems().clear();
		}

		bill.add(hoadon);
		tableBill.setItems(bill);
	}

	public void fillImage() {
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\logo.png");
		circleLogo.setFill(new ImagePattern(img));

		Image poster = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster3.jpg");
		recPoster.setFill(new ImagePattern(poster));

		Image poster6 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster6.jpg");
		recPoster1.setFill(new ImagePattern(poster6));

		Image poster7 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster7.jpg");
		recPoster2.setFill(new ImagePattern(poster7));

		Image poster8 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster8.jpg");
		recPoster3.setFill(new ImagePattern(poster8));

		Image poster9 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster9.jpg");
		recPoster4.setFill(new ImagePattern(poster9));

		Image music = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\musicl.jpg");
		recMusic.setFill(new ImagePattern(music));

		Image poster1 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster1.jpg");
		rec1.setFill(new ImagePattern(poster1));

		Image poster2 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster2.jpg");
		rec2.setFill(new ImagePattern(poster2));

		Image poster3 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster4.jpg");
		rec3.setFill(new ImagePattern(poster3));

		Image poster4 = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\poster5.jpg");
		rec4.setFill(new ImagePattern(poster4));

	}

	int show = 0;

	public void back() {
		if (show == 0) {
			translateAnimation(0.5, pane2, -571);
			show++;
		} else if (show == 1) {
			translateAnimation(0.5, pane3, -571);
			show++;
		} else if (show == 2) {
			translateAnimation(0.5, pane4, -571);
			show++;
		} else if (show == 3) {
			translateAnimation(0.5, pane5, -571);
			show++;
		}

	}

	public void next() {
		if (show == 1) {
			translateAnimation(0.5, pane2, 571);
			show--;

		} else if (show == 2) {
			translateAnimation(0.5, pane3, 571);
			show--;
		} else if (show == 3) {
			translateAnimation(0.5, pane4, 571);
			show--;
		} else if (show == 4) {
			translateAnimation(0.5, pane5, 571);
			show--;
		}
	}

}
