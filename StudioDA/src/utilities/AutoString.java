package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectJDBC.JDBCUtil;

public class AutoString {
	
	public static String autoEmail(String username) {
		String[] split = username.split(" ");
		int lenght = split.length;
		String name = split[lenght - 1];
		String spf = "";
		for (int i = 0; i < split.length -1; i++) {
			spf += split[i].substring(0, 1);
		}
		return name + spf + "@gmail.com";
	}
	
	public static String autoID(String username) {
		int max = 1;
		String[] split = username.split(" ");
		
		String ID = "";
		for (int i = 0; i < split.length; i++) {
			ID += split[i].substring(0, 1);
		}
		
		return ID +(String.format("%04d", max));
	}

}
