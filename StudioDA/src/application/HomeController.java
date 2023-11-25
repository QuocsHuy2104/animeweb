package application;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import IDAO.IDichVu;
import IDAO.IHDCT;
import IDAO.IHoaDon;
import IDAO.IKhachHang;
import IDAO.INhanVien;
import IDAO.ISanPham;
import connectJDBC.JDBCUtil;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import utilities.AutoString;
import utilities.MessageDigest;
import utilities.Notification;
import utilities.PasswordRegex;
import utilities.PhoneRegex;
import utilities.ReadExcel;

public class HomeController implements Initializable {

	@FXML
	private ImageView imageHeart, imgHome, imgStaff, imgClient, imgProduct, imgService, imgBill, imgPaid, setting;

	@FXML
	private Pane MenuPane, ContentPane, paneSetting, paneStaff, paneHome, paneClient, paneProduct, paneService,
			paneBill;

	@FXML
	private Rectangle recHome, recStaff, recPaid, recProduct, recBill, recClient, recService;

	@FXML
	private Button btnHome, btnStaff, btnClient, btnProduct, btnService, btnBill, btnBillDeltais;

	@FXML
	private Line lineMenu;

	// Home

	@FXML
	private PieChart pieChart;

	@FXML
	private LineChart myLineChart;

	@FXML
	private AreaChart myAreaChart;

	@FXML
	private TableView tblRevenue;

	@FXML
	private TableColumn<SanPhamModel, String> productCol;

	@FXML
	private TableColumn<SanPhamModel, Float> priceCol;

	@FXML
	private TableColumn discountCol;

	@FXML
	private TableColumn salesCol;

	@FXML
	private AnchorPane root;

	@FXML
	private Circle circleLogo;

	// Setting

	@FXML
	private Rectangle recDatabase, recLogo;

	@FXML
	private ImageView imgDownArrow, imgDownArrow1, imgDownArrow2, imgDownArrow3;

	@FXML
	private Label lblMail, lblNameStaff;

	@FXML
	private Pane paneAccount, paneSystem;

	// Table Staff

	@FXML
	private TextField IDStaff, nameStaff, phoneStaff, emailStaff;

	@FXML
	private TextArea addressStaff;

	@FXML
	private PasswordField password;

	@FXML
	private RadioButton rbtnStaff, rbtnManage;

	@FXML
	private Button addStaffButton, delStaffButton;

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
	private TableColumn<NhanVienModel, String> emailStaffCol;

	@FXML
	private RadioButton rbtnMan, rbtnWoman;

	// Table Client

	@FXML
	private TextField IDClient, nameClient, phoneClient, emailClient;

	@FXML
	private TextArea addressClient;

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
	private TableColumn<KhachHangModel, String> genderClientCol;

	// Table Product

	@FXML
	private TextField IDProduct, nameProduct, priceProduct;

	@FXML
	private ComboBox<String> trademark;

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

	// Table Service

	@FXML
	private TextField IDSer, nameSer, priceSer, txtFindSer;

	@FXML
	private ComboBox<String> nameProOfSer;

	@FXML
	private TextArea descriptSer;

	@FXML
	private TableView<DichVuModel> tableSer;

	@FXML
	private TableColumn<DichVuModel, String> idSerCol;

	@FXML
	private TableColumn<DichVuModel, String> nameSerCol;

	@FXML
	private TableColumn<DichVuModel, Float> priceSerCol;

	@FXML
	private TableColumn<DichVuModel, String> descriptSerCol;

	@FXML
	private TableColumn<DichVuModel, String> nameOfProCol;

	@FXML
	private Button saveSer, delSer;

	// Table Bill

	@FXML
	private TextField nameStaffOfBill, nameClientOfBill, phoneClientOfBill, quantity, mydate, sumMoney, clientMoney,
			changeMoney;

	@FXML
	private DatePicker dateBill;

	@FXML
	private TableView<SanPhamModel> tableProBill;

	@FXML
	private TableView tableBill;

	@FXML
	private TableColumn<SanPhamModel, String> nameProOfBill;

	@FXML
	private TableColumn<SanPhamModel, Float> priceProOfBill;

	@FXML
	private TableColumn<SanPhamModel, String> trademarkOfBill;

	// =================================================

	@FXML
	private TableColumn<HDCTModel, String> idProOfBill;

	@FXML
	private TableColumn<HDCTModel, String> nameBillCol;

