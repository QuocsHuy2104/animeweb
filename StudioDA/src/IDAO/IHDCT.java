package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.HDCTModel;
import model.HoaDonModel;
public class IHDCT implements DAOInterface<HDCTModel> {

	@Override
	public int insert(HDCTModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"insert into HDCT values (?, ?, ?, (select MAX(?) from HoaDon), (select masp from sanpham where tensp like ?))");
			ps.setString(1, reneric.getMaHDCT());
			ps.setFloat(2, reneric.getDonGia());
			ps.setInt(3, reneric.getSoLuong());
			ps.setString(4, reneric.getMaHD());
			ps.setString(5, reneric.getMasp());

			result = ps.executeUpdate();
			ps.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(HDCTModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("delete from HDCT where MaSP like ?");
			pst.setString(1, reneric.getMasp());

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(HDCTModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "update HDCT set SoLuong = ? where MaHDCT = (select MaHDCT from HDCT where masp like ? and MaHD = ?)";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, reneric.getSoLuong());
			pst.setString(2, reneric.getMasp());
			pst.setString(3, reneric.getMaHD());

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public HDCTModel selectByID(HDCTModel generic) {
		return null;
	}

	@Override
	public ArrayList<HDCTModel> selectAll() {
		ArrayList<HDCTModel> reuslt = new ArrayList<HDCTModel>();
		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement(
					"select MaSP, (select tensp from SANPHAM where MaSP = HDCT.MaSP), HDCT.DonGia, SoLuong, DonGia * SoLuong as thanhToan from hdct\r\n"
							+ "inner join HOADON on HDCT.MaHD = HOADON.MaHD where HDCT.mahd like (select max(mahd) from HOADON)");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String masp = rs.getString(1);
				String tensp = rs.getString(2);
				float dongia = rs.getFloat(3);
				int soluong = rs.getInt(4);
				float thanhtoan = rs.getFloat(5);
				

				HDCTModel model = new HDCTModel(dongia, soluong, tensp, masp, thanhtoan);
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

	public static IHDCT getInstance() {
		return new IHDCT();
	}

	public float selectPaid(String mahd) {
		float result = 0;

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("select sum(dongia * soluong) as thanhtoan from HDCT where MaHD like ?");
			ps.setString(1, mahd);

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

	public String selectMaxID() {

		String result = "";

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select MAX(mahd) from HOADON");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}

			rs.close();
			pst.close();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int delByBill(HDCTModel model) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("Delete from hdct where mahd like ?");
			ps.setString(1, model.getMaHD());
			
			result = ps.executeUpdate();
			ps.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//
	public ArrayList<HDCTModel> selectAllByIDBill(String mahd) {
		ArrayList<HDCTModel> reuslt = new ArrayList<HDCTModel>();
		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement(
					"select MaSP, (select tensp from SANPHAM where MaSP = HDCT.MaSP), HDCT.DonGia, SoLuong, DonGia * SoLuong as thanhToan from hdct\r\n"
							+ "inner join HOADON on HDCT.MaHD = HOADON.MaHD where HDCT.mahd like (select max(?) from HOADON)");
			pst.setString(1, mahd);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String masp = rs.getString(1);
				String tensp = rs.getString(2);
				float dongia = rs.getFloat(3);
				int soluong = rs.getInt(4);
				float thanhtoan = rs.getFloat(5);
				

				HDCTModel model = new HDCTModel(dongia, soluong, tensp, masp, thanhtoan);
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

}
