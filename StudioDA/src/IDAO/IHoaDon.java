package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.HoaDonModel;
import model.KhachHangModel;
import model.SanPhamModel;

public class IHoaDon implements DAOInterface<HoaDonModel> {
	
	public static IHoaDon getInstance() {
		return new IHoaDon();
	}

	@Override
	public int insert(HoaDonModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
//		String sql = "insert into hoadon values (?, ?,(select GiaDichVu\r\n"
//				+ "	from SANPHAM\r\n"
//				+ "	inner join KHACHHANG on KHACHHANG.id_sanpham = SANPHAM.ID_SANPHAM\r\n"
//				+ "	where makh = (select MaKH from KHACHHANG where ID_KhachHang = ?)"
//				+ " ,?,?)";
		
		String sql = "insert into hoadon values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, reneric.getMahd());
			pst.setString(2, reneric.getNgay());
			pst.setFloat(3, reneric.getThanhToan());
			pst.setInt(4, reneric.getID_KhachHang());
			pst.setInt(5, reneric.getID_NV());
			
			result = pst.executeUpdate();
			
			pst.close();
			JDBCUtil.closeConnection(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int del(HoaDonModel reneric) {
		int result  = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("delete from hoadon where mahd = ?");
			pst.setString(1, reneric.getMahd());
			
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(HoaDonModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
//		String sql = "update HOADON set Ngay = ?, ThanhToan = (select GiaDichVu\\r\\n\"\r\n"
//				+ "					+ \"	from SANPHAM\\r\\n\"\r\n"
//				+ "					+ \"	inner join KHACHHANG on KHACHHANG.id_sanpham = SANPHAM.ID_SANPHAM\\r\\n\"\r\n"
//				+ "					+ \"	where makh = (select MaKH from KHACHHANG where ID_KhachHang = ?)), ID_NV = ? where MaHD = ?";
		String sql = "update hoadon set ngay = ?, thanhtoan = ?, id_nv = ? where mahd = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, reneric.getNgay());
			pst.setFloat(2, reneric.getThanhToan());
			pst.setInt(3, reneric.getID_NV());
			pst.setString(4, reneric.getMahd());
			
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public HoaDonModel selectByID(HoaDonModel generic) {
		HoaDonModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select * from hoadon where mahd = ?");
			pst.setString(1, generic.getMahd());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("Mahd");
				String ngay = rs.getString("ngay");
				float thanhtoan = rs.getFloat("thanhtoan");
				int idkh = rs.getInt("id_khachhang");
				int idnv = rs.getInt("id_nv");
				result = new HoaDonModel(id, ngay, thanhtoan, idkh, idnv);
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
	public ArrayList<HoaDonModel> selectAll() {
		ArrayList<HoaDonModel> reuslt = new ArrayList<HoaDonModel>();
		Connection conn = JDBCUtil.getConnection();
		
		try {
			PreparedStatement pst = conn.prepareStatement("select * from hoadon");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString("mahd");
				String ngay = rs.getString("ngay");
				float thanhToan = rs.getFloat("ThanhToan");
				int idkh = rs.getInt("ID_KhachHang");
				int idnv = rs.getInt("ID_NV");

				HoaDonModel model = new HoaDonModel(mahd, ngay, thanhToan, idkh, idnv);
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
	
	public int selectCount() {
		int hoadon = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("select Count(maHD) from hoadon");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				hoadon = rs.getInt(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoadon;
	}
	
	
}
