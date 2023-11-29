package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.aspose.pdf.Document;
import com.aspose.pdf.FontRepository;
import com.aspose.pdf.Page;
import com.aspose.pdf.Position;
import com.aspose.pdf.TextBuilder;
import com.aspose.pdf.TextFragment;

import IDAO.IHDCT;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.HDCTModel;

public class ExportPDF extends Application implements Initializable {
	
	@FXML
	private Label lblIDBill, lblnameStaff, lblDateBill, lblnameClient, lblphoneClient, lblSum, lblTK, lblTL;
	
	@FXML
	private TableView<HDCTModel> tabePrint;
	
	@FXML
	private TableColumn<HDCTModel, String> id;
	
	public void exportPDF() {
		
		String mahd = HomeController.mahoadon;
		String ngaylap = HomeController.ngaylap;
		String tenkh = HomeController.tenkhachhang;
		String sdt = HomeController.sodienthoai;
		
		//ArrayList<HDCTModel> md = IHDCT.getInstance().selectAllByIDBill(mahd);
		
		
		Document doc = new Document();
		
		Page page = doc.getPages().add();
		
		TextFragment header = new TextFragment("THE STUDIO BREAKFAST");
		header.getTextState().setFontSize(14);
		header.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		header.setPosition(new Position(10, 800));
		
		TextFragment address = new TextFragment("Địa chỉ: FPT Polytechnic Casi Răng Cần Thơ");
		address.getTextState().setFontSize(8);
		address.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		address.setPosition(new Position(10, 790));
		
		TextFragment title = new TextFragment("Hóa Đơn Bán Hàng");
		title.getTextState().setFontSize(14);
		title.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		title.setPosition(new Position(10, 782));
		
		TextFragment IDBill = new TextFragment("Mã Hóa Đơn: \t" + mahd);
		IDBill.getTextState().setFontSize(8);
		IDBill.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		IDBill.setPosition(new Position(10, 770));
		
		TextFragment dateCreate = new TextFragment("Ngày Lập Hóa Đơn: \t" + ngaylap);
		dateCreate.getTextState().setFontSize(8);
		dateCreate.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		dateCreate.setPosition(new Position(10, 760));
		
		TextFragment staff = new TextFragment("Nhân Viên: \t" + LoginController.nameStaff);
		staff.getTextState().setFontSize(8);
		staff.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		staff.setPosition(new Position(10, 750));
		
		TextFragment client = new TextFragment("Tên Khách Hàng: \t" + tenkh);
		client.getTextState().setFontSize(8);
		client.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		client.setPosition(new Position(10, 740));
		
		TextFragment phone = new TextFragment("Số điện thoại: \t" + sdt);
		phone.getTextState().setFontSize(8);
		phone.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
		phone.setPosition(new Position(10, 730));
		
		TextBuilder builder = new TextBuilder(page);
		
		builder.appendText(header);
		builder.appendText(address);
		builder.appendText(title);
		builder.appendText(IDBill);
		builder.appendText(dateCreate);
		//builder.appendText(staff);
		builder.appendText(client);
		builder.appendText(phone);
		
		
		doc.save("Bill2.pdf");
	}
	
	public static void main(String[] args) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
