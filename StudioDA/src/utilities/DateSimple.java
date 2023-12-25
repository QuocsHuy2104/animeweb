package utilities;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateSimple {

	public static int testDate(Date date, Date date2) {
		return date.compareTo(date2);
	}

	public static Date asDate(LocalDate localDate) {
		return (Date) Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static java.util.Date convertDate(java.sql.Date sqlDate) {
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());;
		return utilDate;
	}

	public static String asString(Date date) {
		date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	public static Date convertDateSql(java.util.Date utilDate) {
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

}
