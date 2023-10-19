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
			PreparedStatement pst = conn.prepareStatement("insert into nhanvien(?,?,?,?,?,?,?,?)");
			pst.setString(1, reneric.getMaNV());
			pst.setString(2, reneric.getTenNV());
			pst.setString(3, reneric.getDiaChi());
			pst.setString(4, reneric.getSoDT());
			pst.setDouble(5, reneric.getLuong());
			pst.setDate(6, (Date) reneric.getNgayNhan());
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
		return 0;
	}

	@Override
	public int update(NhanVienModel reneric) {
		return 0;
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
				Date ngaynhan = rs.getDate("NgayNhan");
				String pass = rs.getString("MatKhau");
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
		return null;
	}
	

}
