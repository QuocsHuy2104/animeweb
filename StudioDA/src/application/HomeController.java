package application;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlFragment;
import com.aspose.pdf.Page;

import IDAO.IChiTietDV;
import IDAO.IDichVu;
import IDAO.IHDCT;
import IDAO.IHoaDon;
import IDAO.IHoaDonDV;
import IDAO.IKhachHang;
import IDAO.ILichSu;
import IDAO.INhanVien;
import IDAO.ISanPham;
import IDAO.IThongKe;
import IDAO.IThuongHieu;
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
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.ChiTietDVModel;
import model.DichVuModel;
import model.HDCTModel;
import model.HDDVModel;
import model.HoaDonModel;
import model.KhachHangModel;
import model.LichSuModel;
import model.NhanVienModel;
import model.SanPhamModel;
import model.ThongKeModel;
import model.Trademark;
import utilities.AutoString;
import utilities.DateSimple;
import utilities.EmailRegex;
import utilities.MessageDigest;
import utilities.Notification;
import utilities.PasswordRegex;
import utilities.PhoneRegex;

public class HomeController extends javax.swing.JFrame implements Initializable, Printable {

	@FXML
	private ImageView imageHeart, imgHome, imgStaff, imgClient, imgProduct, imgService, imgBill, imgPaid, setting,
			imgRev;

	@FXML
	private Pane MenuPane, ContentPane, paneSetting, paneStaff, paneHome, paneClient, paneProduct, paneService,
			paneBill, paneRevenue, paneHDDV, paneHistory, paneLock;

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
	private TextField IDStaff, nameStaff, phoneStaff, emailStaff, IDTrademark, nameTrademark;

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

	private ObservableList<Trademark> trademarkTable;

	private ObservableList<LichSuModel> billHistory;

	private ObservableList<LichSuModel> billSerHistory;

	private ObservableList<NhanVienModel> lock;

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
	private TextField IDBillSer, nameStaffOfBillSer, findSerOfBillSer, nameClientOfbillSer, phoneOfBillSer, txtTongTien,
			txtTKD, txtTienThua, txtFindTrademark;

	@FXML
	private DatePicker dateBillSer, ngayTraCT, ngayTraDK;

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

	// Table Thuong Hieu

	@FXML
	private TableView<Trademark> tableTrademark;

	@FXML
	private TableColumn<Trademark, String> IDTrademarkCol;

	@FXML
	private TableColumn<Trademark, String> nameTrademarkCol;

	// lich su hoa don

	@FXML
	private Button btnHistory;

	@FXML
	private ImageView imgHistory;

	@FXML
	private TextField txtFindBillHis;

	@FXML
	private TableView<LichSuModel> tableBillHis;

	@FXML
	private TableColumn<LichSuModel, String> idBillHisCol;

	@FXML
	private TableColumn<LichSuModel, String> nameClientBillHisCol;

	@FXML
	private TableColumn<LichSuModel, String> phoneClientBillHisCol;

	@FXML
	private TableColumn<LichSuModel, Date> dateBillHisCol;

	@FXML
	private TableColumn<LichSuModel, Double> paidBillHisCol;

	@FXML
	private TableView<LichSuModel> tableBillSerHis;

	@FXML
	private TableColumn<LichSuModel, String> idBillSerHisCol;

	@FXML
	private TableColumn<LichSuModel, String> nameClientSerBillHisCol;

	@FXML
	private TableColumn<LichSuModel, String> phoneClientSerBillHisCol;

	@FXML
	private TableColumn<LichSuModel, Date> dateBillSerHisCol;

	@FXML
	private TableColumn<LichSuModel, Double> paidBillSerHisCol;

	@FXML
	private TableView<NhanVienModel> tableLock;

	@FXML
	private TableColumn<NhanVienModel, String> nameLockCol;

	@FXML
	private TableColumn<NhanVienModel, String> idLockCol;

	@FXML
	private TableColumn<NhanVienModel, String> roleLockCol;

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
		paneHistory.setVisible(false);

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
			paneHistory.setVisible(false);

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
		paneHistory.setVisible(false);

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
		paneHistory.setVisible(false);

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
		paneHistory.setVisible(false);

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
		paneHistory.setVisible(false);

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
		paneHistory.setVisible(false);

		hiddenRecControl();
		recClient.setVisible(true);
		btnClient.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer1.png"));

