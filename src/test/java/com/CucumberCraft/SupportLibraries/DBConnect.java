package com.CucumberCraft.supportLibraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnect {

	@SuppressWarnings("unused")
	public static String Getclienthealthinfo(String userName, String RecordDate) {
		// Retrieve clud id , record date, account type, account id, steps,
		// calories, weight, energy burned,energy burned goal, exercise time,
		// exercise time goal, stand hours, stand hours goal
		Connection con = null;
		String clubid = null;
		try {
			Properties properties = Settings.getInstance();
			String dbname = properties.getProperty("DataBaseName");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + properties.getProperty("HOST") + ":"
							+ properties.getProperty("DataBaseName"),
					properties.getProperty("DBUSerid"), properties.getProperty("DBPassword"));
			// step3 create the statement object
			Statement stmt = con.createStatement();

			// step4 execute query
			ResultSet rs = stmt.executeQuery(
					"select * from manuclub_client_health_info where club_id in (select club_id from manuclub_client_profile where username like '%"
							+ userName + "%') and record_date = '" + RecordDate + "' order by record_date desc");
			while (rs.next())
			clubid = rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--" + rs.getString(4) + "--"
					+ rs.getString(5) + "--" + rs.getString(6) + "--" + rs.getString(7) + "--" + rs.getString(8) + "--"
					+ rs.getString(9) + "--" + rs.getString(10) + "--" + rs.getString(11) + "--" + rs.getString(12)
					+ "--" + rs.getString(13);
			// step5 close the connection object
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("No value");
		}
		return clubid;
	}

	@SuppressWarnings("unused")
	public static String Getclientsleepinfo(String userName, String RecordDate) {
		// Retrieve clud id , record date, account type, account id, steps,
		// calories, weight, energy burned,energy burned goal, exercise time,
		// exercise time goal, stand hours, stand hours goal
		Connection con = null;
		String clubid = null;
		try {
			Properties properties = Settings.getInstance();
			String dbname = properties.getProperty("DataBaseName");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + properties.getProperty("HOST") + ":"
							+ properties.getProperty("DataBaseName"),
					properties.getProperty("DBUSerid"), properties.getProperty("DBPassword"));
			// step3 create the statement object
			Statement stmt = con.createStatement();

			// step4 execute query
			ResultSet rs = stmt.executeQuery(
					"select * from manuclub_client_health_info where club_id in (select club_id from manuclub_client_profile where username like '%"
							+ userName + "%') and record_date = '" + RecordDate + "' order by record_date desc");
			while (rs.next())
				System.out.println(rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--"
						+ rs.getString(4) + "--" + rs.getString(5) + "--" + rs.getString(6) + "--" + rs.getString(7)
						+ "--" + rs.getString(8) + "--" + rs.getString(9) + "--" + rs.getString(10) + "--"
						+ rs.getString(11) + "--" + rs.getString(12) + "--" + rs.getString(13));
			clubid = rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--" + rs.getString(4) + "--"
					+ rs.getString(5) + "--" + rs.getString(6) + "--" + rs.getString(7) + "--" + rs.getString(8) + "--"
					+ rs.getString(9) + "--" + rs.getString(10) + "--" + rs.getString(11) + "--" + rs.getString(12)
					+ "--" + rs.getString(13);
			// step5 close the connection object
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("No value");
		}
		return clubid;
	}
}
