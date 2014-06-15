package edu.tongji.sse.j2ee.school;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Exam {
	public final Course course;
	Exam(Course course) throws Exception {
		ResultSet rs = DB.select("*", "exam", "course_id = "+course.courseId);
		if (rs.next())
			this.course = course;
		else
			throw new Exception("ExamNotFound");
	}
	
	public ResultSet getTuple() throws Exception{
		ResultSet rs = DB.select("*", "exam", "course_id = "+course.courseId);
		if (rs.next())
			return rs;
		else
			throw new Exception("ExamNotFound");
	}
	
	public String getPlace() throws SQLException, Exception {
		return getTuple().getString("place");
	}
	
	public Date getDate() throws SQLException, Exception {
		return getTuple().getDate("date");
	}
	
	public Time getTime() throws SQLException, Exception {
		return getTuple().getTime("time");
	}
	
	public void setPlace(String place) throws Exception {
		DB.setPara("exam", "place", place, "course_id = "+course.courseId);
	}
	
	public void setDate(Date date) throws Exception {
		DB.setPara("exam", "date", date, "course_id = "+course.courseId);
	}
	
	public void setTime(Time time) throws Exception{
		DB.setPara("exam", "time", time, "course_id = "+course.courseId);
	}

	// Static
	public static Exam addExam(Course course, String place, Date date, Time time) throws Exception {
		try {
			new Exam(course);
		} catch (Exception e) {
			if (e.equals(new Exception("ExamNotFound"))) {
				Connection conn = DB.getConnection();
				PreparedStatement pStmt = conn.prepareStatement("insert into exam(course_id,place,date,time) values(?,?,?,?)");
				pStmt.setInt(1, course.courseId);
				pStmt.setString(2, place);
				pStmt.setDate(3, date);
				pStmt.setTime(4, time);
				pStmt.executeUpdate();
				conn.close();
				return new Exam(course);
			}
			else
				throw e;
		}
		throw new Exception("ExamExisted");
	}
}
