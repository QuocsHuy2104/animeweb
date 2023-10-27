package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.KhachHangModel;
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
			PreparedStatement pst = conn.prepareStatement("insert into sanpham values (?,?,?,?,?,?,?)");
			pst.setString(1, reneric.getMaSP());
			pst.setString(2, reneric.getTenSp());
			pst.setString(3, reneric.getThuongHieu());
			pst.setFloat(4, reneric.getGiaDichVu());
			pst.setString(5, reneric.getMoTa());
			pst.setString(6, reneric.getDichVu());
			pst.setInt(7, reneric.getMaNV());
			
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
			PreparedStatement pst = conn.prepareStatement("update sanpham set tensp = ?, thuonghieu = ?, giadv = ?, mota = ?, dichvu = ? where masp = ?");
			pst.setString(1, reneric.getTenSp());
			pst.setString(2, reneric.getThuongHieu());
			pst.setFloat(3, reneric.getGiaDichVu());
			pst.setString(4, reneric.getMoTa());
			pst.setString(5, reneric.getDichVu());
			pst.setString(6, reneric.getMaSP());
			
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
		return null;
	}

	@Override
	public ArrayList<SanPhamModel> selectAll() {
		ArrayList<SanPhamModel> result = new ArrayList<SanPhamModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM SanPham");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String idPro = rs.getString("MaSp");
				String nameSP = rs.getString("TenSP");
				String trademark = rs.getString("thuonghieu");
				float wage = rs.getFloat("GiaDichVu");
				String describe = rs.getString("mota");
				String service = rs.getString("DichVu");
				int idStaff = rs.getInt("ID_NV");
				
				SanPhamModel sp = new SanPhamModel(idPro, nameSP, trademark, wage, describe, service, idStaff);
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
