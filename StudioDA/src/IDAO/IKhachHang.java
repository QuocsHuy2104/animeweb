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
		return 0;
	}

	@Override
	public int del(KhachHangModel reneric) {
		return 0;
	}

	@Override
	public int update(KhachHangModel reneric) {
		return 0;
	}

	@Override
	public KhachHangModel selectByID(KhachHangModel generic) {
		return null;
	}

	@Override
	public ArrayList<KhachHangModel> selectAll() {
		ArrayList<KhachHangModel> result = new ArrayList<KhachHangModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM KHACHHANG");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("MaKH");
				String name = rs.getString("TenKH");
				String address = rs.getString("DiaChi");
				String phone = rs.getString("SDT");
				int gender = rs.getInt("GioiTinh");
				int idSP = rs.getInt("id_sanpham");
				
				
				boolean genders = gender == 1 ? true : false;
				
				KhachHangModel kh = new KhachHangModel(id, name, address, phone, genders, idSP);
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
	
	

}
