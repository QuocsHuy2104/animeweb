package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectJDBC.JDBCUtil;

public class Auth {

	public static boolean roles;

	public static boolean isManager(String manv) {
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select vaitro from nhanvien where manv = ?");
			pst.setString(1, manv);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getInt("vaitro") == 1)
					return roles = true;
				else
					return roles = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
}
