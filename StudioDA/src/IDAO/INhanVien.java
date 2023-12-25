package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import application.ForgotPassController;
import connectJDBC.JDBCUtil;
import model.NhanVienModel;

public class INhanVien implements DAOInterface<NhanVienModel> {

	public static INhanVien getInstance() {
		return new INhanVien();
	}

	@Override
	public int insert(NhanVienModel reneric) {
		int result = 0;

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("insert into nhanvien values(?,?,?,?,?,?,?,?)");
			pst.setString(1, reneric.getMaNV());
			pst.setString(2, reneric.getTenNV());
			pst.setString(3, reneric.getDiaChi());
			pst.setString(4, reneric.getSdt());
			pst.setString(5, reneric.getEmail());
			pst.setString(6, reneric.getMatKhau());
			pst.setInt(7, reneric.isVaiTro() == true ? 1 : 0);
			pst.setInt(8, reneric.isTrangThai() == true ? 1 : 0);

			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int del(NhanVienModel reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Delete from nhanvien where manv = ?");
			pst.setString(1, reneric.getMaNV());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(NhanVienModel reneric) {
		int result = 0;

		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn
					.prepareStatement("Update nhanvien set TenNV = ?, DIACHI = ?, SDT = ?,Email = ? where MaNV = ?");
			pst.setString(1, reneric.getTenNV());
			pst.setString(2, reneric.getDiaChi());
			pst.setString(3, reneric.getSdt());
			pst.setString(4, reneric.getEmail());
			pst.setString(5, reneric.getMaNV());
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public NhanVienModel selectByID(NhanVienModel generic) {
		NhanVienModel result = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from nhanvien where manv like ? or TenNV like ?");
			pst.setString(1, generic.getMaNV());
			pst.setString(2, generic.getTenNV());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MaNV");
				String name = rs.getString("TenNV");
				String address = rs.getString("DiaChi");
				String sdt = rs.getString("SDT");
				String Email = rs.getString("Email");
				String pass = rs.getString("pass");
				int role = rs.getInt("VaiTro");
				int status = rs.getInt("trangThai");

				boolean roles = role == 1 ? true : false;
				boolean statuss = status == 1 ? true : false;

				result = new NhanVienModel(id, name, address, sdt, Email, pass, roles, statuss);
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
	public ArrayList<NhanVienModel> selectAll() {
		ArrayList<NhanVienModel> result = new ArrayList<NhanVienModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM NHANVIEN");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString("MaNV");
				String name = rs.getString("TenNV");
				String address = rs.getString("DiaChi");
				String phone = rs.getString("SDT");
				String Email = rs.getString("Email");
				String pass = rs.getString("pass");
				int role = rs.getInt("vaitro");
				int status = rs.getInt("trangThai");

				boolean roles = role == 1 ? true : false;
				boolean statuss = status == 1 ? true : false;

				NhanVienModel nv = new NhanVienModel(id, name, address, phone, Email, pass, roles, statuss);
				result.add(nv);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int forgotPassword(String pass, String email, String manv) {
		int result = 0;

		Connection conn = JDBCUtil.getConnectionDefault();
		try {
			PreparedStatement pst = conn.prepareStatement("Update nhanvien set pass = ? where email = ? and manv = ?");
			pst.setString(1, pass);
			pst.setString(2, email);
			pst.setString(3, manv);
			result = pst.executeUpdate();
			pst.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean existMail() {
		
		String email = "";
		Connection conn = JDBCUtil.getConnectionDefault();
		try {
			PreparedStatement pst = conn.prepareStatement("Select Email from nhanvien where Email like ?");
			pst.setString(1, ForgotPassController.mail);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				email += rs.getString(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (email.equals("")) return false;
		else return true;
	}

	public int selectCount() {
		int nhanvien = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("select Count(manV) from nhanvien");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				nhanvien = rs.getInt(1);
			}
			pst.close();
			rs.close();
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanvien;
	}
	
	public void changePassword(String newpassword, String oldpassword, String user)	{
		Connection conn = JDBCUtil.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Update nhanvien set pass = ? where pass = ? and manv = ?");
            pst.setString(1, newpassword);
            pst.setString(2, oldpassword);
            pst.setString(3, user);
            pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void locked(String manv) {
		Connection conn = JDBCUtil.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Update nhanvien set trangthai = 0 where manv = ?");
            pst.setString(1, manv);
            pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public ArrayList<NhanVienModel> listLocked() {
		ArrayList<NhanVienModel> result = new ArrayList<NhanVienModel>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select TenNV, MaNV, vaitro from nhanvien where trangthai = 0");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tennv = rs.getString(1);
				String manv = rs.getString(2);
				String vaitro = rs.getInt(3) == 0 ? "Nhan Vien" : "Truong Phong";
				
				result.add(new NhanVienModel(tennv, manv, vaitro));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int openLock(String manv) throws SQLException {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("update nhanvien set trangthai = 1 where manv = ?");
		ps.setString(1, manv);
		result = ps.executeUpdate();
		
		ps.close();
		JDBCUtil.closeConnection(conn);
		return result;
	}

}
