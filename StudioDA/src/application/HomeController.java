package application;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.aspose.pdf.Document;
import com.aspose.pdf.FontRepository;
import com.aspose.pdf.HtmlFragment;
import com.aspose.pdf.Page;
import com.aspose.pdf.Position;
import com.aspose.pdf.TextBuilder;
import com.aspose.pdf.TextFragment;

import IDAO.IChiTietDV;
import IDAO.IDichVu;
import IDAO.IHDCT;
import IDAO.IHoaDon;
import IDAO.IHoaDonDV;
import IDAO.IKhachHang;
import IDAO.INhanVien;
import IDAO.ISanPham;
import IDAO.IThongKe;
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
import javafx.scene.control.cell.TextFieldTableCell;
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
	private ImageView imageHeart, imgHome, imgStaff, imgClient, imgProduct, imgService, imgBill, imgPaid, setting,
			imgRev;

	@FXML
	private Pane MenuPane, ContentPane, paneSetting, paneStaff, paneHome, paneClient, paneProduct, paneService,
			paneBill, paneRevenue, paneHDDV;

	@FXML
	private Rectangle recHome, recStaff, recPaid, recProduct, recBill, recClient, recService;

	@FXML
	private Button btnHome, btnStaff, btnClient, btnProduct, btnService, btnBill, btnBillDeltais, btnRev;

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
	private RadioButton rbtnStaff, rbtnManage, rbtnFrom, rbtnMonth, rbtnYear;

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
			changeMoney, IDBill, txtFindStaffOfBill;

	@FXML
	private Button btnRemove;

	@FXML
	private DatePicker dateBill, dateFrom, dateTo;

	@FXML
	private TableView<SanPhamModel> tableProBill;

	@FXML
	private TableView<HDCTModel> tableBillDeltais;

	@FXML
	private TableView<HoaDonModel> tableBill;

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
	private TableColumn<HDCTModel, Float> priceBillCol;

	@FXML
	private TableColumn<HDCTModel, Float> paidBillCol;

	// ================================================

	@FXML
	private TableColumn<HoaDonModel, String> IDBillCol;

	@FXML
	private TableColumn<HoaDonModel, Date> date;

	@FXML
	private TableColumn<HoaDonModel, Float> moneyBill;

	@FXML
	private TableColumn<HoaDonModel, String> storyBillCol;

	// ==================================================

	@FXML
	private ComboBox<String> cbbMonth, cbbYear, cbbYear2;

	@FXML
	private TableView<ThongKeModel> tableRevenue2;

	@FXML
	private TableColumn<ThongKeModel, String> IDProOfRev;

	@FXML
	private TableColumn<ThongKeModel, String> IDBillOfRev;

	@FXML
	private TableColumn<ThongKeModel, Integer> quantityProOfRev;

	@FXML
	private TableColumn<ThongKeModel, Float> priceBillOfRev;

	@FXML
	private Button btnRevenue;

	@FXML
	private Label lblTongTien;

	@FXML
	private TextField txtFindStaff, txtFindClient, txtFindProduct, txtFindBill;

	@FXML
	private TextField txtMax, txtMin, txtAvg;

	private ObservableList<HoaDonModel> bill;

	private ObservableList<NhanVienModel> staff;

	private ObservableList<KhachHangModel> client;

	private ObservableList<SanPhamModel> product;

	private ObservableList<DichVuModel> service;

	private ObservableList<HDCTModel> billDeltais;

	private ObservableList<SanPhamModel> proBill;

	private ObservableList<ThongKeModel> revenue2;
	
	private ObservableList<HDDVModel> billSer;
	
	private ObservableList<DichVuModel> serOfBillSer;
	
	private ObservableList<ChiTietDVModel> billSerDeltais;

	private ObservableList revenue;

	// Export File PDF

	@FXML
	private Pane printPane, paneChidrent;

	@FXML
	private Button btnPrint, btnBackPrint, printButton;

	@FXML
	private TableView<HDCTModel> tablePrint;

	@FXML
	private TableColumn<HDCTModel, String> sanPhamCol;

	@FXML
	private TableColumn<HDCTModel, Float> dongiaCol;

	@FXML
	private TableColumn<HDCTModel, Integer> soLuongCol;

	@FXML
	private TableColumn<HDCTModel, Float> thanhTienCol;

	ObservableList<HDCTModel> print;
	
	// hóa đơn dịch vụ
	
	@FXML
    private TableView<HDDVModel> tableHDDV;
	
	@FXML
    private TableColumn<HDDVModel, String> IDHDDVCol;
	
	@FXML
    private TableColumn<HDDVModel, Date> dateHDDVCol;
	
	@FXML
    private TableColumn<HDDVModel, Float> paidHDDVCol;
	
	@FXML
    private TableColumn<HDDVModel, String> storyHDDVCol;
	
	@FXML
	private TextField IDBillSer, nameStaffOfBillSer, findSerOfBillSer, nameClientOfbillSer, phoneOfBillSer;
	
	@FXML
	private DatePicker  dateBillSer, ngayTraCT, ngayTraDK;

	@FXML
	private Label lblIDBill, lblDateBill, lblnameStaff, lblnameClient, lblphoneClient, lblSum, lblTK, lblTL;
	
	// Table dich vu trong hoa don dich vu
	
	@FXML
	private TableView<DichVuModel> tableSerOfBillSer;
	
	@FXML
	private TableColumn<DichVuModel, String> IDSerOfBilSerCol;
	
	@FXML
	private TableColumn<DichVuModel, String> nameSerOfBillSerCol;
	
	@FXML
	private TableColumn<DichVuModel, String> nameProOfBillSercol;
	
	@FXML
	private TableColumn<DichVuModel, Float> priceSerOfBillSerCol;
	
	// Table chi tiet dich vu
	
	@FXML
	private TableView<ChiTietDVModel> tableSerDeltais;
	
	@FXML
	private TableColumn<ChiTietDVModel, String> IDSerOfDeltaisCol;
	
	@FXML
	private TableColumn<ChiTietDVModel, String> namSerOfDeltaisCol;
	
	@FXML
	private TableColumn<ChiTietDVModel, Float> priceSerOfDeltaisCol;
	
	@FXML
	private TableColumn<ChiTietDVModel, Date> ngayThue;
	
	@FXML
	private TableColumn<ChiTietDVModel, Date> ngayTra;
	
	@FXML
	private TableColumn<ChiTietDVModel, Float> paidBillSerOfDeltaisCol;

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
		printPane.setVisible(false);
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
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(false);
		
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
			paneRevenue.setVisible(false);
			paneHDDV.setVisible(false);

			hiddenRecControl();
			recStaff.setVisible(true);
			btnStaff.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
			imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\team-management.png"));

			loadDataStaff();
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
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(false);

		hiddenRecControl();
		recProduct.setVisible(true);
		btnProduct.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order1.png"));

		loadDataProduct();

	}

	public void openRev() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);
		paneRevenue.setVisible(true);
		paneHDDV.setVisible(false);

		hiddenRecControl();

		btnRev.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgRev.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\statistics.png"));
	}

	public void openService() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(true);
		paneBill.setVisible(false);
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(false);

		hiddenRecControl();
		recService.setVisible(true);
		btnService.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgService.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\service.png"));

		loadService();
		loadCbbSer();
	}

	public void openBill() {

		paneBill.setVisible(true);
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(false);

		hiddenRecControl();
		recBill.setVisible(true);
		btnBill.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill1.png"));

		loadDataBill();
		loadDataBillDeltais();
		loadDataProBill();

	}

	public void openClient() {

		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(true);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(false);

		hiddenRecControl();
		recClient.setVisible(true);
		btnClient.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer1.png"));

		loadDataClient();

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
		btnRev.setTextFill(Color.BLACK);

		imgHome.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\home.png"));
		imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\manager.png"));
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer.png"));
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill-service.png"));
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order.png"));
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\receipt.png"));
		imgService.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\customer-service.png"));
		imgRev.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\economy.png"));
	}

	public void openPaid() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(true);

		hiddenRecControl();

		recPaid.setVisible(true);
		btnBillDeltais.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill-service1.png"));
		
		loadDataTableSerOfBillSer();
	}

	// Controll Setting

	public void showDatabase() {
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

		setBillDeltaislTable();
		loadDataBillDeltais();

		setCellRevenue();
		loadDataRevenue();

		setServiceTable();
		loadService();

		setProBill();
		loadDataProBill();

		setCellTableBill();
		loadDataBill();

		getCurrentDate();

		setCellRevenue2();

		setCellTablePrint();

		btnPrint.setOnAction(e -> {
			printPane.setVisible(true);
			loadTablePrint();

		});

		btnBackPrint.setOnAction(e -> {
			printPane.setVisible(false);
		});

		btnRevenue.setOnAction(e -> {
			if (rbtnFrom.isSelected()) {
				loadDataRevenueFromTo();
				sumFromTo();
			} else if (rbtnMonth.isSelected()) {
				loadDataRevenueMonth();
				sumMonth();
			} else {
				loadDataRevenueYear();
				sumYear();
			}
		});

		printButton.setOnAction(e -> {
			printBill();
		});

		tableProduct.setOnMouseClicked(e -> {
			clickTableProduct(e);
		});

		tableBill.setOnMouseClicked(e -> {
			clickTableBill(e);
			loadDataBillDeltais();
		});

		btnRemove.setOnAction(e -> {
			removeAll();
		});

		loadCbbDate();

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
		
		// hoa don dich vu
		setCellTableSerOfBillSer();
		loadDataTableSerOfBillSer();
		
		setCellTableSerOfBillSer();
		loadDataTableSerOfBillSer();
		
		setCellTableBillSerDeltais();
		loadTableBilSerDeltais();
		
		
		// loadLineChart();

		ToggleGroup group = new ToggleGroup();
		rbtnManage.setToggleGroup(group);
		rbtnStaff.setToggleGroup(group);

		ToggleGroup group1 = new ToggleGroup();
		rbtnMan.setToggleGroup(group1);
		rbtnWoman.setToggleGroup(group1);

		ToggleGroup group2 = new ToggleGroup();
		rbtnFrom.setToggleGroup(group2);
		rbtnMonth.setToggleGroup(group2);
		rbtnYear.setToggleGroup(group2);

	}

	// Home Controll

	public void setCellRevenue() {
		revenue = FXCollections.observableArrayList();
		productCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenSp"));
		priceCol.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("donGia"));

	}

	public void loadDataRevenue() {
		if (tblRevenue.getItems().size() >= 1) {
			tblRevenue.getItems().clear();
		}

		ArrayList<SanPhamModel> list = ISanPham.getInstance().selectAll();
		for (SanPhamModel sanPhamModel : list) {

			revenue.add(sanPhamModel);

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

		SanPhamModel model = new SanPhamModel(col.getMaSP(), col.getTenSp(), col.getDonGia(), col.getTenTH());
		if (alert.getResult() == ButtonType.OK) {

			ISanPham.getInstance().del(model);

			loadDataProduct();
			Message msg = new Message();
			msg.start(stage);

		}

	}

	public void clickTableProduct(MouseEvent e) {
		if (e.getClickCount() == 1) {
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

	public void setBillDeltaislTable() {
		billDeltais = FXCollections.observableArrayList();
		idProOfBill.setCellValueFactory(new PropertyValueFactory<HDCTModel, String>("masp"));
		nameBillCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, String>("tenSP"));
		qiantityBillCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Integer>("soLuong"));
		priceBillCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Float>("donGia"));
		paidBillCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Float>("thanhTien"));
	}

	// fill data theo mã hóa đơn
	public void loadDataBillDeltais() {
		if (tableBillDeltais.getItems().size() >= 1) {
			tableBillDeltais.getItems().clear();
		}

		String mahd = IDBill.getText();

		ArrayList<HDCTModel> list = IHDCT.getInstance().selectAllByIDBill(mahd);
		for (HDCTModel hdctModel : list) {
			billDeltais.add(hdctModel);
		}
		tableBillDeltais.setItems(billDeltais);
	}

	// Click table hóa đơn

	public void clickTableBill(MouseEvent event) {
		if (event.getClickCount() == 1) {
			HoaDonModel bill = tableBill.getSelectionModel().getSelectedItem();
			IDBill.setText(bill.getMahd());
		}

	}

	public void setProBill() {
		proBill = FXCollections.observableArrayList();
		nameProOfBill.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenSp"));
		priceProOfBill.setCellValueFactory(new PropertyValueFactory<SanPhamModel, Float>("donGia"));
		trademarkOfBill.setCellValueFactory(new PropertyValueFactory<SanPhamModel, String>("tenTH"));
	}

	// load data sản phẩm lên table sản phẩm
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

	// Hiển thị khách hàng theo số điện thoại
	public void getClient() {
		nameClientOfBill.setText(IKhachHang.getInstance().selectByPhone(phoneClientOfBill.getText()));
		nameClientOfbillSer.setText(IKhachHang.getInstance().selectByPhone(phoneOfBillSer.getText()));
	}

	// Lấy ra ngày hiện tại
	public void getCurrentDate() {
		Date date = new Date();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

		dateBill.setValue(ld);
		dateTo.setValue(ld);
		
		dateBillSer.setValue(ld);
		ngayTraCT.setValue(ld);
		nameStaffOfBillSer.setText(LoginController.nameStaff);
		IDBillSer.setText(IHoaDonDV.getInstance().selectMaxID());

		nameStaffOfBill.setText(LoginController.nameStaff);
		IDBill.setText(IHDCT.getInstance().selectMaxID());

		quantity.setText("1");
		sumMoney.setText(Float.valueOf(IHDCT.getInstance().selectPaid(IHDCT.getInstance().selectMaxID())).toString());
	}

	float money = 0;
	String tensp = "";

	// Click table sản phẩm trong hóa đơn lấy mã sản phẩm, đơn giá
	public void clickItemBill(MouseEvent event) {
		try {
			if (event.getClickCount() == 1) {
				SanPhamModel col = tableProBill.getSelectionModel().getSelectedItem();
				tensp = col.getTenSp();
				money = col.getDonGia();
			}
		} catch (Exception e) {
			Notification.alert(AlertType.ERROR, "Vui lòng nhập số lượng");
		}
	}

	// Tiện ích tính tiền
	public void comfy() {
		try {

			float tongTien = Float.valueOf(sumMoney.getText());
			float tienKhach = Float.valueOf(clientMoney.getText());
			float tienThua = tienKhach - tongTien;

			changeMoney.setText(Float.valueOf(tienThua).toString());
		} catch (NumberFormatException nfe) {
		}
	}

	// Thêm sản phẩm vào hóa đơn chi tiết
	public void insertBillDeltais() {
		String mahdct = AutoString.autoID("HDCT", "maHDCT", "HDCT");
		String sl = quantity.getText();

		if (tensp.equals("") || sl == "") {
			WarningForm wf = new WarningForm();
			wf.start(stage);

			quantity.requestFocus();

			return;
		}

		int quantity = Integer.parseInt(sl);
		int sumPro = 0;
		String masp = "";

		String mahd = IDBill.getText();

		// select sản tất cả hdct có mã hóa đơn mới nhất
		ArrayList<HDCTModel> models = IHDCT.getInstance().selectAllByIDBill(mahd);
		for (HDCTModel hdctModel : models) {
			if (hdctModel.getTenSP().equals(tensp)) {
				sumPro += hdctModel.getSoLuong(); // Lấy số lượng của sản phẩm có trong hdct theo ten
				masp = hdctModel.getMasp(); // Lấy ra mã sản phẩm
			}
		}

		if (sumPro != 0) {
			int sum = sumPro + quantity;

			// Cập nhật lại số lượng nếu sản phẩm đó được thêm vào rồi
			HDCTModel hdctModel = new HDCTModel(mahdct, 0, sum, mahd, masp);
			IHDCT.getInstance().update(hdctModel);
		} else {
			// Thêm sản phẩm vào hóa đơn chi tiết
			HDCTModel model = new HDCTModel(mahdct, money, quantity, mahd, tensp);
			IHDCT.getInstance().insert(model);

		}
		loadDataBillDeltais();
		sumMoney.setText(Float.valueOf(IHDCT.getInstance().selectPaid(mahd)).toString());

	}

	// Thêm hóa đơn
	public void insertBill() {

		String mahd = AutoString.autoID("HD", "maHD", "HoaDon");

		LocalDate localDate = dateBill.getValue();

		java.sql.Date myDate = java.sql.Date.valueOf(localDate);

		String nameKH = nameClientOfBill.getText();

		if (nameKH.equals("")) {
			Notification.alert(AlertType.INFORMATION, "Vui lòng nhập số điện thoại");
			phoneClientOfBill.requestFocus();
			return;
		}

		String nameStaff = nameStaffOfBill.getText();

		HoaDonModel model = new HoaDonModel(mahd, myDate, 0, nameKH, nameStaff, "Chưa Thanh Toán");
		IHoaDon.getInstance().insert(model);

		loadDataBill();

		loadDataBillDeltais();

		IDBill.setText(IHDCT.getInstance().selectMaxID());
	}

	public void setCellTableBill() {
		bill = FXCollections.observableArrayList();
		IDBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, String>("mahd"));
		moneyBill.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Float>("thanhToan"));
		date.setCellValueFactory(new PropertyValueFactory<HoaDonModel, Date>("ngay"));
		storyBillCol.setCellValueFactory(new PropertyValueFactory<HoaDonModel, String>("trangThai"));
	}

	public void loadDataBill() {
		if (tableBill.getItems().size() >= 1) {
			tableBill.getItems().clear();
		}

		ArrayList<HoaDonModel> list = IHoaDon.getInstance().selectAll();
		for (HoaDonModel model : list) {
			bill.add(model);
		}

		tableBill.setItems(bill);

	}

	// Delete Bill

	public void delBill() {
		HoaDonModel col = tableBill.getSelectionModel().getSelectedItem();

		HoaDonModel model = new HoaDonModel(col.getMahd(), col.getNgay(), col.getThanhToan(), col.getTenKH(),
				col.getTenNV(), col.getTrangThai());
		IHoaDon.getInstance().del(model);

		loadDataBill();
	}

	// xóa sản phẩm trong hóa đơn chi tiết
	public void delBillDeltais() {
		HDCTModel col = tableBillDeltais.getSelectionModel().getSelectedItem();

		HDCTModel model = new HDCTModel(col.getDonGia(), col.getSoLuong(), col.getTenSP(), col.getMasp(),
				col.getThanhTien());
		IHDCT.getInstance().del(model);

		loadDataBillDeltais();

		sumMoney.setText(Float.valueOf(IHDCT.getInstance().selectPaid(IHDCT.getInstance().selectMaxID())).toString());
	}

	// Cập nhật số lượng sản phẩm trong hóa dơn chi tiết
	public void updateDataBillDeltais() {
		HDCTModel col = tableBillDeltais.getSelectionModel().getSelectedItem();

		int soluong = Integer.parseInt(quantity.getText());

		HDCTModel model = new HDCTModel(col.getMaHDCT(), col.getDonGia(), soluong, IDBill.getText(), col.getMasp());
		IHDCT.getInstance().update(model);

		loadDataBillDeltais();

		Message msg = new Message();
		msg.start(stage);
	}

	// Cập nhật lại tiền thanh toán và trạng thái trong hóa dơn
	public void paidBill() {
		if (clientMoney.getText().equals("")) {
			Notification.alert(AlertType.INFORMATION, "Nhập tiền của khách");
			clientMoney.requestFocus();
			return;
		}

		if (Float.valueOf(changeMoney.getText()) < 0) {
			Notification.alert(AlertType.INFORMATION, "Thanh Toán thiếu tiền rồi ní ơi !!!");
			clientMoney.requestFocus();
			return;
		}

		String mahd = IDBill.getText();

		float paid = IHDCT.getInstance().selectPaid(mahd);

		HoaDonModel model = new HoaDonModel(mahd, null, paid, null, null, "Đã Thanh Toán");

		if (IHoaDon.getInstance().update(model) == 0) {

			Notification.alert(AlertType.INFORMATION, "Hóa Đơn đã được thanh toán");
			return;

		} else {

			IHoaDon.getInstance().update(model);
			Message smg = new Message();
			smg.start(stage);

			loadDataBill();

			loadDataBillDeltais();

		}

	}

	// xóa hóa đơn
	public void removeBill() {
		String mahd = IDBill.getText();

		HoaDonModel model = new HoaDonModel(mahd, null, money, mahd, mahd, mahd);
		IHoaDon.getInstance().del(model);

		Notification.alert(AlertType.INFORMATION, "Đã Hủy hóa đơn thành công");

		loadDataBill();

		loadDataBillDeltais();
	}

	// Xóa hóa đơn chi tiết theo mã hóa đơn
	public void removeBillDeltais() {
		String mahd = IHDCT.getInstance().selectMaxID();

		HDCTModel model = new HDCTModel(mahd, money, 0, mahd, mahd);
		IHDCT.getInstance().delByBill(model);

		loadDataBill();
		loadDataBillDeltais();

	}

	// Sự kiện hủy hóa đơn
	public void removeAll() {
		removeBillDeltais();
		removeBill();

		IDBill.setText(IHDCT.getInstance().selectMaxID());
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

	// Tìm kiếm nhân viên
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

	// Tìm Kiến khách hàng
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

	// Tìm kiếm sản phẩm
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

	// Tìm kiếm dịch vụ
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

	// fill data combbobox Thương Hiệu
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

	public void setCellRevenue2() {
		revenue2 = FXCollections.observableArrayList();
		IDProOfRev.setCellValueFactory(new PropertyValueFactory<ThongKeModel, String>("masp"));
		IDBillOfRev.setCellValueFactory(new PropertyValueFactory<ThongKeModel, String>("mahd"));
		quantityProOfRev.setCellValueFactory(new PropertyValueFactory<ThongKeModel, Integer>("soluong"));
		priceBillOfRev.setCellValueFactory(new PropertyValueFactory<ThongKeModel, Float>("giaBan"));
	}

	public void loadDataRevenueFromTo() {
		if (tableRevenue2.getItems().size() >= 1) {
			tableRevenue2.getItems().clear();
		}

		LocalDate ldfrom = dateFrom.getValue();
		String from = ldfrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate ldto = dateTo.getValue();
		String to = ldto.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		ArrayList<ThongKeModel> model = IThongKe.getInstance().selectFromTo(from.toString(), to.toString());

		for (ThongKeModel thongKeModel : model) {
			revenue2.add(thongKeModel);
		}

		tableRevenue2.setItems(revenue2);

		txtMax.setText(Float.valueOf(IThongKe.getInstance().getMaxBill(from.toString(), to.toString())).toString());
		txtMin.setText(Float.valueOf(IThongKe.getInstance().getMinBill(from.toString(), to.toString())).toString());
		txtAvg.setText(Float.valueOf(IThongKe.getInstance().getAVGBill(from.toString(), to.toString())).toString());
	}

	public void sumFromTo() {
		LocalDate ldfrom = dateFrom.getValue();
		String from = ldfrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate ldto = dateTo.getValue();
		String to = ldto.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		lblTongTien.setText(
				Float.valueOf(IThongKe.getInstance().sumPaidFromTo(from.toString(), to.toString())).toString());
	}

	public void loadDataRevenueMonth() {
		if (tableRevenue2.getItems().size() >= 1) {
			tableRevenue2.getItems().clear();
		}

		String month = cbbMonth.getValue();
		String thang = "";
		switch (month) {
		case "January":
			thang = "01";
			break;
		case "February":
			thang = "02";
			break;
		case "March":
			thang = "03";
			break;
		case "April":
			thang = "04";
			break;
		case "May":
			thang = "05";
			break;
		case "June":
			thang = "06";
			break;
		case "July":
			thang = "07";
			break;
		case "August":
			thang = "08";
			break;
		case "September":
			thang = "09";
			break;
		case "October":
			thang = "10";
			break;
		case "November":
			thang = "11";
			break;
		default:
			thang = "12";
			break;
		}

		String year = cbbYear.getValue();

		ArrayList<ThongKeModel> list = IThongKe.getInstance().selectMonth(thang, year);
		if (list == null) {
			Notification.alert(AlertType.INFORMATION, "Không có hóa đơn phù hợp");
			return;
		} else {
			for (ThongKeModel thongKeModel : list) {
				revenue2.add(thongKeModel);
			}

			tableRevenue2.setItems(revenue2);
		}

		txtMax.setText(Float.valueOf(IThongKe.getInstance().getMaxMonthBill(thang, year)).toString());
		txtMin.setText(Float.valueOf(IThongKe.getInstance().getMinMonthBill(thang, year)).toString());
		txtAvg.setText(Float.valueOf(IThongKe.getInstance().getAVGMonthBill(thang, year)).toString());
	}

	public void sumMonth() {
		String month = cbbMonth.getValue();
		String thang = "";
		switch (month) {
		case "January":
			thang = "01";
			break;
		case "February":
			thang = "02";
			break;
		case "March":
			thang = "03";
			break;
		case "April":
			thang = "04";
			break;
		case "May":
			thang = "05";
			break;
		case "June":
			thang = "06";
			break;
		case "July":
			thang = "07";
			break;
		case "August":
			thang = "08";
			break;
		case "September":
			thang = "09";
			break;
		case "October":
			thang = "10";
			break;
		case "November":
			thang = "11";
			break;
		default:
			thang = "12";
			break;
		}
		String year = cbbYear.getValue();

		lblTongTien.setText(Float.valueOf(IThongKe.getInstance().sumPaidMonth(thang, year)).toString());
	}

	public void loadDataRevenueYear() {
		if (tableRevenue2.getItems().size() >= 1) {
			tableRevenue2.getItems().clear();
		}

		String year = cbbYear2.getValue();

		ArrayList<ThongKeModel> list = IThongKe.getInstance().selectYear(year);
		if (list == null) {
			Notification.alert(AlertType.INFORMATION, "Không có hóa đơn phù hợp");
			return;
		} else {
			for (ThongKeModel thongKeModel : list) {
				revenue2.add(thongKeModel);
			}
			tableRevenue2.setItems(revenue2);
		}

		txtMax.setText(Float.valueOf(IThongKe.getInstance().getMaxYearBill(year)).toString());
		txtMin.setText(Float.valueOf(IThongKe.getInstance().getMinYearBill(year)).toString());
		txtAvg.setText(Float.valueOf(IThongKe.getInstance().getAVGYearBill(year)).toString());
	}

	public void sumYear() {
		String year = cbbYear2.getValue();
		lblTongTien.setText(Float.valueOf(IThongKe.getInstance().sumPaidYear(year)).toString());
	}

	public void loadCbbDate() {
		String[] month = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		for (String string : month) {
			cbbMonth.getItems().add(string);
		}

		String[] year = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030",
				"2031", "2032", "2033", "2034", "2035", "2036" };

		for (String string : year) {
			cbbYear.getItems().add(string);
			cbbYear2.getItems().add(string);
		}
	}

	public void findProOfBill() {

		if (tableProBill.getItems().size() >= 1) {
			tableProBill.getItems().clear();
		}

		String masp = txtFindStaffOfBill.getText();
		String tensp = txtFindStaffOfBill.getText();

		ArrayList<SanPhamModel> list = ISanPham.getInstance().selectByName(masp, "%" + tensp + "%");

		for (SanPhamModel sanPhamModel : list) {
			proBill.add(sanPhamModel);
		}
		tableProBill.setItems(proBill);
	}

	public void printBill() {

		ArrayList<HDCTModel> list = IHDCT.getInstance().selectAllByIDBill(lblIDBill.getText());
		for (HDCTModel hdctModel : list) {

		}
		try (Document doc = new Document()) {
			Page page = doc.getPages().add();

			HtmlFragment frag = new HtmlFragment("<ol>"
					+ "<li style =\"list-style-type:none; text-align:center; font-size: 28px; font-weight: bold; font-family: \"Times New Roman\", Times, serif;\">THE STUDIO BREAKFAST</li>"
					+ "<li style =\"list-style-type:none; text-align:center; font-size: 18px; font-weight: 300;\">Địa chỉ: FPT Polytechnic Cái Răng Cần Thơ</li>"
					+ "<li style =\"list-style-type:none; text-align:center; font-size: 23px; font-weight: bold; font-family: \"Times New Roman\", Times, serif;\">Hóa Đơn Bán Hàng</li>"
					+ "<li style =\"list-style-type:none; text-align:center; font-size: 18px; font-weight: 300;\">Mã Hóa Đơn: "
					+ IDBill.getText() + " </li>"
					+ "<li style =\"list-style-type:none; text-align:center; font-size: 18px; font-weight: 300;\">Ngày Lập HĐ: "
					+ dateBill.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString() + " </li>"
					+ "<li style =\"list-style-type:none; text-align:left; font-size: 18px; font-weight: 300;\">Tên Nhân Viên: "
					+ nameStaffOfBill.getText() + " </li>"
					+ "<li style =\"list-style-type:none; text-align:left; font-size: 18px; font-weight: 300;\">Tên Khách Hàng: "
					+ nameClientOfBill.getText() + " </li>"
					+ "<li style =\"list-style-type:none; text-align:left; font-size: 18px; font-weight: 300;\">Số Điện Thoại: "
					+ phoneClientOfBill.getText() + " </li>" + "<li></li>" + "<li></li>"
					+ "<li style = \"text-align:center;\">"
					+ "-------------------------------------------------------------------------" + "</li>"
					+ "<li></li>" + " <li style=\"list-style-type:none;\">\r\n"
					+ "    <table style=\"text-align: center;\">\r\n" + "      <tr>\r\n"
					+ "        <th style=\"padding: 15px;\">\r\n" + "          Tên Sản Phẩm\r\n" + "        </th>\r\n"
					+ "        <th style=\"padding: 15px;\">\r\n" + "          Giá Bán\r\n" + "        </th>\r\n"
					+ "        <th style=\"padding: 15px;\">\r\n" + "          Số Lượng\r\n" + "        </th>\r\n"
					+ "        <th style=\"padding: 15px;\">\r\n" + "          Thanh Toán\r\n" + "        </th>\r\n"
					+ "      </tr>\r\n" + "\r\n" + "      <tr>\r\n" + "      </tr>\r\n" + "</table>\r\n" + "  </li>"
					+ "</ol>");

			doc.getPages().get_Item(1).getParagraphs().add(frag);

			doc.save("Bill2.pdf");
		}

	}

	public void setCellTablePrint() {
		print = FXCollections.observableArrayList();
		sanPhamCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, String>("tenSP"));
		dongiaCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Float>("donGia"));
		soLuongCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Integer>("soLuong"));
		thanhTienCol.setCellValueFactory(new PropertyValueFactory<HDCTModel, Float>("thanhTien"));
	}

	public void loadTablePrint() {
		if (tablePrint.getItems().size() >= 1) {
			tablePrint.getItems().clear();
		}

		String mahd = IDBill.getText();

		ArrayList<HDCTModel> list = IHDCT.getInstance().selectAllByIDBill(mahd);
		for (HDCTModel hdctModel : list) {
			print.add(hdctModel);
		}
		tablePrint.setItems(print);

		lblIDBill.setText(mahd);
		lblDateBill.setText(dateBill.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
		lblnameStaff.setText(nameStaffOfBill.getText());
		lblnameClient.setText(nameClientOfBill.getText());
		lblphoneClient.setText(phoneClientOfBill.getText());
		lblSum.setText(Float.valueOf(sumMoney.getText()).toString());
		lblTK.setText(clientMoney.getText());
		lblTL.setText(changeMoney.getText());
	}
	
	
	// HOA DON DICH VU
	public void setCellTableBillSer() {
		billSer = FXCollections.observableArrayList();
		IDHDDVCol.setCellValueFactory(new PropertyValueFactory<HDDVModel, String>("maHDDV"));
		dateHDDVCol.setCellValueFactory(new PropertyValueFactory<HDDVModel, Date>("ngayLap"));
		paidHDDVCol.setCellValueFactory(new PropertyValueFactory<HDDVModel, Float>("thanhToan"));
		storyHDDVCol.setCellValueFactory(new PropertyValueFactory<HDDVModel, String>("trangThai"));
	}
	
	public void loadDataBillSer() {
		if (tableHDDV.getItems().size() >= 1) {
			tableHDDV.getItems().clear();
		}
		
		ArrayList<HDDVModel> list = IHoaDonDV.getInstance().selectAll();
		for (HDDVModel hddvModel : list) {
			billSer.add(hddvModel);
		}
		tableHDDV.setItems(billSer);
	}
	
	public void insertBillSer() {
		String mahddv = IDBillSer.getText();
		
		java.sql.Date ngayLap = java.sql.Date.valueOf(dateBillSer.getValue());
		String tenkh = nameClientOfbillSer.getText();
		String tennv = nameStaffOfBillSer.getText();
		
		HDDVModel model = new HDDVModel(mahddv, ngayLap, 0, tenkh, tennv, "Chưa Thanh Toán");
		IHoaDonDV.getInstance().insert(model);
	}
	
	
	//load table dich vu trong hoa don dich vu
	public void setCellTableSerOfBillSer() {
		serOfBillSer = FXCollections.observableArrayList();
		IDSerOfBilSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("maDV"));
		nameSerOfBillSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("tenDV"));
		nameProOfBillSercol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("tenSp"));
		priceSerOfBillSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, Float>("giaDV"));
	}
	
	public void loadDataTableSerOfBillSer() {
		if(tableSerOfBillSer.getItems().size() >= 1) {
			tableSerOfBillSer.getItems().clear();
		}
		
		ArrayList<DichVuModel> list = IDichVu.getInstance().selectAll();
		for (DichVuModel dichVuModel : list) {
			serOfBillSer.add(dichVuModel);
		}
		
		tableSerOfBillSer.setItems(serOfBillSer);
	}
	
	String madv = "";
	String tendv = "";
	float giadv = 0;
	public void clickItemSerOfBill(MouseEvent event) {
		if (event.getClickCount() == 1) {
			DichVuModel col = tableSerOfBillSer.getSelectionModel().getSelectedItem();
			madv = col.getMaDV();
			tendv = col.getTenDV();
			giadv = col.getGiaDV();
		}
	}
	// load table chi tiet dich vu
	
	public void setCellTableBillSerDeltais() {
		billSerDeltais = FXCollections.observableArrayList();
		IDSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, String> ("maDV"));
		namSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, String> ("tenDV"));
		priceSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Float> ("giaDV"));
		ngayThue.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Date> ("ngayThue"));
		ngayTra.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Date> ("ngayTraCT"));
		paidBillSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Float> ("thanhTien"));
	}
	
	public void loadTableBilSerDeltais() {
		if (tableSerDeltais.getItems().size() >= 1) {
			tableSerDeltais.getItems().clear();
		}
		
		ArrayList<ChiTietDVModel> list = IChiTietDV.getInstance().selectAllByID(IDBillSer.getText());
		for (ChiTietDVModel model : list) {
			billSerDeltais.add(model);
		}
		tableSerDeltais.setItems(billSerDeltais);
	}
	
	public void inserBillSerDeltais() {
		String macthddv = AutoString.autoID("CTHDDV", "MaCTHDDV", "CHITIETHOADONDV");
		
		if (dateBillSer.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("") 
				|| ngayTraDK.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")
				|| ngayTraCT.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")) {
			Notification.alert(AlertType.INFORMATION, "Vui lòng chọn ngày");
			return;
		}
		
		java.sql.Date ngayThue = java.sql.Date.valueOf(dateBillSer.getValue());
		java.sql.Date ngaydk = java.sql.Date.valueOf(ngayTraDK.getValue());
		java.sql.Date ngayct = java.sql.Date.valueOf(ngayTraCT.getValue());
		
		ChiTietDVModel model = new ChiTietDVModel(macthddv, giadv, ngayThue, ngaydk, ngayct, macthddv, IDBillSer.getText());
		IChiTietDV.getInstance().insert(model);
		
		loadTableBilSerDeltais();
	}
	
	public void delBillSerDeltais() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Thông Báo");
		alert.setHeaderText("Thông báo đến bạn");
		alert.setContentText("Bạn có muốn xóa hông");
		alert.showAndWait();
		
		if (alert.getResult() == ButtonType.OK) {
			ChiTietDVModel model = new ChiTietDVModel(null, 0, null, null, null, tendv, null);
			IChiTietDV.getInstance().del(model);
			
			Message msg = new Message();
			msg.start(stage);
		}
	}

}
