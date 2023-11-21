package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.SanPhamModel;

public class ISanPham implements DAOInterface<SanPhamModel> {
	
	public static ISanPham getInstance() {
		return new ISanPham();
	}

	@Override
	public int insert(SanPhamModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("insert into sanpham values (?,?,?, (select math from thuonghieu where tenth like ?))");
			pst.setString(1, reneric.getMaSP());
			pst.setString(2, reneric.getTenSp());
			pst.setFloat(3, reneric.getDonGia());
			pst.setString(4, reneric.getTenTH());
			
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(SanPhamModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from sanpham where masp = ?");
			pst.setString(1, reneric.getMaSP());
			
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(SanPhamModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("update sanpham set tensp = ?, dongia = ? where masp = ?");
			pst.setString(1, reneric.getTenSp());
			pst.setFloat(2, reneric.getDonGia());
			pst.setString(3, reneric.getMaSP());
			
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public SanPhamModel selectByID(SanPhamModel generic) {
		SanPhamModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MaSP, tensp, DonGia, tenth from SANPHAM\r\n"
					+ "inner join THUONGHIEU on SANPHAM.MaTH = THUONGHIEU.MaTH where MaSP like ?");
			pst.setString(1, generic.getMaSP());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("Masp");
				String name = rs.getString("Tensp");
				String maTH = rs.getString("TenTH");
				Float donGia = rs.getFloat("dongia");
				result = new SanPhamModel(id, name, donGia, maTH);
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
	public ArrayList<SanPhamModel> selectAll() {
		ArrayList<SanPhamModel> result = new ArrayList<SanPhamModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MaSP, tensp, DonGia, tenth from SANPHAM\r\n"
					+ "inner join THUONGHIEU on SANPHAM.MaTH = THUONGHIEU.MaTH");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("Masp");
				String name = rs.getString("Tensp");
				Float donGia = rs.getFloat("dongia");
				String tenth = rs.getString("tenth");
				
				SanPhamModel sp = new SanPhamModel(id, name, donGia, tenth);
				result.add(sp);
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
		int service = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("select Count(maSP) from sanpham");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				service = rs.getInt(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return service;
	}

}
