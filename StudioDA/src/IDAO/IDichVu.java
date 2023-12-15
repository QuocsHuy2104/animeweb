package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.DichVuModel;

public class IDichVu implements DAOInterface<DichVuModel> {

	public static IDichVu getInstance() {
		return new IDichVu();
	}

	@Override
	public int insert(DichVuModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn
					.prepareStatement("Insert into DICHVU values (?,?,?,?,(select masp from sanpham where tensp = ?))");
			pst.setString(1, reneric.getMaDV());
			pst.setString(2, reneric.getTenDV());
			pst.setFloat(3, reneric.getGiaDV());
			pst.setString(4, reneric.getMoTa());
			pst.setString(5, reneric.getTenSp());
			result = pst.executeUpdate();

			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int del(DichVuModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from dichvu where MaDV like ?");
			pst.setString(1, reneric.getMaDV());

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(DichVuModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"update dichvu set tendv = ?, GiaDV = (select madv from dichvu where tendv = ?),MaSP = ?, MoTaDV  where masp = ?");
			pst.setString(1, reneric.getTenDV());
			pst.setFloat(2, reneric.getGiaDV());
			pst.setString(3, reneric.getTenSp());
			pst.setString(4, reneric.getMoTa());
			pst.setString(5, reneric.getMaDV());

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DichVuModel selectByID(DichVuModel generic) {
		DichVuModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MaDV, TenDV, GiaDV, MoTaDV, TenSP from DICHVU\r\n"
					+ "inner join SANPHAM on DICHVU.MaSP = SANPHAM.MaSP");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MaDV");
				String name = rs.getString("TenDV");
				Float donGia = rs.getFloat("GiaDV");
				String tenth = rs.getString("MoTaDV");
				String tensp = rs.getString("TenSP");

				result = new DichVuModel(id, name, donGia, tenth, tensp);
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
	public ArrayList<DichVuModel> selectAll() {
		ArrayList<DichVuModel> result = new ArrayList<DichVuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MaDV, TenDV, GiaDV, MoTaDV, TenSP from DICHVU\r\n"
					+ "inner join SANPHAM on DICHVU.MaSP = SANPHAM.MaSP");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MaDV");
				String name = rs.getString("TenDV");
				Float donGia = rs.getFloat("GiaDV");
				String tenth = rs.getString("MoTaDV");
				String tensp = rs.getString("TenSP");

				DichVuModel sp = new DichVuModel(id, name, donGia, tenth, tensp);
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
	
	public ArrayList<String> selectNamePro() {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select tenSP from SanPham");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				list.add(name);
			}
			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<DichVuModel> selectMax() {
		ArrayList<DichVuModel> result = new ArrayList<DichVuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MaDV, TenDV, GiaDV, MoTaDV, TenSP from DICHVU\r\n"
					+ "inner join SANPHAM on DICHVU.MaSP = SANPHAM.MaSP\r\n"
					+ "ORDER BY MaDV DESC");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MaDV");
				String name = rs.getString("TenDV");
				Float donGia = rs.getFloat("GiaDV");
				String tenth = rs.getString("MoTaDV");
				String tensp = rs.getString("TenSP");

				DichVuModel sp = new DichVuModel(id, name, donGia, tenth, tensp);
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
	
	public ArrayList<DichVuModel> selectAllByIDName(String id, String name) {
		ArrayList<DichVuModel> result = new ArrayList<DichVuModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MaDV,TenDV,TenSP,GiaDV from DICHVU A \r\n"
					+ "INNER JOIN SANPHAM B ON A.MaSP = B.MaSP\r\n"
					+ "WHERE MADV like ? or TenDV LIKE ?");
			pst.setString(1, id);
			pst.setString(2, name);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String madv = rs.getString("MaDV");
				String tendv = rs.getString("TenDV");
				String tensp = rs.getString("TenSP");
				Float giadv = rs.getFloat("Giadv");

				DichVuModel sp = new DichVuModel(madv, tendv, giadv, null, tensp);
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

}
