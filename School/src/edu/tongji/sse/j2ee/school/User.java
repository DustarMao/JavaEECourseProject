package edu.tongji.sse.j2ee.school;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.errors.WrongPassword;

public class User {
	public final int id;
	public User(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		RowSet rs = DB.select("*", "user", "id ="+id);
		if (rs.next())
			this.id = id;
		else
			throw new Exception("UserNotFound");
	}
	
	// static
	public static int getNewId() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		RowSet rs = DB.select("id", "user");
		int newId = 1;
		while (rs.next()) {
			if (rs.getInt("id") >= newId) {
				newId = rs.getInt("id")+1;
			}
		}
		return newId;
	}
	
	public static User getUser(int id, String pass) throws UserIdNotFound, WrongPassword, Exception {
		RowSet rs = DB.select("password", "user", "id = "+id);
		if (rs.next()) {
			if (pass.equals(rs.getString("password"))) {
				return new User(id);
			}
			else {
				throw new WrongPassword();
			}
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
	public static User newUser(int id, String pass, boolean isTeach, boolean isAdmin) throws Exception {
		Connection conn = DB.getConnection();
		if (DB.select("*", "user", "id = "+id).next())
			throw new Exception("id existed");
		PreparedStatement pStmt = conn.prepareStatement("insert into user(id,password,teacher,admin) values(?,?,?,?)");
		pStmt.setInt(1, id);
		pStmt.setString(2, pass);
		pStmt.setBoolean(3, isTeach);
		pStmt.setBoolean(4, isAdmin);
		pStmt.executeUpdate();
		conn.close();
		return new User(id);
	}
	
	public static void removeUser(User user) throws Exception {
		DB.delete("user", "id = "+user.id);
	}
	
	// this user
	protected RowSet getTuple() throws UserIdNotFound, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			RowSet rs = DB.select("*", "user", "id ="+this.id);
			if (rs.next())
				return rs;
			else
				throw new UserIdNotFound();
	}
	protected void setCol(String col, boolean val) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("user", col, val, "id = "+this.id);
	}
	protected void setCol(String col, Date val) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("user", col, val, "id = "+this.id);
	}
	protected void setCol(String col, String val) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("user", col, val, "id = "+this.id);
	}
	
	public boolean isTeacher() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getBoolean("teacher");
	}
	
	public boolean isAdmin() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getBoolean("admin");
	}
	
	public boolean isPassword(String pass) throws Exception, UserIdNotFound {
		return pass.equals(getTuple().getString("password"));
	}
	
	public String getName() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("name");
	}
	
	public String getDepartment() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("department");
	}
	
	public String getResidentId() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("resident_id");
	}
	
	
	public Date getBirthday() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getDate("birthday");
	}
	
	public String getBirthplace() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("birthplace");
	}
	
	public boolean isMale() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getBoolean("sex");
	}
	
	public String getPhone() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("phone");
	}
	
	public String getEmail() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("email");
	}
	
	public String getQQ() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("qq");
	}
	
	public String getWebsite() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("website");
	}
	
	public String getAddress() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTuple().getString("address");
	}
	
	public void setTeacher(boolean isTeach) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("teacher", isTeach);
	}
	
	public void setAdmin(boolean isAdmin) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("admin", isAdmin);
	}
	
	public boolean setPassword(String nPass, String oPass) throws Exception, WrongPassword, UserIdNotFound {
		if (isPassword(oPass)) {
			setCol("password", nPass);
			return true;
		}
		else
			return false;
	}
	
	public void setName(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("name", name);
	}
	
	public void setDepartment(String depart) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("department", depart);
	}
	
	public void setResidentId(String rid) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("resident_id", rid);
	}
	
	public void setBirthday(Date bd) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("birthday", bd);
	}
	
	public void setBirthplace(String bp) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("birthplace", bp);
	}
	
	public void setSex(boolean isMale) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("sex", isMale);
	}
	
	public void setPhone(String p) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("phone", p);
	}
	
	public void setEmail(String em) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("email", em);
	}
	
	public void setQQ(String qq) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("qq", qq);
	}
	
	public void setWebsite(String href) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("website", href);
	}
	
	public void setAddress(String adr) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setCol("address",adr);
	}

	public Student toStudent() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound, Exception {
		if (!isTeacher())
			return (Student)this;
		else
			throw new Exception("It's not a Student");
	}
	public Teacher toTeacher() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound, Exception {
		if (isTeacher())
			return (Teacher)this;
		else
			throw new Exception("It's not a Student");
	}
}
