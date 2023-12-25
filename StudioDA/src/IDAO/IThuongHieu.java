package IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAOInterface;
import connectJDBC.JDBCUtil;
import model.Trademark;

public class IThuongHieu implements DAOInterface<Trademark> {
	
	public static IThuongHieu getInstance() {
		return new IThuongHieu();
	}

	@Override
	public int insert(Trademark reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("insert into thuonghieu values (?,?)");
			pst.setString(1, reneric.getMaTH());
			pst.setString(2, reneric.getTenTH());
			
			result = pst.executeUpdate();
			
			JDBCUtil.closeConnection(conn);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(Trademark reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from thuonghieu where math = ?");
			pst.setString(1, reneric.getMaTH());
			
			result = pst.executeUpdate();
			
			JDBCUtil.closeConnection(conn);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Trademark reneric) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("update thuonghieu set tenth = ? where math = ?");
			pst.setString(1, reneric.getTenTH());
			pst.setString(2, reneric.getMaTH());
			
			result = pst.executeUpdate();
			
			JDBCUtil.closeConnection(conn);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
			PreparedStatement ps = conn.prepareStatement("select * from thuonghieu");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String math = rs.getString(1);
				String tenth = rs.getString(2);
				
				Trademark model = new Trademark(math, tenth);
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
	
	public ArrayList<Trademark> selectTrademarkByID(String idTrademark) {
		ArrayList<Trademark> result = new ArrayList<Trademark>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select * from thuonghieu where math like ?");
			pst.setString(1, idTrademark);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String math = rs.getString(1);
				String tenth = rs.getString(2);
				
				result.add(new Trademark(math, tenth));
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JDBCUtil.closeConnection(conn);
		return result;
	}

}
