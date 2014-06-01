package edu.tongji.sse.j2ee.dboperator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSchool {
	public static Connection conn;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql//localhost:3306/school", "root", "root");
		return con;
	}
}