	@FXML
	private TableColumn<HDCTModel, Integer> qiantityBillCol;

	@FXML
	private TableColumn<SanPhamModel, Float> priceBillCol;

	@FXML
	private TableColumn<HoaDonModel, Float> paidBillCol;

	@FXML
	private TableColumn<HoaDonModel, String> storyBillCol;

	@FXML
	private TextField txtFindStaff, txtFindClient, txtFindProduct, txtFindBill;

	private ObservableList<NhanVienModel> staff;

	private ObservableList<KhachHangModel> client;

	private ObservableList<SanPhamModel> product;

	private ObservableList<DichVuModel> service;

	private ObservableList bill;

	private ObservableList<SanPhamModel> proBill;

	private ObservableList revenue;

	Stage stage;
	Scene scene;
	Parent root1;

	// Code Handle event from

	public void convertHeart() {
		Image img = new Image("C:/Users/HP/workspage-udpm/StudioDA/src/image/love-you.png");
		imageHeart.setImage(img);
	}

	public void setting() {
		paneSetting.setVisible(true);
	}

	public void closeSetting() {
		paneSetting.setVisible(false);
		Image img = new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\metal-gear.png");
		setting.setImage(img);
	}

	public void openHome() {

		paneStaff.setVisible(false);
		paneHome.setVisible(true);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);

