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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(SanPhamModel reneric) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SanPhamModel reneric) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SanPhamModel selectByID(SanPhamModel generic) {
		// TODO Auto-generated method stub
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

}
