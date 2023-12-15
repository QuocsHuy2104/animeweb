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
		ArrayList<ThongKeModel> result = new ArrayList<ThongKeModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT A.MaHD, MaSP, SoLuong, DonGia FROM HOADON A\r\n"
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
		return result;

	}

	// select tháng năm
	public ArrayList<ThongKeModel> selectMonth(String month, String date) {
		ArrayList<ThongKeModel> result = new ArrayList<ThongKeModel>();
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
		return result;
	}

	// select theo năm

	public ArrayList<ThongKeModel> selectYear(String date) {
		ArrayList<ThongKeModel> result = new ArrayList<ThongKeModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT A.MaHD, MaSP, SoLuong, ThanhToan FROM HOADON A \r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n" + "WHERE YEAR(NgayLapHD) = ? AND TrangThai = 1");
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
		return result;
	}

	public float sumPaidFromTo(String from, String to) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"SELECT Sum(DonGia) FROM HOADON A \r\n" + "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
							+ "WHERE TrangThai = 1 AND NgayLapHD >= ? AND NgayLapHD <= ?");

			pst.setString(1, from);
			pst.setString(2, to);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public float sumPaidMonth(String month, String date) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"SELECT sum(dongia) FROM HOADON A\r\n" + "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
							+ "WHERE MONTH(NgayLapHD) = ? AND YEAR(NgayLapHD) = ? AND TrangThai = 1");

			pst.setString(1, month);
			pst.setString(2, date);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public float sumPaidYear(String date) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT sum(dongia) FROM HOADON A \r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n" + "WHERE YEAR(NgayLapHD) = ? AND TrangThai = 1");

			pst.setString(1, date);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public float getMaxBill(String from, String to) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TOP 1 WITH TIES DonGia FROM HOADON A \r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE TrangThai = 1 AND NgayLapHD >= ? AND NgayLapHD <= ? ORDER BY DonGia DESC ");
			pst.setString(1, from);
			pst.setString(2, to);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getMinBill(String from, String to) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TOP 1 WITH TIES DonGia FROM HOADON A \r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE TrangThai = 1 AND NgayLapHD >= ? AND NgayLapHD <= ? ORDER BY DonGia ASC ");
			pst.setString(1, from);
			pst.setString(2, to);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getAVGBill(String from, String to) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT AVG(DonGia) FROM HOADON A \r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE TrangThai = 1 AND NgayLapHD >= ? AND NgayLapHD <= ?");
			pst.setString(1, from);
			pst.setString(2, to);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getMaxMonthBill(String month, String year) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TOP 1 DonGia FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE MONTH(NgayLapHD) = ? AND YEAR(NgayLapHD) = ? AND TrangThai = 1 ");
			pst.setString(1, month);
			pst.setString(2, year);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getMinMonthBill(String month, String year) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TOP 1 WITH TIES DonGia FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE MONTH(NgayLapHD) = ? AND YEAR(NgayLapHD) = ? AND TrangThai = 1 order by DonGia ASC");
			pst.setString(1, month);
			pst.setString(2, year);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getAVGMonthBill(String month, String year) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT AVG(DonGia) FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE MONTH(NgayLapHD) = ? AND YEAR(NgayLapHD) = ? AND TrangThai = 1");
			pst.setString(1, month);
			pst.setString(2, year);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getMinYearBill( String year) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TOP 1 WITH TIES DonGia FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE YEAR(NgayLapHD) = ? AND TrangThai = 1 ORDER BY DonGia ASC");
			
			pst.setString(1, year);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getMaxYearBill( String year) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT TOP 1 DonGia FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE YEAR(NgayLapHD) = ? AND TrangThai = 1");
			
			pst.setString(1, year);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float getAVGYearBill( String year) {
		float result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT AVG(DONGIA) FROM HOADON A\r\n"
					+ "INNER JOIN HDCT B ON A.MaHD = B.MaHD\r\n"
					+ "WHERE YEAR(NgayLapHD) = ? AND TrangThai = 1");
			
			pst.setString(1, year);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}

