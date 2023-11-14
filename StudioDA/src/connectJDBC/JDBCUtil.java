package connectJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class JDBCUtil {
	
	public static Connection getConnection() {
		Connection conn = null;

		String server = LoginController.server;
		
		String database = LoginController.database;
		
		String pass = LoginController.passDB;
		
		if (server.equals("") || database.equals("") || pass.equals("")) {
			String url = "jdbc:sqlserver://LAPTOP-DTHD\\TESTSERVER:1433;"
					+ "user=sa;password=0398948675;databaseName=STUDIO;encrypt=false";
			
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		} else {
			String url = "jdbc:sqlserver://"+ server +":1433;"
					+ "user=sa;password="+ pass +
					";databaseName="
					+ database + ";encrypt=false";
			
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		}
		
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
