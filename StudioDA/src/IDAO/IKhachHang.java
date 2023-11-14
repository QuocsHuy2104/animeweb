package IDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.KhachHangModel;
import model.NhanVienModel;

public class IKhachHang implements DAOInterface<KhachHangModel> {

	public static IKhachHang getInstance() {
		return new IKhachHang();
	}

	@Override
	public int insert(KhachHangModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("insert into khachhang values (?, ?, ?, ?, ?, ?)");
			pst.setString(1, reneric.getMaKH());
			pst.setString(2, reneric.getTenKH());
			pst.setString(3, reneric.getDiachi());
			pst.setString(4, reneric.getSdt());
			pst.setBoolean(5, reneric.isGioiTinh());
			pst.setString(6, reneric.getEmail());
			result = pst.executeUpdate();

			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(KhachHangModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from khachhang where makh = ?");
			pst.setString(1, reneric.getMaKH());
			result = pst.executeUpdate();

			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(KhachHangModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("update khachhang set tenkh = ?, diachi = ?, sdt = ? where makh = ?");
			pst.setString(1, reneric.getTenKH());
			pst.setString(2, reneric.getDiachi());
			pst.setString(3, reneric.getSdt());
			pst.setString(4, reneric.getMaKH());
			result = pst.executeUpdate();

			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public KhachHangModel selectByID(KhachHangModel generic) {
		KhachHangModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select * from khachhang where makh = ?");
			pst.setString(1, generic.getMaKH());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("TenKH");
				String name = rs.getString("TenKH");
				String address = rs.getString("DIACHI");
				String contact = rs.getString("SDT");
				int role = rs.getInt("GioiTinh");
				String email = rs.getString("setString");
				int tt = rs.getInt("trangThai");

				boolean genders = role == 1 ? true : false;
				boolean trangThai = tt == 1 ? true : false;

				result = new KhachHangModel(id, name, address, contact, genders, email, trangThai);
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
	public ArrayList<KhachHangModel> selectAll() {
		ArrayList<KhachHangModel> result = new ArrayList<KhachHangModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM KHACHHANG");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MaKH");
				String name = rs.getString("TenKH");
				String address = rs.getString("DiaChi");
				String phone = rs.getString("SDT");
				int gender = rs.getInt("GioiTinh");
				String email = rs.getString("Email");
				int tt = rs.getInt("trangThai");

				boolean genders = gender == 1 ? true : false;
				boolean trangThai = tt == 1 ? true : false;

				KhachHangModel kh = new KhachHangModel(id, name, address, phone, genders, email, trangThai);
				result.add(kh);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectCount() {
		int khachhang = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("select Count(makh) from Khachhang");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				khachhang = rs.getInt(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachhang;
	}

}
