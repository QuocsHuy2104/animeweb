package IDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.HDDVModel;

public class IHoaDonDV implements DAOInterface<HDDVModel> {
	
	public static IHoaDonDV getInstance() {
		return new IHoaDonDV();
	}

	@Override
	public int insert(HDDVModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO HOADONDV VALUES (?, ?, ?, (select makh from khachhang where tenkh = ?), (select manv from nhanvien where tennv = ?), ?)");
			ps.setString(1, reneric.getMaHDDV());
			ps.setDate(2, reneric.getNgayLap());
			ps.setFloat(3, reneric.getThanhToan());
			ps.setString(4, reneric.getTenKhachHang());
			ps.setString(5, reneric.getTenNhanVien());
			int trangthai = reneric.getTrangThai() == "Đã Thanh Toán" ? 1 : 0;
			ps.setInt(6, trangthai);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(HDDVModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from HOADONDV where MaHDDV = ?");
			pst.setString(1, reneric.getMaHDDV());
			
			result = pst.executeUpdate();
			
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(HDDVModel reneric) {
		int result = 0;
        Connection conn = JDBCUtil.getConnection();
        try {
			PreparedStatement pst = conn.prepareStatement("update HOADONDV set thanhtoan = ?, trangthai = ? where MaHDDV = ? and trangthai = 0");
			pst.setFloat(1, reneric.getThanhToan());
			pst.setInt(2, reneric.getTrangThai() == "Đã Thanh Toán" ? 1 : 0);
			pst.setString(3, reneric.getMaHDDV());
			
			result = pst.executeUpdate();
			
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public HDDVModel selectByID(HDDVModel generic) {
		return null;
	}

	@Override
	public ArrayList<HDDVModel> selectAll() {
		ArrayList<HDDVModel> result = new ArrayList<HDDVModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select MaHDDV, NgayLapHDDV, THANHTOAN, TenKH, TenNV ,HOADONDV.TrangThai from HOADONDV\r\n"
					+ "inner join NHANVIEN on HOADONDV.MaNV = NHANVIEN.MaNV\r\n"
					+ "inner join KHACHHANG on HOADONDV.MaKH = KHACHHANG.MaKH");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String mahddv = rs.getString(1);
				Date ngayLap = rs.getDate(2);
				float thanhToan = rs.getFloat(3);
				String tenKhachHang = rs.getString(4);
				String tenNhanVien = rs.getString(5);
				int trangThai = rs.getInt(6);
				String tt = trangThai == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
				
				HDDVModel model = new HDDVModel(mahddv, ngayLap, thanhToan, tenKhachHang, tenNhanVien, tt);
				result.add(model);
			}
			
			ps.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String selectMaxID() {

		String result = "";

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select max(MaHDDV) from HOADONDV");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
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
