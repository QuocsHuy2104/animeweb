package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import connectJDBC.JDBCUtil;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.NhanVienModel;

public class HomeController implements Initializable {

	@FXML
	private ImageView ImageMenu, imageSignout, ImageMenuBar, imageHeart;

	@FXML
	private Pane MenuPane, ContentPane, paneSetting, paneStaff, paneHome, paneClient, paneProduct, paneRevenue,
			paneBill;

	@FXML
	private Rectangle menuBar, recHome, recStaff, recService, recProduct, recRevenue, recBill;

	@FXML
	private Label labelHome, labelStaff, labelProduct, labelRenvenue, labelService, labelSignOut, labelBill, title;

	@FXML
	private Line lineMenu;

	@FXML
	private PieChart pieChart;

	@FXML
	private AnchorPane root;

	@FXML
	private Button btnChangePass, btnAddStaff, btnEditStaff, btnDellStaff, addStaff, updateStaff, removeStaff, btnAbout;

	@FXML
	private ComboBox<String> cbbBackground;

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

	@FXML
	private LineChart myLineChart;

	Connection conn = null;

	private ObservableList<NhanVienModel> staff;

	Stage stage;
	Scene scene;
	Parent root1;

	// Code Handle event from

	public void zoomOut() {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 176; i >= 110; i--) {
					menuBar.setWidth(i);
					hiddenMenu();
					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void zoomIn() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 110; i <= 176; i++) {
					menuBar.setWidth(i);
					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				showMenu();
			}
		}).start();
	}

	public void showMenu() {
		recHome.setVisible(true);
		recStaff.setVisible(true);
		recService.setVisible(true);
		recProduct.setVisible(true);
		recRevenue.setVisible(true);
		recBill.setVisible(true);

		labelHome.setVisible(true);
		labelStaff.setVisible(true);
		labelProduct.setVisible(true);
		labelRenvenue.setVisible(true);
		labelService.setVisible(true);
		labelSignOut.setVisible(true);
		labelBill.setVisible(true);

		ImageMenu.setVisible(true);
		lineMenu.setVisible(true);
		imageSignout.setLayoutX(128);
		ImageMenuBar.setVisible(false);

	}

	public void hiddenMenu() {
		recHome.setVisible(false);
		recStaff.setVisible(false);
		recService.setVisible(false);
		recProduct.setVisible(false);
		recRevenue.setVisible(false);
		recBill.setVisible(false);

		labelHome.setVisible(false);
		labelStaff.setVisible(false);
		labelProduct.setVisible(false);
		labelRenvenue.setVisible(false);
		labelService.setVisible(false);
		labelSignOut.setVisible(false);
		labelBill.setVisible(false);

		ImageMenu.setVisible(false);
		lineMenu.setVisible(false);
		imageSignout.setLayoutX(50);
		ImageMenuBar.setVisible(true);

	}

	public void convertScene() {

	}

	public void convertHeart() {
		Image img = new Image("C:/Users/HP/workspage-udpm/StudioDA/src/image/changeheart.png");
		imageHeart.setImage(img);
	}

	public void setting() {
		paneSetting.setVisible(true);

	}

	public void closeSetting() {
		paneSetting.setVisible(false);
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
		title.setText("Pages / Trang chủ");
	}

	public void openStaff() {
		paneStaff.setVisible(true);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(false);
		paneBill.setVisible(false);
		title.setText("Pages / Nhân viên");
	}

	public void openProduct() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(true);
		paneRevenue.setVisible(false);
		paneBill.setVisible(false);
		title.setText("Pages / Sản phẩm");
	}

	public void openBill() {
		paneBill.setVisible(true);
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(false);
		title.setText("Pages / Hóa Đơn");
	}

	public void openRevenue() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(false);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(true);
		title.setText("Pages / Thống kê");
	}

	public void openClient() {
		paneStaff.setVisible(false);
		paneHome.setVisible(false);
		paneClient.setVisible(true);
		paneProduct.setVisible(false);
		paneRevenue.setVisible(false);
		title.setText("Pages / Khách hàng");
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
			Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = new Stage();
			Scene scene = new Scene(login);
			stage.setScene(scene);
			stage.show();
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

		cbbBackground.getItems().setAll("Default", "White smoke");

		conn = JDBCUtil.getConnection();

		staff = FXCollections.observableArrayList();
		setCellTable();
		loadDataStaff();
	}

	// set column table Staff ('NHÂN VIÊN')
	public void setCellTable() {
		idCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("maNV"));
		nameCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("tenNV"));
		addressCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("diaChi"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, String>("soDT"));
		wageCol.setCellValueFactory(new PropertyValueFactory<NhanVienModel, Float>("luong"));
	}
	
	// load data from database enter table in screen

	public void loadDataStaff() {
		try {
			PreparedStatement pst = conn.prepareStatement("Select * from nhanvien");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				staff.add(new NhanVienModel(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("DIACHI"),
						rs.getString("SDT"), rs.getFloat("Luong"), rs.getDate("NGAYNHAN"), rs.getString("pass"),
						rs.getBoolean("vaitro")));
			}
			tableStaff.setItems(staff);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(conn);
	}

	// set PieChart
	
	public void setPieChart() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Satff", 10),
				new PieChart.Data("Service", 23), new PieChart.Data("Product", 100));

		pieChartData.forEach(
				data -> data.nameProperty().bind(Bindings.concat(data.getName(), " amount ", data.pieValueProperty())));

		pieChart.getData().addAll(pieChartData);
	}
	
	// load data pieChart

	public void loadPieChar() {
	}
}