		loadDataClient();

	}

	public void openHistory() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneService.setVisible(false);
		paneBill.setVisible(false);
		paneRevenue.setVisible(false);
		paneHDDV.setVisible(false);
		paneHistory.setVisible(true);

		hiddenRecControl();
		btnHistory.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
		imgHistory.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\o-clock.png"));

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
		btnHistory.setTextFill(Color.BLACK);

		imgHome.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\home.png"));
		imgStaff.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\manager.png"));
		imgClient.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\costumer.png"));
		imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill-service.png"));
		imgProduct.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\order.png"));
		imgBill.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\receipt.png"));
		imgService.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\customer-service.png"));
		imgRev.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\economy.png"));
		imgHistory.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\clock.png"));
	}

	public void openPaid() {

		if (LoginController.roles == 1) {
			paneStaff.setVisible(false);
			paneHome.setVisible(false);
			paneClient.setVisible(false);
			paneProduct.setVisible(false);
			paneService.setVisible(false);
			paneBill.setVisible(false);
			paneRevenue.setVisible(false);
			paneHDDV.setVisible(true);
			paneHistory.setVisible(false);

			hiddenRecControl();

			recPaid.setVisible(true);
			btnBillDeltais.setStyle("-fx-text-fill: #33b0f2; -fx-background-color: none");
			imgPaid.setImage(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\bill-service1.png"));

			loadDataTableSerOfBillSer();
		} else {
			ErrorForm errorForm = new ErrorForm();
			errorForm.start(stage);
		}
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
		paneLock.setVisible(false);
	}

	public void showPaneSystem() {
		paneSystem.setVisible(true);
		paneAccount.setVisible(false);
		paneLock.setVisible(false);
	}

	public void showPaneLocked() {
		if(LoginController.roles == 1) {
			paneLock.setVisible(true);
			paneSystem.setVisible(false);
			paneAccount.setVisible(false);
		} else Notification.alert(AlertType.CONFIRMATION, "Đăng nhập tài khoản trưởng phòng để xem");
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
			if (!rbtnFrom.isSelected()) {
				Notification.alert(AlertType.ERROR, "Click chọn thống kê");
				return;
			}

			if (rbtnFrom.isSelected()) {
				loadDataRevenueFromTo();
				sumFromTo();
			}
		});

		printButton.setOnAction(e -> {
			loadTablePrint();
			FilePrintClicked();
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

		circleLogo
				.setFill(new ImagePattern(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\logo.png")));

		recLogo.setFill(new ImagePattern(new Image("C:\\Users\\HP\\workspage-udpm\\StudioDA\\src\\image\\logo.png")));
		lblMail.setText(LoginController.email);
		lblNameStaff.setText(LoginController.nameStaff);

		txtTongTien.setText(Float
				.valueOf(IChiTietDV.getInstance().selectPaid(IDBillSer.getText(),
						java.sql.Date.valueOf(ngayTraCT.getValue()), java.sql.Date.valueOf(dateBillSer.getValue())))
				.toString());

//		try {
//			ReadExcel.downDB();
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}

		loadCbbTrademark();

		loadCbbSer();

		// hoa don dich vu
		setCellTableBillSer();
		loadDataBillSer();

		setCellTableSerOfBillSer();
		loadDataTableSerOfBillSer();

		setCellTableBillSerDeltais();
		loadTableBilSerDeltais();

		loadLineChart();

		ToggleGroup group = new ToggleGroup();
		rbtnManage.setToggleGroup(group);
		rbtnStaff.setToggleGroup(group);

		ToggleGroup group1 = new ToggleGroup();
		rbtnMan.setToggleGroup(group1);
		rbtnWoman.setToggleGroup(group1);

		ToggleGroup group2 = new ToggleGroup();
		rbtnFrom.setToggleGroup(group2);

		setCellTableTrademark();
		loadTableTrademark();

		setCellTableBillHis();
		setCellTableBillSerHis();

		loadBillHistory();
		loadBillSerHistory();

		loadCbbMonth();

		setCellTableLock();

	}

// Code for Staff Controller --------------------------------------------------------------------------------------------------------------------------------

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

// Function insert staff into database

	public void insertStaff() {

		String id = IDStaff.getText().trim();
		String ten = nameStaff.getText().trim();
		String diachi = addressStaff.getText();
		String sdt = phoneStaff.getText().trim();
		String email = emailStaff.getText();

		// Check input value
		String pass = password.getText();
		if (id.equals("") || ten.equals("") || diachi.equals("") || sdt.equals("") || pass.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}

		// Check phone
		try {
			PhoneRegex.checkPhone(sdt);
		} catch (PhoneRegex e) {
			return;
		}

		// Create new Password Regex
		PasswordRegex passwordRegex = new PasswordRegex();

		if (passwordRegex.validate(pass) == false) {
			Notification.alert(AlertType.ERROR,
					"Tối thiểu 8 ký tự, ít nhất 1 chữ cái in hoa và thường, 1 số và 1 ký tự đặc biệt:");
			return;
		}
		try {

			// MD5
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

	public void saveStaff() {
		ArrayList<NhanVienModel> list = INhanVien.getInstance().selectAll();
		for (NhanVienModel nhanVienModel : list) {
			if (nhanVienModel.getMaNV().equals(IDStaff.getText())) {
				updateStaff();
				return;
			}
		}

		insertStaff();
	}

//Function to update staff
	public void updateStaff() {
		String id = IDStaff.getText().trim();
		String ten = nameStaff.getText().trim();
		String diachi = addressStaff.getText();
		String sdt = phoneStaff.getText().trim();
		String email = emailStaff.getText();
		try {
			PhoneRegex.checkPhone(sdt);
		} catch (PhoneRegex e) {
			return;
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

// Funciton auto generated email
	public void gmailStaff() {
		emailStaff.setText(AutoString.convertString(AutoString.autoEmail(nameStaff.getText())));
	}

//Function delete staff
	public void deleteStaff() {
		try {
			NhanVienModel col = tableStaff.getSelectionModel().getSelectedItem();

			if (col.getMaNV().toLowerCase().equals(LoginController.user)) {
				Notification.alert(AlertType.INFORMATION, "Bạn không thể xóa chính bản thân");
				return;
			}

			NhanVienModel model = new NhanVienModel(col.getMaNV(), col.getTenNV(), col.getDiaChi(), col.getSdt(),
					col.getEmail(), col.getMatKhau(), col.isVaiTro(), col.isTrangThai());

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Studio Thông Báo");
			alert.setHeaderText("Thông báo");
			alert.setContentText("Bạn có chắc chắn muốn xóa hông");
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) {
				INhanVien.getInstance().del(model);

				loadDataStaff();
				cleanStaff();
			}

		} catch (Exception e) {
			Notification.alert(AlertType.WARNING, "Nhân Viên này hiện đang bán hàng");
		}

	}

//Function click Items for Staff
	public void clickItem(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			NhanVienModel col = tableStaff.getSelectionModel().getSelectedItem();
			IDStaff.setText(col.getMaNV());
			nameStaff.setText(col.getTenNV());
			phoneStaff.setText(col.getSdt());
			emailStaff.setText(col.getEmail());
			addressStaff.setText(col.getDiaChi());
			rbtnStaff.setSelected(col.isVaiTro() == false ? true : false);
			rbtnManage.setSelected(col.isVaiTro());
		}
	}

// Tìm kiếm nhân viên
	public void findStaff() {

		String manv = txtFindStaff.getText();
		NhanVienModel model = new NhanVienModel(manv, "%" + manv + "%", null, null, null, null, false, false);

		if (manv.equals("")) {
			loadDataStaff();
		}

		if (tableStaff.getItems().size() >= 1) {
			tableStaff.getItems().clear();
		}

		NhanVienModel model1 = INhanVien.getInstance().selectByID(model);
		staff.add(model1);
		tableStaff.setItems(staff);

	}

// Code for Client Controller -----------------------------------------------------------------------------------------------------------------------------

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

		ArrayList<KhachHangModel> list = IKhachHang.getInstance().selectAll();
		for (KhachHangModel khachHangModel : list) {
			if (khachHangModel.getMaKH().equals(IDClient.getText())) {
				updateClient();
				loadDataClient();

				cleanClient();
				return;
			}
		}

		insertClient();
		loadDataClient();
		cleanClient();
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

		// check number phone
		try {
			PhoneRegex.checkPhone(phone);
		} catch (PhoneRegex e) {
			return;
		}

		// check format email

		EmailRegex emailRegex = new EmailRegex();
		if (!emailRegex.validate(email)) {
			Notification.alert(AlertType.CONFIRMATION, "Email không đúng định dạng");
			return;
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
		alert.setContentText("Bạn có chắc chắn muốn xóa hông");
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
			rbtnMan.setSelected(col.getGioiTinh() == "Nam" ? true : false);
			rbtnWoman.setSelected(col.getGioiTinh() == "Nữ" ? true : false);
		}
	}

	public void autoMailClient() {
		emailClient.setText(AutoString.convertString(AutoString.autoEmail(nameClient.getText())));
		IDClient.setText(AutoString.autoID("KH", "makh", "KhachHang"));
	}

// Tìm Kiến khách hàng

	public void findClient() {
		String makh = txtFindClient.getText();

		if (makh.equals("")) {
			loadDataClient();
		}

		KhachHangModel model = new KhachHangModel(null, null, null, "%" + makh + "%", null, null, false);

		if (tableClient.getItems().size() >= 1) {
			tableClient.getItems().clear();
		}

		KhachHangModel kh = IKhachHang.getInstance().selectByID(model);
		client.add(kh);
		tableClient.setItems(client);
	}

// Product Controller ----------------------------------------------------------------------------------------------------------------------------------------

	public void insertPro() {
		String id = IDProduct.getText();
		String name = nameProduct.getText();

		if (id.equals("") || name.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}

		Float price = Float.parseFloat(priceProduct.getText());
		String tenth = trademark.getValue();

		if (price < 0) {
			Notification.alert(AlertType.WARNING, "Giá không được là số âm");
			return;
		}

		SanPhamModel model = new SanPhamModel(id, name, price, tenth);
		ISanPham.getInstance().insert(model);

		Message msg = new Message();
		msg.start(stage);

	}

// update product
	public void updatePro() {

		SanPhamModel click = tableProduct.getSelectionModel().getSelectedItem();

		String name = nameProduct.getText();
		Float price = Float.parseFloat(priceProduct.getText());
		String th = trademark.getValue();

		if (name.equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin");
			return;
		}

		if (price < 0) {
			Notification.alert(AlertType.WARNING, "Giá Tiền không được âm");
			return;
		}

		SanPhamModel model = new SanPhamModel(click.getMaSP(), name, giadv, th);
		ISanPham.getInstance().update(model);

		Message msg = new Message();
		msg.start(stage);

	}

	public void savePro() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText("Lưu hoặc cập nhật sản phẩm");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {

			ArrayList<SanPhamModel> list = ISanPham.getInstance().selectAll();
			for (SanPhamModel sanPhamModel : list) {
				if (sanPhamModel.getMaSP().equals(IDProduct.getText())) {
					updatePro();
					loadDataProduct();

					cleanPro();
					return;
				}
			}

			insertPro();
			loadDataProduct();

			cleanPro();
		} else {
			ArrayList<Trademark> list = IThuongHieu.getInstance().selectAll();
			for (Trademark tr : list) {
				if (tr.getMaTH().equals(IDTrademark.getText())) {
					updateTrademark();
					loadTableTrademark();
					cleanTrademark();
					return;
				}
			}

			insertTrademark();
			loadTableTrademark();
			cleanTrademark();
		}
	}

	public void cleanPro() {
		nameProduct.setText("");
		IDProduct.setText("");
		priceProduct.setText("");
	}

// Delete Product

	public void deleteProduct() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Studio Thông Báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText("Chon OK de xoa san pham");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {

			SanPhamModel col = tableProduct.getSelectionModel().getSelectedItem();

			Alert alert2 = new Alert(AlertType.CONFIRMATION);
			alert2.setTitle("Studio Thông Báo");
			alert2.setHeaderText("Thông báo");
			alert2.setContentText("Bạn có muốn xóa san pham nay");
			alert2.showAndWait();

			SanPhamModel model = new SanPhamModel(col.getMaSP(), col.getTenSp(), col.getDonGia(), col.getTenTH());
			if (alert.getResult() == ButtonType.OK) {

				ISanPham.getInstance().del(model);

				loadDataProduct();
				Message msg = new Message();
				msg.start(stage);

			}

		} else if (alert.getResult() == ButtonType.CANCEL) {

			if (IDTrademark.getText().equals("")) {
				Notification.alert(AlertType.WARNING, "Vui lòng nhập mã Thương Hiệu để xóa");
				return;
			}

			Alert alert2 = new Alert(AlertType.CONFIRMATION);
			alert2.setTitle("Studio Thông Báo");
			alert2.setHeaderText("Thông báo");
			alert2.setContentText("Bạn có muốn xóa Thương Hiệu nay");
			alert2.showAndWait();

			if (alert2.getResult() == ButtonType.OK) {

				IThuongHieu.getInstance().del(new Trademark(IDTrademark.getText(), null));

				loadTableTrademark();
				Message msg = new Message();
				msg.start(stage);

			}
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

// Tìm kiếm sản phẩm
	public void findProduct() {

		String masp = txtFindProduct.getText();

		if (masp.equals("")) {
			loadDataProduct();
		}

		SanPhamModel model = new SanPhamModel("%" + masp + "%", "%" + masp + "%", 0, null);
		SanPhamModel sp = ISanPham.getInstance().selectByID(model);

		if (tableProduct.getItems().size() >= 1) {
			tableProduct.getItems().clear();
		}
		product.add(sp);

		tableProduct.setItems(product);
	}

// find product
	public void findProOfBill() {

		if (tableProBill.getItems().size() >= 1) {
			tableProBill.getItems().clear();
		}

		String masp = txtFindStaffOfBill.getText();
		String tensp = txtFindStaffOfBill.getText();

		if (masp.equals(""))
			loadDataBill();

		ArrayList<SanPhamModel> list = ISanPham.getInstance().selectAllByID(masp, "%" + tensp + "%");

		for (SanPhamModel sanPhamModel : list) {
			proBill.add(sanPhamModel);
		}
		tableProBill.setItems(proBill);
	}

// Service Controller -----------------------------------------------------------------------------------------------------------------------------------------

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

		String nameService = nameSer.getText();
		if (nameService.equals("")) {
			Notification.alert(AlertType.CONFIRMATION, "Vui lòng nhập đầy đủ thông tin!");
			return;
		}
		String id = IDSer.getText();
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
		alert.setContentText("Bạn có chắc chắn muốn xóa hông");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {

			IDichVu.getInstance().del(model);

			loadService();

			Message msg = new Message();
			msg.start(stage);

		}

	}

	public void saveSer() {
		ArrayList<DichVuModel> list = IDichVu.getInstance().selectAll();
		for (DichVuModel dichVuModel : list) {
			if (dichVuModel.getMaDV().equals(IDSer.getText())) {
				updateSer();
				loadService();

				clearSer();
				Message msg = new Message();
				msg.start(stage);
				return;
			}
		}

		insertSer();
		loadService();

		clearSer();
		Message msg = new Message();
		msg.start(stage);
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

// Bill Controller ------------------------------------------------------------------------------------------------------------------------------------

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
			try {
				HoaDonModel bill = tableBill.getSelectionModel().getSelectedItem();
				IDBill.setText(bill.getMahd());
				nameClientOfBill.setText(bill.getTenKH());
				phoneClientOfBill.setText(IKhachHang.getInstance().selectByName(bill.getTenKH()));
				dateBill.setValue(
						Instant.ofEpochMilli(bill.getNgay().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				sumMoney.setText(AutoString.formatMoney(IHDCT.getInstance().selectPaid(bill.getMahd())).toString());
			} catch (Exception e) {
			}
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

			float tongTien = IHDCT.getInstance().selectPaid(IDBill.getText());
			float tienKhach = Float.valueOf(clientMoney.getText());
			float tienThua = tienKhach - tongTien;

			changeMoney.setText(AutoString.formatMoney(tienThua));
		} catch (NumberFormatException nfe) {
		}
	}

	public void getChangeMoney() {
		try {
			float tongTien2 = IChiTietDV.getInstance().sumPriceSer(IDBillSer.getText());
			float tienKhach2 = Float.valueOf(txtTKD.getText());
			float tienThua2 = tienKhach2 - tongTien2;

			txtTienThua.setText(AutoString.formatMoney(tienThua2));
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
			sumMoney.setText(AutoString
					.formatMoney(Float.valueOf(IHDCT.getInstance().selectPaid(IHDCT.getInstance().selectMaxID())))
					.toString());

		} else {
			// Thêm sản phẩm vào hóa đơn chi tiết
			HDCTModel model = new HDCTModel(mahdct, money, quantity, mahd, tensp);
			IHDCT.getInstance().insert(model);

		}
		loadDataBillDeltais();
		sumMoney.setText(AutoString.formatMoney(Float.valueOf(IHDCT.getInstance().selectPaid(mahd))).toString());

	}

	// Thêm hóa đơn
	public void insertBill() {

		String mahd = AutoString.autoID("HD", "maHD", "HoaDon");

		LocalDate localDate = dateBill.getValue();
		java.sql.Date myDate = java.sql.Date.valueOf(localDate);
		String strMyDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String nameKH = nameClientOfBill.getText();

		if (phoneClientOfBill.getText().equals("")) {
			Notification.alert(AlertType.INFORMATION, "Vui lòng nhập số điện thoại");
			phoneClientOfBill.requestFocus();
			return;
		}

		if (IKhachHang.getInstance().selectByPhone(phoneClientOfBill.getText()).equals("")) {
			Notification.alert(AlertType.ERROR, "Không tìm thấy khách hàng có số điện thoại này");
			return;
		}

		Date date = new Date();
		String dateCurrent = DateSimple.asString(date);

		if (strMyDate.equals(dateCurrent)) {
			String nameStaff = nameStaffOfBill.getText();

			HoaDonModel model = new HoaDonModel(mahd, myDate, 0, nameKH, nameStaff, "Chưa Thanh Toán");
			IHoaDon.getInstance().insert(model);

			loadDataBill();

			loadDataBillDeltais();

			IDBill.setText(IHDCT.getInstance().selectMaxID());
		} else {
			Notification.alert(AlertType.ERROR, "Ngày tạo phải là ngày hiện tại " + strMyDate + " " + dateCurrent);
			return;
		}

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

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Thông Báo");
		alert.setHeaderText("Vui lòng xác nhận");
		alert.setContentText("Bạn có muốn xóa hông ?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			HoaDonModel model = new HoaDonModel(col.getMahd(), col.getNgay(), col.getThanhToan(), col.getTenKH(),
					col.getTenNV(), col.getTrangThai());
			IHoaDon.getInstance().del(model);

			loadDataBill();
		}
	}

	// xóa sản phẩm trong hóa đơn chi tiết

	public void delBillDeltais() {
		HDCTModel col = tableBillDeltais.getSelectionModel().getSelectedItem();

		HDCTModel model = new HDCTModel(col.getDonGia(), col.getSoLuong(), col.getTenSP(), col.getMasp(),
				col.getThanhTien());
		IHDCT.getInstance().del(model);

		loadDataBillDeltais();

		sumMoney.setText(
				AutoString.formatMoney(Float.valueOf(IHDCT.getInstance().selectPaid(IHDCT.getInstance().selectMaxID())))
						.toString());
	}

	// Cập nhật số lượng sản phẩm trong hóa dơn chi tiết
	public void updateDataBillDeltais() {
		HDCTModel col = tableBillDeltais.getSelectionModel().getSelectedItem();

		int soluong = Integer.parseInt(quantity.getText());

		HDCTModel model = new HDCTModel(col.getMaHDCT(), col.getDonGia(), soluong, IDBill.getText(), col.getMasp());
		IHDCT.getInstance().update(model);

		loadDataBillDeltais();
		sumMoney.setText(
				AutoString.formatMoney(Float.valueOf(IHDCT.getInstance().selectPaid(IDBill.getText()))).toString());

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
			Notification.alert(AlertType.INFORMATION, "Thanh Toán thiếu tiền !!!");
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
					"select NgayLapHD, SUM(thanhtoan) as thanhtoan, count(MaHD) as soluong from HOADON group by NgayLapHD");
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

	// Tìm kiếm dịch vụ
	public void findService() {
		if (tableSer.getItems().size() >= 1) {
			tableSer.getItems().clear();
		}
		String masp = txtFindSer.getText();

		if (masp.equals(""))
			loadService();

		ArrayList<DichVuModel> sp = IDichVu.getInstance().selectAllByIDName(masp, "%" + masp + "%");

		for (DichVuModel dichVuModel : sp) {

			service.add(dichVuModel);
		}

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

		if (dateFrom.getValue() == null) {
			Notification.alert(AlertType.WARNING, "Vui lòng chọn ngày");
			dateFrom.requestFocus();
			return;
		}

		LocalDate ldfrom = dateFrom.getValue();
		String from = ldfrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate ldto = dateTo.getValue();
		String to = ldto.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		if (DateSimple.asDate(ldto).compareTo(DateSimple.asDate(ldfrom)) < 0) {
			Notification.alert(AlertType.WARNING, "Ngày đến phải sao ngày : " + from);
			dateTo.requestFocus();
			return;
		}

		ArrayList<ThongKeModel> model = IThongKe.getInstance().selectFromTo(from.toString(), to.toString());

		if (model.size() == 0) {
			Notification.alert(AlertType.CONFIRMATION, "Không có hóa đơn nào trong khoảng thời gian trên");
			return;
		}

		for (ThongKeModel thongKeModel : model) {
			revenue2.add(thongKeModel);
		}

		tableRevenue2.setItems(revenue2);

		txtMax.setText(AutoString
				.formatMoney(Float.valueOf(IThongKe.getInstance().getMaxBill(from.toString(), to.toString()))));
		txtMin.setText(AutoString
				.formatMoney(Float.valueOf(IThongKe.getInstance().getMinBill(from.toString(), to.toString()))));
		txtAvg.setText(AutoString
				.formatMoney(Float.valueOf(IThongKe.getInstance().getAVGBill(from.toString(), to.toString()))));
	}

	public void sumFromTo() {
		try {

			LocalDate ldfrom = dateFrom.getValue();
			String from = ldfrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			LocalDate ldto = dateTo.getValue();
			String to = ldto.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			lblTongTien.setText(AutoString
					.formatMoney(Float.valueOf(IThongKe.getInstance().sumPaidFromTo(from.toString(), to.toString()))));
		} catch (Exception e) {
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
		if (tablePrint.getItems().size() > 0) {
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
		lblSum.setText(sumMoney.getText());
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
		String mahddv = AutoString.autoID("HDDV", "maHDDV", "HOADONDV");

		java.sql.Date ngayLap = java.sql.Date.valueOf(dateBillSer.getValue());
		String strMyDate = dateBillSer.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String tenkh = nameClientOfbillSer.getText();
		String tennv = nameStaffOfBillSer.getText();

		if (phoneOfBillSer.getText().equals("")) {
			Notification.alert(AlertType.ERROR, "Vui lòng nhập số điện thoại");
			phoneOfBillSer.requestFocus();
			return;
		}

		if (tenkh.equals("")) {
			Notification.alert(AlertType.ERROR, "Không có khách hàng có số điện thoại này");
			phoneOfBillSer.requestFocus();
			return;
		}

		Date date = new Date();
		String dateCurrent = DateSimple.asString(date);

		if (!strMyDate.equals(dateCurrent)) {
			Notification.alert(AlertType.ERROR, "Ngày lập phải là ngày hiện tại");
			return;
		}

		HDDVModel model = new HDDVModel(mahddv, ngayLap, 0, tenkh, tennv, "Chưa Thanh Toán");
		IHoaDonDV.getInstance().insert(model);

		loadDataBillSer();
	}

	public void delBillSer() {
		HDDVModel model = new HDDVModel(IDBillSer.getText(), null, 0, null, null, null);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Thông Báo");
		alert.setHeaderText("Vui lòng xác nhận");
		alert.setContentText("Bạn có muốn xóa hông ?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			IChiTietDV.getInstance().delBillSerByID(IDBillSer.getText());
			loadTableBilSerDeltais();

			IHoaDonDV.getInstance().del(model);
			loadDataBillSer();
		}
	}

	public void updateBillSer() { // Button thanh toán
		String mahddv = IDBillSer.getText();
		Float thanhtoan = IChiTietDV.getInstance().sumPriceSer(mahddv);
		
		if(txtTKD.getText().equals("")) {
			Notification.alert(AlertType.ERROR, "Vui Lòng nhập tiền của khách hàng");
			return;
		}
		
		float tongTien2 = IChiTietDV.getInstance().sumPriceSer(IDBillSer.getText());
		float tienKhach2 = Float.valueOf(txtTKD.getText());
		float tienThua2 = tienKhach2 - tongTien2;
		
		if (tienThua2 < 0) {
			Notification.alert(AlertType.ERROR, "Thanh toans thiếu tiền !!!");
			return;
		}
		

		HDDVModel model = new HDDVModel(mahddv, null, thanhtoan, null, null, "Đã Thanh Toán");
		IHoaDonDV.getInstance().update(model);

		Notification.alert(AlertType.INFORMATION, "Thanh Toán Thành Công");

		loadDataBillSer();
	}

	public void clickBillSer() {
		HDDVModel col = tableHDDV.getSelectionModel().getSelectedItem();
		IDBillSer.setText(col.getMaHDDV());

		txtTongTien.setText(
				AutoString.formatMoney(Float.valueOf(IChiTietDV.getInstance().sumPriceSer(IDBillSer.getText()))));
		dateBillSer.setValue(
				Instant.ofEpochMilli(col.getNgayLap().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
		loadTableBilSerDeltais();
	}

	// load table dich vu trong hoa don dich vu
	public void setCellTableSerOfBillSer() {
		serOfBillSer = FXCollections.observableArrayList();
		IDSerOfBilSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("maDV"));
		nameSerOfBillSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("tenDV"));
		nameProOfBillSercol.setCellValueFactory(new PropertyValueFactory<DichVuModel, String>("tenSp"));
		priceSerOfBillSerCol.setCellValueFactory(new PropertyValueFactory<DichVuModel, Float>("giaDV"));
	}

	public void loadDataTableSerOfBillSer() {
		if (tableSerOfBillSer.getItems().size() >= 1) {
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
		IDSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, String>("maDV"));
		namSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, String>("tenDV"));
		priceSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Float>("giaDV"));
		ngayThue.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Date>("ngayThue"));
		ngayTra.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Date>("ngayTraCT"));
		paidBillSerOfDeltaisCol.setCellValueFactory(new PropertyValueFactory<ChiTietDVModel, Float>("thanhTien"));
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
		try {

			if (dateBillSer.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")
					|| ngayTraDK.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")
					|| ngayTraCT.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")) {
				Notification.alert(AlertType.WARNING, "Nhập ngày Thuê ngày Trả");
				return;
			}

			boolean exsit = false;
			ArrayList<ChiTietDVModel> list = IChiTietDV.getInstance().selectAllByID(IDBillSer.getText());
			for (ChiTietDVModel chiTietDVModel : list) {
				if (chiTietDVModel.getTenDV().equals(tendv)) {
					exsit = true;
				}
			}

			java.sql.Date ngaythue = java.sql.Date.valueOf(dateBillSer.getValue());
			java.sql.Date ngaydk = java.sql.Date.valueOf(ngayTraDK.getValue());
			java.sql.Date ngayct = java.sql.Date.valueOf(ngayTraCT.getValue());

			// Kiểm tra ngày hiện tại
			String strNgayThue = dateBillSer.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			Date date = new Date();
			String strCurrentDate = DateSimple.asString(date);

			if (!strCurrentDate.equals(strNgayThue)) {
				Notification.alert(AlertType.WARNING, "Ngày Thuê phải là ngày hiện tại");
				return;
			}

			// Kiểm trả ngày trả

			if (ngayct.compareTo(DateSimple.convertDateSql(date)) < 0) {
				Notification.alert(AlertType.WARNING, "Ngày trả phải sau ngày hiện tại");
				return;
			}

			if (ngaydk.compareTo(ngayct) > 0) {
				Notification.alert(AlertType.WARNING, "Ngày trả dự kiến phải trước ngày trả chính thức");
				return;
			}

			if (exsit) {
				Notification.alert(AlertType.CONFIRMATION, "Dịch vụ này bạn đã thuê vui lòng cập nhật lại ngày");
				return;
			}

			ChiTietDVModel model = new ChiTietDVModel(macthddv, giadv, ngaythue, ngaydk, ngayct, tendv,
					IDBillSer.getText());
			IChiTietDV.getInstance().insert(model);

			loadTableBilSerDeltais();
		} catch (Exception e) {
			Notification.alert(AlertType.INFORMATION, "Vui lòng chọn ngày");
		}

		txtTongTien.setText(Float
				.valueOf(IChiTietDV.getInstance().selectPaid(IDBillSer.getText(),
						java.sql.Date.valueOf(ngayTraCT.getValue()), java.sql.Date.valueOf(dateBillSer.getValue())))
				.toString());

	}

	public void delBillSerDeltais() {
		ChiTietDVModel col = tableSerDeltais.getSelectionModel().getSelectedItem();
		ChiTietDVModel model = new ChiTietDVModel(null, 0, null, null, null, col.getTenDV(), null);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Thông Báo");
		alert.setHeaderText("Thông báo đến bạn");
		alert.setContentText("Bạn có muốn xóa hông");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.OK) {

			IChiTietDV.getInstance().del(model);

			Message msg = new Message();
			msg.start(stage);

			loadTableBilSerDeltais();

			txtTongTien.setText(Float
					.valueOf(IChiTietDV.getInstance().selectPaid(IDBillSer.getText(),
							java.sql.Date.valueOf(ngayTraCT.getValue()), java.sql.Date.valueOf(dateBillSer.getValue())))
					.toString());

		}
	}

	public void updateDateBillSerDeltais() {

		if (dateBillSer.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")
				|| ngayTraDK.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")
				|| ngayTraCT.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().equals("")) {
			return;
		}

		java.sql.Date ngaydk = java.sql.Date.valueOf(ngayTraDK.getValue());
		java.sql.Date ngayct = java.sql.Date.valueOf(ngayTraCT.getValue());

		ChiTietDVModel col = tableSerDeltais.getSelectionModel().getSelectedItem();
		ChiTietDVModel model = new ChiTietDVModel(null, 0, null, ngaydk, ngayct, col.getTenDV(), col.getMaDV());

		IChiTietDV.getInstance().update(model);

		loadTableBilSerDeltais();

		txtTongTien.setText(Float
				.valueOf(IChiTietDV.getInstance().selectPaid(IDBillSer.getText(),
						java.sql.Date.valueOf(ngayTraCT.getValue()), java.sql.Date.valueOf(dateBillSer.getValue())))
				.toString());

		Message msg = new Message();
		msg.start(stage);
	}

	public void findSerOfBillSer() {
		if (tableSerOfBillSer.getItems().size() >= 1) {
			tableSerOfBillSer.getItems().clear();
		}

		String madv = findSerOfBillSer.getText();

		if (madv.equals(""))
			loadTableBilSerDeltais();

		ArrayList<DichVuModel> list = IDichVu.getInstance().selectAllByIDName(madv, "%" + madv + "%");
		for (DichVuModel dichVuModel : list) {
			serOfBillSer.add(dichVuModel);
		}

		tableSerOfBillSer.setItems(serOfBillSer);
	}

	public static void excel(TableView<ThongKeModel> tableRevenue22, File file) {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("sheet1");
			XSSFRow row = null;
			Cell cell = null;
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH THỐNG KÊ DOANH THU");

			row = (XSSFRow) sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã sản phẩm");

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Mã hóa đơn");

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Số lượng");

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Giá bán");

			for (int i = 0; i < tableRevenue22.getColumns().size(); i++) {
				XSSFRow excelRow = (XSSFRow) sheet.createRow(i + 4);
				for (int j = 0; j < tableRevenue22.getColumns().size(); j++) {
					Cell excelCell = excelRow.createCell(j, CellType.STRING);
					Object value = tableRevenue22.getColumns().get(j).getCellData(i);
					excelCell.setCellValue(value != null ? value.toString() : "");
				}
			}

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			Notification.alert(AlertType.INFORMATION, "Xuất thành công");

		} catch (IOException e) {
			Notification.alert(AlertType.INFORMATION, "Xuất thất bại");
			e.printStackTrace();
		}
	}

	public void print() {

		File f = new File("E:\\thongKe.xlsx");
		excel(tableRevenue2, f);
	}

	public void setCellTableTrademark() {
		trademarkTable = FXCollections.observableArrayList();
		IDTrademarkCol.setCellValueFactory(new PropertyValueFactory<Trademark, String>("maTH"));
		nameTrademarkCol.setCellValueFactory(new PropertyValueFactory<Trademark, String>("tenTH"));
	}

	public void loadTableTrademark() {

		if (tableTrademark.getItems().size() >= 0) {
			tableTrademark.getItems().clear();
		}

		ArrayList<Trademark> list = IThuongHieu.getInstance().selectAll();
		for (Trademark tr : list) {
			trademarkTable.add(tr);
		}

		tableTrademark.setItems(trademarkTable);
	}

	public void insertTrademark() {
		if (IDTrademark.getText().equals("") || nameTrademark.getText().equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin Thương Hiệu");
			return;
		}

		Trademark model = new Trademark(IDTrademark.getText(), nameTrademark.getText());
		IThuongHieu.getInstance().insert(model);

		loadTableTrademark();

		Message msg = new Message();
		msg.start(stage);

	}

	public void updateTrademark() {

		if (IDTrademark.getText().equals("") || nameTrademark.getText().equals("")) {
			Notification.alert(AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin Thương Hiệu");
			return;
		}

		Trademark model = new Trademark(IDTrademark.getText(), nameTrademark.getText());
		IThuongHieu.getInstance().update(model);

		loadTableTrademark();

		Message msg = new Message();
		msg.start(stage);

	}

	public void cleanTrademark() {
		IDTrademark.setText("");
		nameTrademark.setText("");
	}

	public void findTrademark() {
		String idTrademark = txtFindTrademark.getText();

		if (tableTrademark.getItems().size() >= 0) {
			tableTrademark.getItems().clear();
		}

		if (idTrademark.equals("")) {
			loadTableTrademark();
		}

		ArrayList<Trademark> list = IThuongHieu.getInstance().selectTrademarkByID(idTrademark);
		for (Trademark tr : list) {
			trademarkTable.add(tr);
		}

		tableTrademark.setItems(trademarkTable);
	}

	public void setCellTableBillHis() {
		billHistory = FXCollections.observableArrayList();
		idBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, String>("maHD"));
		nameClientBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, String>("tenKH"));
		phoneClientBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, String>("sdt"));
		dateBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, Date>("ngayLap"));
		paidBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, Double>("thanhToan"));
	}

	public void loadBillHistory() {
		if (tableBillHis.getItems().size() > 0) {
			tableBillHis.getItems().clear();
		}

		ArrayList<LichSuModel> list = ILichSu.getInstance().selectAllBillByStory();
		for (LichSuModel lichSuModel : list) {
			billHistory.add(lichSuModel);
		}

		tableBillHis.setItems(billHistory);
	}

	public void setCellTableBillSerHis() {
		billSerHistory = FXCollections.observableArrayList();
		idBillSerHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, String>("maHDDV"));
		nameClientSerBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, String>("tenKH"));
		phoneClientSerBillHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, String>("sdt"));
		dateBillSerHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, Date>("ngayLap"));
		paidBillSerHisCol.setCellValueFactory(new PropertyValueFactory<LichSuModel, Double>("thanhToan"));
	}

	public void loadBillSerHistory() {
		if (tableBillSerHis.getItems().size() > 0) {
			tableBillSerHis.getItems().clear();
		}

		ArrayList<LichSuModel> list = ILichSu.getInstance().selectAllBillSerByStory();
		for (LichSuModel lichSuModel : list) {
			billSerHistory.add(lichSuModel);
		}

		tableBillSerHis.setItems(billSerHistory);
	}

	public void findAllBillHis() {
		String sdt = txtFindBillHis.getText();

		if (tableBillSerHis.getItems().size() > 0) {
			tableBillSerHis.getItems().clear();
		}

		if (tableBillHis.getItems().size() > 0) {
			tableBillHis.getItems().clear();
		}

		if (sdt.equals("")) {
			loadBillHistory();
			loadBillSerHistory();
		}

		ArrayList<LichSuModel> list1 = ILichSu.getInstance().fillBillHis(sdt);
		ArrayList<LichSuModel> list2 = ILichSu.getInstance().fillBillSerHis(sdt);

		if (list1.size() == 0 || list2.size() == 0) {
			Notification.alert(AlertType.CONFIRMATION, "Không có hóa đơn thuộc số điện thoại " + sdt);
			loadBillHistory();
			loadBillSerHistory();
			return;
		}

		for (LichSuModel lichSuModel : list1) {
			billHistory.add(lichSuModel);
		}
		tableBillHis.setItems(billHistory);

		for (LichSuModel lichSuMode2 : list2) {
			billSerHistory.add(lichSuMode2);
		}
		tableBillSerHis.setItems(billSerHistory);

	}

	public void loadCbbMonth() {
		String[] month = { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8",
				"Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" };
		for (String string : month) {
			cbbMonth.getItems().add(string);
		}
	}

	public void findWithCbbMonth() {
		if (tableBillSerHis.getItems().size() > 0) {
			tableBillSerHis.getItems().clear();
		}

		if (tableBillHis.getItems().size() > 0) {
			tableBillHis.getItems().clear();
		}
		String month = cbbMonth.getValue();
		int value;
		switch (month) {
		case "Tháng 1":
			value = 1;
			break;
		case "Tháng 2":
			value = 2;
			break;
		case "Tháng 3":
			value = 3;
			break;
		case "Tháng 4":
			value = 4;
			break;
		case "Tháng 5":
			value = 5;
			break;
		case "Tháng 6":
			value = 6;
			break;
		case "Tháng 7":
			value = 7;
			break;
		case "Tháng 8":
			value = 8;
			break;
		case "Tháng 9":
			value = 9;
			break;
		case "Tháng 10":
			value = 10;
			break;
		case "Tháng 11":
			value = 11;
			break;
		default:
			value = 12;
			break;
		}
		ArrayList<LichSuModel> listBill = ILichSu.getInstance().findBillHisForMonths(value);
		ArrayList<LichSuModel> listBillSer = ILichSu.getInstance().findBillSerHisForMonths(value);

		if (listBill.size() == 0 || listBillSer.size() == 0) {
			Notification.alert(AlertType.CONFIRMATION, "Không có hóa đơn trong tháng " + value);
			loadBillHistory();
			loadBillSerHistory();
			return;
		}

		for (LichSuModel lichsuModel : listBill) {
			billHistory.add(lichsuModel);
		}
		tableBillHis.setItems(billHistory);

		for (LichSuModel lichSuMode2 : listBillSer) {
			billSerHistory.add(lichSuMode2);
		}
		tableBillSerHis.setItems(billSerHistory);

	}

	public void setCellTableLock() {
		lock = FXCollections.observableArrayList();
		loadDataTableLock();
		nameLockCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("tenNV"));
		idLockCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("maNV"));
		roleLockCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("chucVu"));
	}

	public void loadDataTableLock() {
		if (tableLock.getItems().size() > 0) {
			tableLock.getItems().clear();
		}

		ArrayList<NhanVienModel> list = INhanVien.getInstance().listLocked();
		for (NhanVienModel n : list) {
			lock.add(new NhanVienModel(n.getTenNV(), n.getMaNV(), n.getChucVu()));

		}

		tableLock.setItems(lock);
	}

	public void updateStatusStaff(MouseEvent e) {
		if (e.getClickCount() == 2) {
			NhanVienModel model = tableLock.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Thông Báo");
			alert.setHeaderText("Thông báo đến bạn");
			alert.setContentText("Bạn có muốn mở khóa tài khoản của: " + model.getTenNV());
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) {
				try {
					INhanVien.getInstance().openLock(model.getMaNV());

					loadDataTableLock();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void FilePrintClicked() {

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		try {
			if (job.printDialog()) {
				job.print();
			}
		} catch (PrinterException e) {
			e.printStackTrace();

		}
	}

	private model.HoaDonModel hoaDon;
	IKhachHang khDAO = new IKhachHang();
	private List<model.HDCTModel> chiTietList;

	@Override
	public int print(Graphics g, PageFormat format, int pagenum) throws PrinterException {
		if (pagenum > 0) {
			return Printable.NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(format.getImageableX(), format.getImageableY());

		Font fontHeader = new Font("Arial", Font.BOLD, 16);
		Font fontNormal = new Font("Arial", Font.PLAIN, 12);

		int x = 50; // Điểm bắt đầu vẽ theo trục x
		int y = 50; // Điểm bắt đầu vẽ theo trục y

		// Vẽ thông tin cửa hàng
		g2d.setFont(fontHeader);
		x += 150;
		y += 20;
		g2d.drawString("THE STUDIO BREAKFAST", x, y);

		g2d.setFont(fontHeader);
		x += -50;
		y += 20;
		g2d.drawString("ĐC: Toà nhà FPT Polytechnic, Cần Thơ", x, y);
		// x+=20;
		// y += 20;
		// Sắp xếp lại các phần tử trong hóa đơn
		x = 50;
		y += 20;

		// Vẽ thông tin hóa đơn
		Date dd = new Date();
		SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");
		String time = dateTime.format(dd);
		g2d.setFont(fontNormal);
		g2d.drawString("Tên nhân viên: " + nameStaffOfBill.getText(), x, y);
		// x += 100;
		y += 20;
		g2d.drawString("Mã Hoá Đơn: " + IDBill.getText(), x, y);
		// x += 100;
		y += 20;
		g2d.drawString("Ngày Bán: " + dateBill.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
				x, y);
		// x += 100;
		y += 20;
		g2d.drawString("Thời Gian: " + time, x, y);
		// x += 100;
		y += 20;
		g2d.drawString("Tên Khách Hàng: " + nameClientOfBill.getText(), x, y);
		// x += 10;
		y += 20;

		// Vẽ bảng sản phẩm
		x = 50;
		y += 10;
		g2d.drawLine(x, y, x + 500, y); // Đường ngang đầu bảng

		y += 20;

		x += 30;
		g2d.drawString("Tên sản phẩm", x, y);
		x += 150;
		g2d.drawString("Giá bán", x, y);
		x += 100;
		g2d.drawString("Số lượng", x, y);
		x += 130;
		g2d.drawString("Thành tiền", x, y);

		y += 20;
		g2d.drawLine(x - 410, y, x + 90, y); // Đường ngang phía dưới tiêu đề cột
		// Vẽ sản phẩm
		ArrayList<HDCTModel> chiTietList = IHDCT.getInstance().selectAllByIDBill(lblIDBill.getText());
		for (HDCTModel chiTiet : chiTietList) {
			// model.SanPham sp =
			// spDAO.selectById(ctspDAO.selectById(chiTiet.getMaCTSP()).getMaSP() + "");
			x = 50;
			y += 20;
			x += 30;
			g2d.drawString(chiTiet.getTenSP(), x, y); // tên sản phẩm
			x += 150;
			g2d.drawString(Float.toString(chiTiet.getDonGia()), x, y); // giá bán
			x += 100;
			g2d.drawString(Integer.toString(chiTiet.getSoLuong()), x, y); // so luong
			x += 130;
			g2d.drawString(Float.toString(chiTiet.getDonGia() * chiTiet.getSoLuong()), x, y); // Thành tiền

		}
		// Vẽ tổng số tiền
		float tongTien = IHDCT.getInstance().selectPaid(IDBill.getText());
		float tienKhach = Float.parseFloat(clientMoney.getText());
		float tienThua = tienKhach - tongTien;
		x = 50;
		y += 30;
		g2d.drawLine(x, y, x + 500, y); // Đường ngang cuối bảng

		y += 30;
		Font fontTongTien = new Font("Arial", Font.BOLD, 14); // Chọn kích thước font mới
		g2d.setFont(fontTongTien);
		g2d.drawString("Tổng Tiền: "
				+ (Float.valueOf(IHDCT.getInstance().selectPaid(IHDCT.getInstance().selectMaxID())).toString())
				+ " VNĐ", x, y);
		y += 20;

		g2d.drawString("Tiền khách đưa: " + Float.valueOf(clientMoney.getText().toString()) + " VNĐ", x, y);
		y += 20;

		g2d.drawString("Tiền thối lại: " + tienThua + " VNĐ", x, y);

		changeMoney.setText(Float.valueOf(tienThua).toString());
		// Vẽ chân trang
		x += 100;
		y += 30;
		g2d.drawString("Chúc quý khách vui vẻ và hẹn gặp lại.", x, y);
		return PAGE_EXISTS;

	}
	
	

}
