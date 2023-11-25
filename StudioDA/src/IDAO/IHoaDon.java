package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.HoaDonModel;

public class IHoaDon implements DAOInterface<HoaDonModel> {

	public static IHoaDon getInstance() {
		return new IHoaDon();
	}

	@Override
	public int insert(HoaDonModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();

		String sql = "insert into hoadon values (?, ?, ?, ?, (select makh from khachhang where tenkh like ?), (select manv from nhanvien where tennv like ?))";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, reneric.getMahd());
			pst.setDate(2, reneric.getNgay());
			pst.setFloat(3, reneric.getThanhToan());
			int story = reneric.getTrangThai().equals("Thanh Toán") ? 1 : 0;
			pst.setInt(4, story);
			pst.setString(5, reneric.getTenKH());
			pst.setString(6, reneric.getTenNV());

			result = pst.executeUpdate();

			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int del(HoaDonModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("delete from hoadon where mahd = ?");
			pst.setString(1, reneric.getMahd());

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(HoaDonModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "update hoadon set ngay = ?, thanhtoan = ?, makh = (select makh from khachhang where tenkh = ?), manv = (select manv from nhanvien where tennv = ?) where mahd = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setDate(1, reneric.getNgay());
			pst.setFloat(2, reneric.getThanhToan());
			pst.setString(3, reneric.getTenKH());
			pst.setString(4, reneric.getTenNV());
			pst.setString(5, reneric.getMahd());

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public HoaDonModel selectByID(HoaDonModel generic) {
		HoaDonModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"select mahd, ngaylaphd, thanhtoan, HOADON.TrangThai, tenkh, tennv from HoaDon\r\n"
							+ "inner join nhanvien on hoadon.MaNV = NHANVIEN.MaNV\r\n"
							+ "inner join KHACHHANG on HOADON.MaKH = KHACHHANG.MaKH\r\n" + "where MaHD like = ?");
			pst.setString(1, generic.getMahd());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("mahd");
				Date ngay = rs.getDate("ngaylaphd");
				float thanhtoan = rs.getFloat("thanhtoan");
				int trangthai = rs.getInt("trangthai");
				String story;
				if (trangthai == 1)
					story = "Thanh Toán";
				else
					story = "Chưa Thanh Toán";
				String tenkhach = rs.getString("tenkh");
				String tennv = rs.getString("tenNV");
				result = new HoaDonModel(ma, (java.sql.Date) ngay, thanhtoan, tenkhach, tennv, story);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<HoaDonModel> selectAll() {
		ArrayList<HoaDonModel> reuslt = new ArrayList<HoaDonModel>();
		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement(
					"select mahd, ngaylaphd, thanhtoan, HOADON.TrangThai, tenkh, tennv from HoaDon\r\n"
							+ "inner join nhanvien on hoadon.MaNV = NHANVIEN.MaNV\r\n"
							+ "inner join KHACHHANG on HOADON.MaKH = KHACHHANG.MaKH\r\n");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("mahd");
				Date ngay = rs.getDate("ngaylaphd");
				float thanhtoan = rs.getFloat("thanhtoan");
				int trangthai = rs.getInt("trangthai");
				String story;
				if (trangthai == 1)
					story = "Thanh Toán";
				else
					story = "Chưa Thanh Toán";
				
				String tenkhach = rs.getString("tenkh");
				String tennv = rs.getString("tenNV");

				HoaDonModel model = new HoaDonModel(ma, (java.sql.Date) ngay, thanhtoan, tenkhach, tennv, story);
				reuslt.add(model);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	public int selectCount() {
		int hoadon = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("select Count(maHD) from hoadon");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				hoadon = rs.getInt(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoadon;
	}

}
