package edu.tongji.sse.j2ee.school;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public final class DB {
	private DB() {}
	
	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
	}
	
	public static RowSet select(String select, String from) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select "+select+" from "+from);
		CachedRowSet crs = new CachedRowSetImpl();
		crs.populate(rs);
		stmt.close();
		conn.close();
		return crs;
	}
	
	public static RowSet select(String select, String from, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select "+select+" from "+from+" where "+where);
		CachedRowSet crs = new CachedRowSetImpl();
		crs.populate(rs);
		stmt.close();
		conn.close();
		return crs;
	}
	
	public static void update(String update, String set) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update "+update+" set "+set);
		conn.close();
	}
/*	
	public static void update(String update, String set, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update "+update+" set "+set+" where "+where);
		conn.close();
	}
*/
	public static void setPara(String table, String para, int val, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update "+table+" set "+para+" = ? where "+where);
		pStmt.setInt(1,val);
		pStmt.executeUpdate();
		conn.close();
	}
	public static void setPara(String table, String para, boolean val, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update "+table+" set "+para+" = ? where "+where);
		pStmt.setBoolean(1,val);
		pStmt.executeUpdate();
		conn.close();
	}
	public static void setPara(String table, String para, String val, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update "+table+" set "+para+" = ? where "+where);
		pStmt.setString(1,val);
		pStmt.executeUpdate();
		conn.close();
	}
	public static void setPara(String table, String para, Date val, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update "+table+" set "+para+" = ? where "+where);
		pStmt.setDate(1, val);
		pStmt.executeUpdate();
		conn.close();
	}
	public static void setPara(String table, String para, Time val, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update "+table+" set "+para+" = ? where "+where);
		pStmt.setTime(1, val);
		pStmt.executeUpdate();
		conn.close();
	}
	public static void setPara(String table, String para, Object val, String where) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update "+table+" set "+para+" = ? where "+where);
		pStmt.setString(1,val.toString());
		pStmt.executeUpdate();
		conn.close();
	}

	public static void delete(String from, String where) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("delete from "+from+" where "+where);
		conn.close();
	}
}
