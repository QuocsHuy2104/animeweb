package IDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import javafx.scene.control.Alert.AlertType;
import model.LichSuModel;
import utilities.Notification;

public class ILichSu implements DAOInterface<LichSuModel> {

	public static ILichSu getInstance() {
		return new ILichSu();
	}

	@Override
	public int insert(LichSuModel reneric) {
		return 0;
	}

	@Override
	public int del(LichSuModel reneric) {
		return 0;
	}

	@Override
	public int update(LichSuModel reneric) {
		return 0;
	}

	@Override
	public LichSuModel selectByID(LichSuModel generic) {
		return null;
	}

	@Override
	public ArrayList<LichSuModel> selectAll() {
		return null;
	}

	public ArrayList<LichSuModel> selectAllBillByStory() {
		ArrayList<LichSuModel> result = new ArrayList<LichSuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("select MaHD, NgayLapHD, ThanhToan, TenKH, sdt from HOADON\r\n"
							+ "inner join KHACHHANG on HOADON.MaKH = KHACHHANG.MaKH\r\n"
							+ "where hoadon.TrangThai = 1");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString(1);
				Date ngay = rs.getDate(2);
				Double thanhToan = (double) rs.getFloat(3);
				String tenkh = rs.getString(4);
				String sdt = rs.getString("sdt");

				LichSuModel model = new LichSuModel(mahd, sdt, ngay, thanhToan, tenkh);
				result.add(model);
			}

			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(conn);
		return result;
	}

	public ArrayList<LichSuModel> selectAllBillSerByStory() {
		ArrayList<LichSuModel> result = new ArrayList<LichSuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("select MaHDDV, NgayLapHDDV, ThanhToan, TenKH, sdt from HOADONDV\r\n"
							+ "inner join KHACHHANG on HOADONDV.MaKH = KHACHHANG.MaKH\r\n"
							+ "where hoadondv.TrangThai = 1");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String mahddv = rs.getString("MaHDDV");
				Date ngay = rs.getDate("NgayLapHDDV");
				Double thanhToan = (double) rs.getFloat("ThanhToan");
				String tenkh = rs.getString("TenKH");
				String sdt = rs.getString("sdt");

				LichSuModel model = new LichSuModel(mahddv, tenkh, sdt, ngay, thanhToan);
				result.add(model);
			}

			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(conn);
		return result;
	}

	public ArrayList<LichSuModel> fillBillSerHis(String number) {
		ArrayList<LichSuModel> result = new ArrayList<LichSuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("select MaHDDV, NgayLapHDDV, ThanhToan, TenKH, sdt from HOADONDV\r\n"
							+ "inner join KHACHHANG on HOADONDV.MaKH = KHACHHANG.MaKH\r\n"
							+ "where hoadondv.TrangThai = 1 and sdt like ?");
			pst.setString(1, number);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String mahddv = rs.getString("MaHDDV");
				Date ngay = rs.getDate("NgayLapHDDV");
				Double thanhToan = (double) rs.getFloat("ThanhToan");
				String tenkh = rs.getString("TenKH");
				String sdt = rs.getString("sdt");

				LichSuModel model = new LichSuModel(mahddv, tenkh, sdt, ngay, thanhToan);
				result.add(model);
			}

			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(conn);
		return result;
	}
	
	public ArrayList<LichSuModel> fillBillHis(String number) {
		ArrayList<LichSuModel> result = new ArrayList<LichSuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("select MaHD, NgayLapHD, ThanhToan, TenKH, sdt from HOADON\r\n"
							+ "inner join KHACHHANG on HOADON.MaKH = KHACHHANG.MaKH\r\n"
							+ "where hoadon.TrangThai = 1 and sdt like ?");
			pst.setString(1, number);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString(1);
				Date ngay = rs.getDate(2);
				Double thanhToan = (double) rs.getFloat(3);
				String tenkh = rs.getString(4);
				String sdt = rs.getString("sdt");

				LichSuModel model = new LichSuModel(mahd, sdt, ngay, thanhToan, tenkh);
				result.add(model);
			}

			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(conn);
		return result;
	}
	
	public ArrayList<LichSuModel> findBillHisForMonths(int months) {
		ArrayList<LichSuModel> result = new ArrayList<LichSuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select MaHD, NgayLapHD, ThanhToan, TenKH, sdt from HOADON\r\n"
					+ "inner join KHACHHANG on HOADON.MaKH = KHACHHANG.MaKH\r\n"
					+ "where hoadon.TrangThai = 1 and MONTH(HoaDon.NgayLapHD) like ?");
			ps.setInt(1, months);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString(1);
				Date ngay = rs.getDate(2);
				Double thanhToan = (double) rs.getFloat(3);
				String tenKH = rs.getString(4);
				String sdt = rs.getString(5);
				
				result.add(new LichSuModel(mahd, sdt, ngay, thanhToan, tenKH));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<LichSuModel> findBillSerHisForMonths(int months) {
		ArrayList<LichSuModel> result = new ArrayList<LichSuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select MaHDDV, NgayLapHDDV, ThanhToan, TenKH, sdt from HOADONDV\r\n"
					+ "inner join KHACHHANG on HOADONDV.MaKH = KHACHHANG.MaKH\r\n"
					+ "where hoadonDV.TrangThai = 1 and MONTH(HOADONDV.NgayLapHDDV) like ?");
			ps.setInt(1, months);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String mahddv = rs.getString(1);
				Date ngay = rs.getDate(2);
				Double thanhToan = (double) rs.getFloat(3);
				String tenKH = rs.getString(4);
				String sdt = rs.getString(5);
				
				result.add(new LichSuModel(mahddv, tenKH, sdt, ngay, thanhToan));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
