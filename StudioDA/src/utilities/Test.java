package utilities;

import java.lang.reflect.Array;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.mail.MessagingException;

import IDAO.INhanVien;
import application.ErrorForm;
import application.HomeController;
import connectJDBC.JDBCUtil;
import javafx.stage.Stage;
import model.NhanVienModel;

public class Test {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String input = sc.nextLine();
//		int max = 100;
//		System.out.println(AutoString.autoID(input, max));
//	
//		System.out.println(input +(String.format("%04d", max)));
		
//		System.out.println(input.replaceAll(" ", ""));
//		String[] output = input.split(" ");
//		System.out.println(output[2]);
//		
//		String kho = "";
//		for (int i = 0; i < output.length - 1; i++) {
//			
//			kho += output[i].substring(0, 1);
//			
//		}
//		System.out.println(kho);
		
		
		//System.out.println(AutoString.autoEmail("Nguyen Minh Trieu"));

//		int code = (int) Math.floor(((Math.random() * 899999) + 100000));
//		System.out.println(code);
		
//		Email email = new Email();
//		System.out.println(email.messageMail);
//		System.out.println(email.messageMail);
		
//		Connection conn = JDBCUtil.getConnection();
//		int id = 0;
//		try {
//			PreparedStatement pst = conn.prepareStatement("SELECT Max(MaKH) as LastID FROM KHACHHANG");
//			ResultSet rs = pst.executeQuery();
//			String makh = "";
//			while(rs.next()) {
//				makh += rs.getString(1);
//			}
//			
//			System.out.println(makh);
//			
//			String[] split = makh.split("000");
//			for (int i = 1; i < split.length; i++) {
//				id = Integer.parseInt(split[i]);
//				
//			}
//			pst.close();
//			rs.close();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(String.format("%04d", id + 1));
		
//		NhanVienModel model = new NhanVienModel("KyDV", null, null, null, null, null, false, false);
//		INhanVien.getInstance().selectByID(model);
		
//		DecimalFormat d = new DecimalFormat("###,##0.000");
//		System.out.println(d.format(scanner.nextDouble()));
		
		
	}

}
