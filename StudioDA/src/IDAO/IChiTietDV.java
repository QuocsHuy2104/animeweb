package IDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.ChiTietDVModel;

public class IChiTietDV implements DAOInterface<ChiTietDVModel> {

	public static IChiTietDV getInstance() {
		return new IChiTietDV();
	}

	@Override
	public int insert(ChiTietDVModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"insert into CHITIETHOADONDV values (?, ?, ?, ?, ?, (select madv from DICHVU where TenDV like ?), ?)");
			pst.setString(1, reneric.getMaHDCTDV());
			pst.setFloat(2, reneric.getGiaDV());
			pst.setDate(3, reneric.getNgayThue());
			pst.setDate(4, reneric.getNgayTraDK());
			pst.setDate(5, reneric.getNgayTraCT());
			pst.setString(6, reneric.getTenDV());
			pst.setString(7, reneric.getMaHDDV());

			result = pst.executeUpdate();

			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(ChiTietDVModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from chitiethoadondv where madv like (select madv from DICHVU where tendv like ?)");
			ps.setString(1, reneric.getTenDV());

			result = ps.executeUpdate();

			ps.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(ChiTietDVModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"update CHITIETHOADONDV\r\n"
					+ "set NgayTraDK = ?,\r\n"
					+ "NgayTraCT = ?\r\n"
					+ "where MaCTHDDV = (\r\n"
					+ "select MaCTHDDV from CHITIETHOADONDV\r\n"
					+ "where MaDV = ?)");
			ps.setDate(1, reneric.getNgayTraDK());
			ps.setDate(2, reneric.getNgayTraCT());
			ps.setString(3, reneric.getMaDV());

			result = ps.executeUpdate();

			ps.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ChiTietDVModel selectByID(ChiTietDVModel generic) {
		return null;
	}

	@Override
	public ArrayList<ChiTietDVModel> selectAll() {
		ArrayList<ChiTietDVModel> result = new ArrayList<ChiTietDVModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select DICHVU.madv, tendv, dichvu.giadv, ngaythue, ngaytract, dichvu.giadv * (day(ngaytract) - day(ngaythue)) as thanhtoan from CHITIETHOADONDV\r\n"
							+ "inner join DICHVU on CHITIETHOADONDV.MaDV = DICHVU.MaDV");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String madv = rs.getString(1);
				String tendv = rs.getString(2);
				float giadv = rs.getFloat(3);
				Date ngaythue = rs.getDate(4);
				Date ngaytra = rs.getDate(5);
				float thanhtoan = rs.getFloat(6);

				ChiTietDVModel model = new ChiTietDVModel(madv, tendv, giadv, ngaythue, ngaytra, thanhtoan);
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

	public ArrayList<ChiTietDVModel> selectAllByID(String mahddv) {
		ArrayList<ChiTietDVModel> result = new ArrayList<ChiTietDVModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select DICHVU.madv, tendv, dichvu.giadv, ngaythue, ngaytract, dichvu.giadv * (day(ngaytract) - day(ngaythue)) as thanhtoan from CHITIETHOADONDV\r\n"
							+ "inner join DICHVU on CHITIETHOADONDV.MaDV = DICHVU.MaDV\r\n" + "where MaHDDV like ?");
			ps.setString(1, mahddv);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String madv = rs.getString(1);
				String tendv = rs.getString(2);
				float giadv = rs.getFloat(3);
				Date ngaythue = rs.getDate(4);
				Date ngaytra = rs.getDate(5);
				float thanhtoan = rs.getFloat(6);

				ChiTietDVModel model = new ChiTietDVModel(madv, tendv, giadv, ngaythue, ngaytra, thanhtoan);
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
	
	public int delBillSerByID(String mahddv) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from chitiethoadondv where mahddv like ?");
			ps.setString(1, mahddv);
			
			result = ps.executeUpdate();
			ps.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public float selectPaid(String mahddv, Date ngaytract, Date ngaythue) {
		float result = 0;

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("select sum(giadv * (day(?) - DAY(?))) as thanhtoan from CHITIETHOADONDV where MaHDDV like ?");
			ps.setDate(1, ngaytract);
			ps.setDate(2, ngaythue);
			ps.setString(3, mahddv);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			ps.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public float sumPriceSer(String mahddv) {
		float result = 0;

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("select sum(giadv) as thanhtoan from CHITIETHOADONDV where MaHDDV like ?");
			ps.setString(1, mahddv);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getFloat(1);
			}
			ps.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
