package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.Trademark;

public class ITrademark implements DAOInterface<Trademark> {
	
	public static ITrademark getInstance() {
		return new ITrademark();
	}

	@Override
	public int insert(Trademark reneric) {
		return 0;
	}

	@Override
	public int del(Trademark reneric) {
		return 0;
	}

	@Override
	public int update(Trademark reneric) {
		return 0;
	}

	@Override
	public Trademark selectByID(Trademark generic) {
		return null;
	}

	@Override
	public ArrayList<Trademark> selectAll() {
		ArrayList<Trademark> result = new ArrayList<Trademark>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM THUONGHIEU");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maTH = rs.getString("maTH");
				String tenTH = rs.getString("tenTH");
				Trademark th = new Trademark(maTH, tenTH);
				result.add(th);
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
