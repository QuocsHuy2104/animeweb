package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.regex.Pattern;

import connectJDBC.JDBCUtil;

public class AutoString {

	public static String convertString(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public static String autoEmail(String username) {
		String[] split = username.split(" ");
		int lenght = split.length;
		String name = split[lenght - 1];
		String spf = "";
		for (int i = 0; i < split.length - 1; i++) {
			spf += split[i].substring(0, 1);
		}
		return name + spf + "@gmail.com";
	}

	public static String autoID(String type, String idTable, String Table) {
		int id = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT Max(" + idTable + ") as LastID FROM " + Table);
			ResultSet rs = pst.executeQuery();
			String makh = "";
			while (rs.next()) {
				makh += rs.getString(1);
			}

			String[] split = makh.split("000");
			for (int i = 1; i < split.length; i++) {
				id = Integer.parseInt(split[i]);

			}
			pst.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return type + (String.format("%04d", id + 1));
	}
	
	public static String formatMoney(Float value) {
		DecimalFormat df = new DecimalFormat("###,##0.000");
		return df.format(Float.valueOf(value).toString());
	}

}
