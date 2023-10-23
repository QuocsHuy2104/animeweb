package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.DonHangModel;
import model.KhachHangModel;

public class IHoaDon implements DAOInterface<DonHangModel> {
	
	public static IHoaDon getInstance() {
		return new IHoaDon();
	}

	@Override
	public int insert(DonHangModel reneric) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(DonHangModel reneric) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DonHangModel reneric) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DonHangModel selectByID(DonHangModel generic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DonHangModel> selectAll() {
		return null;
	}

	public ArrayList<DonHangModel> selecOfBill() {
		ArrayList<DonHangModel> reuslt = new ArrayList<DonHangModel>();
		Connection conn = JDBCUtil.getConnection();
		
//		"select mahd, tenkh, dichvu, ngay, thanhtoan\r\n"
//		+ "from hoadon\r\n" 
//		+ "inner join khachhang on HOADON.ID_KhachHang = KHACHHANG.ID_KhachHang\r\n"
//		+ "inner join NHANVIEN on NHANVIEN.ID_NV = HOADON.ID_NV\r\n"
//		+ "inner join SANPHAM on SANPHAM.ID_NV = NHANVIEN.ID_NV"
		
		try {
			PreparedStatement pst = conn.prepareStatement("select * from HoaDon");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString("mahd");
				Date ngay = rs.getDate("ngay");
				float thanhToan = rs.getFloat("ThanhToan");
				int idkh = rs.getInt("ID_KhachHang");
				int idnv = rs.getInt("ID_NV");

				DonHangModel model = new DonHangModel(mahd, ngay, thanhToan, idkh, idnv);
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
