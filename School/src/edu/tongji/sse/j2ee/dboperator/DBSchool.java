package edu.tongji.sse.j2ee.dboperator;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBSchool {
	public static void main(PrintWriter out) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","root");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from actor");
			while (rs.next()) {
				out.println(""+rs.getInt("actor_id")+"\t"+rs.getString("first_name")+" "+rs.getString("last_name"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
