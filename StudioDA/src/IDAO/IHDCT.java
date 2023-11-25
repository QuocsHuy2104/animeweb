package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
					"insert into HoaDon values (?, ?, ?, ?, (select masp from sanpham where tensp like ?))");
			ps.setString(1, reneric.getMaHDCT());
			ps.setFloat(2, reneric.getDonGia());
			ps.setInt(3, reneric.getSoLuong());
			ps.setString(4, reneric.getMaHD());
			ps.setString(5, reneric.getMaSP());

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
		return 0;
	}

	@Override
	public int update(HDCTModel reneric) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HDCTModel selectByID(HDCTModel generic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HDCTModel> selectAll() {
		ArrayList<HDCTModel> reuslt = new ArrayList<HDCTModel>();
		Connection conn = JDBCUtil.getConnection();

		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT A.MaSP,TenSP, A.DonGia, SoLuong, ThanhToan,TrangThai FROM SANPHAM A\r\n"
							+ "INNER JOIN HDCT B ON A.MaSP = B.MaSP\r\n" 
							+ "INNER JOIN HOADON C ON B.MaHD = C.MaHD");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String masp = rs.getString(1);
				String tensp = rs.getString(2);
				float dongia = rs.getFloat(3);
				int soluong = rs.getInt(4);
				float thanhtoan = rs.getFloat(5);
				String trangthai = rs.getString(6);

				HDCTModel model = new HDCTModel(dongia, soluong, tensp, masp, trangthai, thanhtoan);
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

}
