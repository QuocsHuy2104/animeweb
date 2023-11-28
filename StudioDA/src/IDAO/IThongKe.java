package IDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectJDBC.JDBCUtil;
import model.ThongKeModel;

public class IThongKe {

	public static IThongKe getInstance() {
		return new IThongKe();
	}

	// selec từ ngày này đến ngày kia
	public ArrayList<ThongKeModel> selectFromTo(String from, String to) {
		ArrayList<Object> result = new ArrayList<Object>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT A.MaHD, MaSP, SoLuong, DonGia,NgayLapHD FROM HOADON A \r\n"
							+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
							+ "WHERE TrangThai = 1 AND NgayLapHD >= ? AND NgayLapHD <= ?");
			pst.setString(1, from);
			pst.setString(2, to);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String masp = rs.getString(1);
				String mahd = rs.getString(2);
				int soluong = rs.getInt(3);
				float giaBan = rs.getFloat(4);

				ThongKeModel thongKeModel = new ThongKeModel(masp, mahd, soluong, giaBan);
				result.add(thongKeModel);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// select tháng năm
	public ArrayList<ThongKeModel> selectMonth(String month, String date) {
		ArrayList<Object> result = new ArrayList<Object>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT A.MaHD, MaSP, SoLuong, ThanhToan FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE MONTH(NgayLapHD) = ? AND YEAR(NgayLapHD) = ? AND TrangThai = 1");
			pst.setString(1, month);
			pst.setString(2, date);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String masp = rs.getString(1);
				String mahd = rs.getString(2);
				int soluong = rs.getInt(3);
				float giaBan = rs.getFloat(4);

				ThongKeModel thongKeModel = new ThongKeModel(masp, mahd, soluong, giaBan);
				result.add(thongKeModel);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// select theo năm

	public ArrayList<ThongKeModel> selectYear(String date) {
		ArrayList<Object> result = new ArrayList<Object>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT A.MaHD, MaSP, SoLuong, ThanhToan FROM HOADON A \r\n"
							+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
							+ "WHERE YEAR(NgayLapHD) = ? AND TrangThai = 1");
			pst.setString(1, date);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String masp = rs.getString(1);
				String mahd = rs.getString(2);
				int soluong = rs.getInt(3);
				float giaBan = rs.getFloat(4);

				ThongKeModel thongKeModel = new ThongKeModel(masp, mahd, soluong, giaBan);
				result.add(thongKeModel);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
