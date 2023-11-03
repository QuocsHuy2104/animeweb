package IDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.NhanVienModel;

public class INhanVien implements DAOInterface<NhanVienModel> {
	
	public static INhanVien getInstance() {
		return new INhanVien();
	}

	@Override
	public int insert(NhanVienModel reneric) {
		int result = 0;
		
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("insert into nhanvien values(?,?,?,?,?,?,?,?)");
			pst.setString(1, reneric.getMaNV());
			pst.setString(2, reneric.getTenNV());
			pst.setString(3, reneric.getDiaChi());
			pst.setString(4, reneric.getSoDT());
			pst.setString(5, reneric.getNgayNhan());
			pst.setDouble(6, reneric.getLuong());
			pst.setString(7, reneric.getPass());
			pst.setBoolean(8, reneric.isRoles());
			
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int del(NhanVienModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Delete from nhanvien where manv = ?");
			pst.setString(1, reneric.getMaNV());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(NhanVienModel reneric) {
		int result = 0;
		
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Update nhanvien set tenNV = ?, diaChi = ?, sdt = ?,luong = ? where manv = ?");
			pst.setString(1, reneric.getTenNV());
			pst.setString(2, reneric.getDiaChi());
			pst.setString(3, reneric.getSoDT());
			pst.setString(4, Double.valueOf(reneric.getLuong()).toString());
			pst.setString(5, reneric.getMaNV());
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public NhanVienModel selectByID(NhanVienModel generic) {
		NhanVienModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select * from nhanvien where manv = ?");
			pst.setString(1, generic.getMaNV());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("MaNV");
				String name = rs.getString("TenNV");
				String address = rs.getString("DiaChi");
				String contact = rs.getString("SDT");
				Double luong = rs.getDouble("luong");
				String ngaynhan = rs.getString("NgayNhan");
				String pass = rs.getString("pass");
				boolean role = rs.getBoolean("VaiTro");
				result = new NhanVienModel(id, name, address, contact, luong, ngaynhan, pass, role);
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
	public ArrayList<NhanVienModel> selectAll() {
		ArrayList<NhanVienModel> result = new ArrayList<NhanVienModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM NHANVIEN");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("MaNV");
				String name = rs.getString("TenNV");
				String address = rs.getString("DiaChi");
				String phone = rs.getString("SDT");
				Double luong = rs.getDouble("luong");
				String reDate = rs.getString("NgayNhan");
				String pass = rs.getString("pass");
				int role = rs.getInt("vaitro");
				
				boolean roles = role == 1 ? true : false;
				
				NhanVienModel nv = new NhanVienModel(id, name, address, phone, luong, reDate, pass, roles);
				result.add(nv);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updatePass(String pass, String manv) {
		int result = 0;
		
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Update pass = ? where manv = ?");
			pst.setString(1, pass);
			pst.setString(2, manv);
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int selectCount() {
		int nhanvien = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("select Count(manV) from nhanvien");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				nhanvien = rs.getInt(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanvien;
	}
	

}