		hiddenRecControl();
		recHome.setVisible(true);
		btnHome.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgHome.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\home-page.png"));

	}

	public void openStaff() {
		if (LoginController.roles == 1) {

			paneStaff.setVisible(true);
			paneHome.setVisible(false);
			paneClient.setVisible(false);
			paneProduct.setVisible(false);
			paneService.setVisible(false);
			paneBill.setVisible(false);

			hiddenRecControl();
			recStaff.setVisible(true);
			btnStaff.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
			imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\team-management.png"));

		} else {
			ErrorForm errorForm = new ErrorForm();
			errorForm.start(stage);
		}

	}

	public void openProduct() {

		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(true);
		paneService.setVisible(false);
		paneBill.setVisible(false);

		hiddenRecControl();
		recProduct.setVisible(true);
		btnProduct.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order1.png"));

	}

	public void openService() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(true);
		paneBill.setVisible(false);

		hiddenRecControl();
		recService.setVisible(true);
		btnService.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgService.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\service.png"));
	}

	public void openBill() {

		paneBill.setVisible(true);
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);

		hiddenRecControl();
		recBill.setVisible(true);
		btnBill.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill1.png"));

	}

	public void openClient() {

		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(true);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);

		hiddenRecControl();
		recClient.setVisible(true);
		btnClient.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer1.png"));

	}

	public void hiddenRecControl() {
		recHome.setVisible(false);
		recStaff.setVisible(false);
		recBill.setVisible(false);
		recClient.setVisible(false);
		recPaid.setVisible(false);
		recService.setVisible(false);
		recProduct.setVisible(false);

		btnHome.setTextFill(Color.BLACK);
		btnStaff.setTextFill(Color.BLACK);
		btnProduct.setTextFill(Color.BLACK);
		btnClient.setTextFill(Color.BLACK);
		btnService.setTextFill(Color.BLACK);
		btnBill.setTextFill(Color.BLACK);
		btnBillDeltais.setTextFill(Color.BLACK);

		imgHome.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\home.png"));
		imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\manager.png"));
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer.png"));
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill-service.png"));
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order.png"));
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\receipt.png"));
		imgService.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\customer-service.png"));
	}

	public void openPaid() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);

		hiddenRecControl();

		recPaid.setVisible(true);
		btnBillDeltais.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill-service1.png"));
	}

	// Controll Setting

	public void showDatabase() {
		// 190
		// 64

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 64; i <= 190; i++) {
					recDatabase.setHeight(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				imgDownArrow.setVisible(false);
				imgDownArrow1.setVisible(true);
			}
		}).start();

	}

	public void hiddenDatabase() {
		// 190
		// 64

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 190; i >= 64; i--) {
					recDatabase.setHeight(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				imgDownArrow.setVisible(true);
				imgDownArrow1.setVisible(false);
			}
		}).start();

	}

	public void showPaneAccount() {
		paneSystem.setVisible(false);
		paneAccount.setVisible(true);
	}

	public void showPaneSystem() {
		paneSystem.setVisible(true);
		paneAccount.setVisible(false);

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

	// screen open Login ('ĐĂNG NHẬP')
	public void openLogin() {
		stage = (Stage) root.getScene().getWindow();
		stage.close();

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			Parent root = loader.load();
			stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);

			LoginController controller = loader.getController();

			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent arg0) {
					if (arg0.getCode() == KeyCode.ENTER) {
						try {
							controller.LoginEvent();
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
					} else if (arg0.getCode() == KeyCode.ESCAPE)
						controller.exitForm();
				}

			});
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

	public void openMusic() {

		try {
			Parent music = FXMLLoader.load(getClass().getResource("Music.fxml"));
			stage = new Stage();
			scene = new Scene(music);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// End code show Stage

	// Begin handle code back end

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setPieChart();

		setCellTable();
		loadDataStaff();

		setClientTable();
		loadDataClient();

		setProductTable();
		loadDataProduct();

		setBillTable();
		loadDataBill();

		setCellRevenue();
		loadDataRevenue();

		setServiceTable();
		loadService();

		setProBill();
		loadDataProBill();

		getCurrentDate();

		circleLogo
				.setFill(new ImagePattern(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\logo.png")));

		recLogo.setFill(new ImagePattern(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\logo.png")));
		lblMail.setText(LoginController.email);
		lblNameStaff.setText(LoginController.nameStaff);

//		try {
//			ReadExcel.downDB();
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}

		loadCbbTrademark();

		loadCbbSer();

		// loadLineChart();

		ToggleGroup group = new ToggleGroup();
		rbtnManage.setToggleGroup(group);
		rbtnStaff.setToggleGroup(group);

		ToggleGroup group1 = new ToggleGroup();
		rbtnMan.setToggleGroup(group1);
		rbtnWoman.setToggleGroup(group1);

	}

	// Home Controll

	public void setCellRevenue() {
		revenue = FXCollections.observableArrayList();
		productCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenSp"));
		priceCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("donGia"));
		discountCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("maNV"));

	}

	public void loadDataRevenue() {
		if (tblRevenue.getItems().size() >= 1) {
			tblRevenue.getItems().clear();
		}

		ArrayList<SanPhamModel> list = ISanPham.getInstance().selectAll();
		ArrayList<NhanVienModel> list1 = INhanVien.getInstance().selectAll();
		for (SanPhamModel sanPhamModel : list) {
			for (NhanVienModel nhanVienModel : list1) {
				revenue.add(sanPhamModel);
				revenue.add(nhanVienModel);
			}
		}

		tblRevenue.setItems(revenue);
	}

	// Staff Controll

	// set column table Staff ('NHÂN VIÊN')
	public void setCellTable() {
		staff = FXCollections.observableArrayList();
		idCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("maNV"));
		nameCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("tenNV"));
		addressCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("diaChi"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("sdt"));
		emailStaffCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("email"));
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

	public void insertStaff() {
		String id = IDStaff.getText().trim();
		String ten = nameStaff.getText().trim();
		String diachi = addressStaff.getText();
		String sdt = phoneStaff.getText().trim();
		String email = emailStaff.getText();
		try {
			PhoneRegex.checkPhone(sdt);
		} catch (PhoneRegex e) {
			e.printStackTrace();
		}
		String pass = password.getText();
		if (id.equals("") || ten.equals("") || diachi.equals("") || sdt.equals("") || pass.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}
		PasswordRegex passwordRegex = new PasswordRegex();

		if (passwordRegex.validate(pass) == false) {
			Notification.alert(AlertType.ERROR, "Tối thiểu 8 ký tự, ít nhất 1 chữ cái, 1 số và 1 ký tự đặc biệt:");
		} else {
			try {
				String passMD = MessageDigest.getMD5(pass);
				boolean roles = rbtnManage.isSelected() ? true : false;

				NhanVienModel model = new NhanVienModel(id, ten, diachi, sdt, email, passMD, roles, true);
				INhanVien.getInstance().insert(model);
				loadDataStaff();
				Message msg = new Message();
				msg.start(stage);
				cleanStaff();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

	}

	public void saveStaff() {
		int select = tableStaff.getSelectionModel().getSelectedIndex();
		if (select < 0) {
			insertStaff();
		} else if (select > 0) {
			updateStaff();
		}
	}

	public void updateStaff() {
		String id = IDStaff.getText().trim();
		String ten = nameStaff.getText().trim();
		String diachi = addressStaff.getText();
		String sdt = phoneStaff.getText().trim();
		String email = emailStaff.getText();
		try {
			PhoneRegex.checkPhone(sdt);
		} catch (PhoneRegex e) {
			e.printStackTrace();
		}
		String pass = password.getText();
		boolean roles = rbtnManage.isSelected() ? true : false;

		if (id.equals("") || ten.equals("") || diachi.equals("") || sdt.equals("") || pass.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}

		NhanVienModel model = new NhanVienModel(id, ten, diachi, sdt, email, pass, roles, true);
		INhanVien.getInstance().update(model);
		loadDataStaff();
		Message msg = new Message();
		msg.start(stage);
		cleanStaff();
	}

	public void cleanStaff() {
		IDStaff.setText("");
		nameStaff.setText("");
		addressStaff.setText("");
		phoneStaff.setText("");
		emailStaff.setText("");
		password.setText("");
		rbtnManage.setSelected(false);
		rbtnStaff.setSelected(false);
	}

	public void gmailStaff() {
		emailStaff.setText(AutoString.convertString(AutoString.autoEmail(nameStaff.getText())));
	}

	// DELETE Staff

	public void deleteStaff() {
		NhanVienModel col = tableStaff.getSelectionModel().getSelectedItem();
		NhanVienModel model = new NhanVienModel(col.getMaNV(), col.getTenNV(), col.getDiaChi(), col.getSdt(),
				col.getEmail(), col.getMatKhau(), col.isVaiTro(), col.isTrangThai());

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText("Bạn có chắc chắc muốn xóa hông");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {
			INhanVien.getInstance().del(model);

			loadDataStaff();
			cleanStaff();
		}

	}

	public void clickItem(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			NhanVienModel col = tableStaff.getSelectionModel().getSelectedItem();
			IDStaff.setText(col.getMaNV());
			nameStaff.setText(col.getTenNV());
			phoneStaff.setText(col.getSdt());
			emailStaff.setText(col.getEmail());
			addressStaff.setText(col.getDiaChi());
			rbtnStaff.setSelected(col.isVaiTro());
			rbtnManage.setSelected(col.isVaiTro());
		}
	}

	// Client Controll

	// set column table Client ('Khách Hàng')

	public void setClientTable() {
		client = FXCollections.observableArrayList();
		idClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("maKH"));
		nameClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("tenKH"));
		addressClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("diachi"));
		phoneClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("sdt"));
		genderClientCol.setCellValueFactory(new PropertyValueFactory<KhachHangModel, String>("gioiTinh"));
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

	public void saveClient() {
		int select = tableClient.getSelectionModel().getSelectedIndex();

		if (select < 0) {
			insertClient();
			cleanClient();
		} else if (select > 0) {
			updateClient();
			cleanClient();
		}
	}

	public void insertClient() {
		String id = IDClient.getText();
		String name = nameClient.getText();
		String address = addressClient.getText();
		String phone = phoneClient.getText();
		String gender = rbtnMan.isSelected() ? "Nam" : "Nữ";
		String email = emailClient.getText();

		if (id.equals("") || name.equals("") || address.equals("") || phone.equals("")) {
			Notification.alert(AlertType.WARNING, "Nhập đầy đủ thông tin");
			return;
		}
		try {
			PhoneRegex.checkPhone(phone);
		} catch (PhoneRegex e) {
			e.printStackTrace();
		}

		KhachHangModel kh = new KhachHangModel(id, name, address, phone, gender, email, true);
		IKhachHang.getInstance().insert(kh);

		loadDataClient();
		Message msg = new Message();
		msg.start(stage);
	}

	// Delete client

	public void delClient() {
		KhachHangModel col = tableClient.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText("Bạn có chắc chắc muốn xóa hông");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {

			KhachHangModel model = new KhachHangModel(col.getMaKH(), col.getTenKH(), col.getDiachi(), col.getSdt(),
					col.getGioiTinh(), col.getEmail(), col.isTrangThai());
			IKhachHang.getInstance().del(model);

			loadDataClient();
			Message msg = new Message();
			msg.start(stage);
			cleanClient();
		}

	}

	public void updateClient() {
		String id = IDClient.getText();
		String name = nameClient.getText();
		String address = addressClient.getText();
		String phone = phoneClient.getText();
		String gender = rbtnMan.isSelected() ? "Nam" : "Nữ";
		String email = emailClient.getText();

		if (id.equals("") || name.equals("") || address.equals("") || phone.equals("")) {
			Notification.alert(AlertType.WARNING, "Nhập đầy đủ thông tin");
			return;
		}
		try {
			PhoneRegex.checkPhone(phone);
		} catch (PhoneRegex e) {
			e.printStackTrace();
		}

		KhachHangModel kh = new KhachHangModel(id, name, address, phone, gender, email, true);
		IKhachHang.getInstance().update(kh);

		loadDataClient();
		Message msg = new Message();
		msg.start(stage);
	}

	public void cleanClient() {
		IDClient.setText("");
		nameClient.setText("");
		addressClient.setText("");
		phoneClient.setText("");
		emailClient.setText("");
	}

	public void clickTableClient(MouseEvent e) {
		if (e.getClickCount() == 2) {
			KhachHangModel col = tableClient.getSelectionModel().getSelectedItem();
			IDClient.setText(col.getMaKH());
			nameClient.setText(col.getTenKH());
			addressClient.setText(col.getDiachi());
			emailClient.setText(col.getEmail());
			phoneClient.setText(col.getSdt());
		}
	}

	public void autoMailClient() {
		emailClient.setText(AutoString.convertString(AutoString.autoEmail(nameClient.getText())));
		IDClient.setText(AutoString.autoID("KH", "makh", "KhachHang"));
	}

	// Product Controll

	public void insertPro() {
		String id = IDProduct.getText();
		String name = nameProduct.getText();
		Float price = Float.parseFloat(priceProduct.getText());
		String tenth = trademark.getValue();

		if (id.equals("") || name.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}

		if (price < 0) {
			priceProduct.setText("0");
			return;
		}

		SanPhamModel model = new SanPhamModel(id, name, price, tenth);
		ISanPham.getInstance().insert(model);

	}

	public void updatePro() {
		String id = IDProduct.getText();
		String name = nameProduct.getText();
		Float price = Float.parseFloat(priceProduct.getText());
		String th = trademark.getValue();

		if (id.equals("") || name.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}

		SanPhamModel model = new SanPhamModel(id, name, price, th);
		ISanPham.getInstance().update(model);

	}

	public void savePro() {
		int select = tableProduct.getSelectionModel().getSelectedIndex();

		if (select < 0) {
			insertPro();

			Message msg = new Message();
			msg.start(stage);

			loadDataProduct();

			cleanPro();
		} else if (select > 0) {
			updatePro();

			Message msg = new Message();
			msg.start(stage);

			loadDataProduct();

			cleanPro();
		}
	}

	public void cleanPro() {
		nameProduct.setText("");
		IDProduct.setText("");
		priceProduct.setText("");
	}

	// Delete Product

	public void deleteProduct() {
		SanPhamModel col = tableProduct.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText("Bạn có chắc chắc muốn xóa hông");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {

			SanPhamModel model = new SanPhamModel(col.getMaSP(), col.getTenSp(), col.getDonGia(), col.getTenTH());
			ISanPham.getInstance().del(model);

			loadDataProduct();
			Message msg = new Message();
			msg.start(stage);

		}

	}

	public void clickTableProduct(MouseEvent e) {
		if (e.getClickCount() == 2) {
			SanPhamModel col = tableProduct.getSelectionModel().getSelectedItem();
			IDProduct.setText(col.getMaSP());
			nameProduct.setText(col.getTenSp());
			priceProduct.setText(String.valueOf(col.getDonGia()));
			trademark.setValue(col.getTenTH());
		}

	}

	// set column table Product ('Sản Phẩm')

	public void setProductTable() {
		product = FXCollections.observableArrayList();
		idProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("maSP"));
		nameProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenSp"));
		wageProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("donGia"));
		markProCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenTH"));
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

	// Service Controll

	public void setServiceTable() {
		service = FXCollections.observableArrayList();
		idSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("maDV"));
		nameSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("tenDV"));
		priceSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, Float>("giaDV"));
		nameOfProCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("tenSp"));
		descriptSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("moTa"));
	}

	public void loadService() {
		if (tableSer.getItems().size() >= 1) {
			tableSer.getItems().clear();
		}

		ArrayList<DichVuModel> list = IDichVu.getInstance().selectAll();
		for (DichVuModel dichVuModel : list) {
			service.add(dichVuModel);
		}

		tableSer.setItems(service);
	}

	public void loadCbbSer() {
		ArrayList<String> list = IDichVu.getInstance().selectNamePro();
		for (String string : list) {
			nameProOfSer.getItems().add(string);
		}
	}

	public void autoIDSer() {
		IDSer.setText(AutoString.autoID("DV", "madv", "dichvu"));
	}

	public void insertSer() {

		String id = IDSer.getText();
		String nameService = nameSer.getText();
		Float priceService = Float.parseFloat(priceSer.getText());
		String descript = descriptSer.getText();
		String nameProd = nameProOfSer.getValue();

		DichVuModel model = new DichVuModel(id, nameService, priceService, descript, nameProd);
		IDichVu.getInstance().insert(model);

	}

	public void delSer() {

		DichVuModel col = tableSer.getSelectionModel().getSelectedItem();
		DichVuModel model = new DichVuModel(col.getMaDV(), null, null, null, null);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText("Bạn có chắc chắc muốn xóa hông");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {

			IDichVu.getInstance().del(model);

			loadService();

			Message msg = new Message();
			msg.start(stage);

		}

	}

	public void saveSer() {

		int select = tableSer.getSelectionModel().getSelectedIndex();

		if (select < 0) {
			insertSer();

			Message msg = new Message();
			msg.start(stage);

			loadService();

			clearSer();
		} else if (select > 0) {
			updateSer();

			Message msg = new Message();
			msg.start(stage);

			loadService();

			clearSer();
		}
	}

	public void updateSer() {

		String id = IDSer.getText();
		String nameService = nameSer.getText();
		Float priceService = Float.parseFloat(priceSer.getText());
		String descript = descriptSer.getText();
		String nameProd = nameProOfSer.getValue();

		DichVuModel model = new DichVuModel(id, nameService, priceService, descript, nameProd);
		IDichVu.getInstance().insert(model);

	}

	public void clearSer() {
		IDSer.setText("");
		nameSer.setText("");
		priceSer.setText("");
		descriptSer.setText("");
	}

	public void clickItemSer(MouseEvent event) {
		if (event.getClickCount() == 2) {
			DichVuModel col = tableSer.getSelectionModel().getSelectedItem();
			IDSer.setText(col.getMaDV());
			nameSer.setText(col.getTenDV());
			priceSer.setText(String.valueOf(col.getGiaDV()));
			descriptSer.setText(col.getMoTa());
		}

	}

	// Bill Controll

	// set column table Bill ('Hóa Đơn')

	public void setBillTable() {
		bill = FXCollections.observableArrayList();
		// idProOfBill.setCellValueFactory(new PropertyValueFactory<HDCTModel,
		// String>(""));
		// nameBillCol.setCellValueFactory(new PropertyValueFactory<HDCTModel,
		// String>("tenSp"));
		qiantityBillCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Integer>("soLuong"));
		priceBillCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("donGia"));
		paidBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Float>("thanhToan"));
		storyBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, String>("trangThai"));
	}

	// load data from database enter table in screen

	public void loadDataBill() {
		if (tableBill.getItems().size() >= 1) {
			tableBill.getItems().clear();
		}

		ArrayList<HDCTModel> list = IHDCT.getInstance().selectAll();
		for (HDCTModel hdctModel : list) {
			bill.add(hdctModel);
		}
		tableBill.setItems(bill);
	}

	public void setProBill() {
		proBill = FXCollections.observableArrayList();
		nameProOfBill.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenSp"));
		priceProOfBill.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("donGia"));
		trademarkOfBill.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenTH"));
	}

	public void loadDataProBill() {
		if (tableProBill.getItems().size() >= 1) {
			tableProBill.getItems().clear();
		}

		ArrayList<SanPhamModel> list = ISanPham.getInstance().selectAll();
		for (SanPhamModel sanPhamModel : list) {
			proBill.add(sanPhamModel);
		}
		tableProBill.setItems(proBill);
	}

	public void getClient() {
		nameClientOfBill.setText(IKhachHang.getInstance().selectByPhone(phoneClientOfBill.getText()));
	}

	public void getCurrentDate() {
		Date date = new Date();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

		dateBill.setValue(ld);

		nameStaffOfBill.setText(LoginController.nameStaff);
	}

	float money = 0;
	String tensp = "";

	public void clickItemBill(MouseEvent event) {
		try {
			if (event.getClickCount() == 2) {
				SanPhamModel col = tableProBill.getSelectionModel().getSelectedItem();
				tensp = col.getTenSp();
				money = col.getDonGia();
				String sl = quantity.getText();
				int quantity = Integer.valueOf(sl);
				sumMoney.setText(money * quantity + "");
			}
		} catch (Exception e) {
			Notification.alert(AlertType.ERROR, "Vui lòng nhập số lượng");
		}
	}

	public void comfy() {
		try {

			float tongTien = Float.valueOf(sumMoney.getText());
			float tienKhach = Float.valueOf(clientMoney.getText());

			changeMoney.setText(tienKhach - tongTien + " ");
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
	}

	public void insertBill() {
		String mahd = AutoString.autoID("HD", "mahd", "HoaDon");
		LocalDate localDate = dateBill.getValue();

		java.sql.Date myDate = java.sql.Date.valueOf(localDate);

		float thanhtoan = Float.valueOf(sumMoney.getText());
		String nameKH = nameClientOfBill.getText();
		String nameStaff = nameStaffOfBill.getText();

		int sl = Integer.valueOf(quantity.getText());

		HoaDonModel model = new HoaDonModel(mahd, myDate, thanhtoan, nameKH, nameStaff, "Chưa Thanh Toán");
		IHoaDon.getInstance().insert(model);

		String mahdct = AutoString.autoID("HDCT", "MaHDCT", "HDCT");

		HDCTModel hdct = new HDCTModel(mahdct, money, sl, mahd, tensp);
		IHDCT.getInstance().insert(hdct);

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
				.bind(Bindings.concat(data.getName(), " Số Lượng --> ", data.pieValueProperty())));

		pieChart.getData().addAll(pieChartData);

	}

	// Delete Bill

	public void delBill() {
		HoaDonModel col = (HoaDonModel) tableBill.getSelectionModel().getSelectedItem();

		HoaDonModel model = new HoaDonModel(col.getMahd(), col.getNgay(), col.getThanhToan(), col.getTenKH(),
				col.getTenNV(), col.getTrangThai());
		IHoaDon.getInstance().del(model);

		loadDataBill();
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
		NhanVienModel model = new NhanVienModel(manv, null, null, null, null, null, false, false);

		if (tableStaff.getItems().size() >= 1) {
			tableStaff.getItems().clear();
		}

		NhanVienModel model1 = INhanVien.getInstance().selectByID(model);
		staff.add(model1);
		tableStaff.setItems(staff);

	}

	public void findClient() {
		String makh = txtFindClient.getText();

		KhachHangModel model = new KhachHangModel(makh, null, null, null, null, null, false);

		if (tableClient.getItems().size() >= 1) {
			tableClient.getItems().clear();
		}

		KhachHangModel kh = IKhachHang.getInstance().selectByID(model);
		client.add(kh);
		tableClient.setItems(client);
	}

	public void findProduct() {

		String masp = txtFindProduct.getText();

		SanPhamModel model = new SanPhamModel(masp, null, 0, null);
		SanPhamModel sp = ISanPham.getInstance().selectByID(model);

		if (tableProduct.getItems().size() >= 1) {
			tableProduct.getItems().clear();
		}
		product.add(sp);

		tableProduct.setItems(product);
	}

	public void findService() {
		String masp = txtFindSer.getText();

		DichVuModel model = new DichVuModel(masp, null, null, null, null);
		DichVuModel sp = IDichVu.getInstance().selectByID(model);

		if (tableSer.getItems().size() >= 1) {
			tableSer.getItems().clear();
		}

		service.add(sp);
		tableSer.setItems(service);
	}

	public void findBill() {
		String mahd = txtFindBill.getText();

		HoaDonModel model = new HoaDonModel(mahd, null, 0, null, null, null);
		HoaDonModel hoadon = IHoaDon.getInstance().selectByID(model);

		if (tableBill.getItems().size() >= 1) {
			tableBill.getItems().clear();
		}

		bill.add(hoadon);
		tableBill.setItems(bill);
	}

	public void loadCbbTrademark() {
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select tenth from thuonghieu");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tenth = rs.getString("tenth");
				this.trademark.getItems().add(tenth);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void autoIDPRo() {
		IDProduct.setText(AutoString.autoID("SP", "masp", "SanPham"));
	}

}
