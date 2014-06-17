package edu.tongji.sse.j2ee.school;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.RowSet;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;

public class Student extends User {

	Student(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		super(id);
	}
	
	protected RowSet getStuTuple() throws UserIdNotFound, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		RowSet rs = DB.select("*", "student", "id ="+this.id);
		if (rs.next())
			return rs;
		else
			throw new UserIdNotFound();
	}
	
	public String getMajor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, UserIdNotFound, SQLException {
		return getStuTuple().getString("major");
	}
	
	public Date getEnterDate() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getStuTuple().getDate("enter_date");
	}
	
	public int getStudyYear() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getStuTuple().getInt("study_year");
	}
	
	public String getStudyType() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getStuTuple().getString("study_type");
	}

	public void setMajor(String major) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("student", "major", major, "id = "+this.id);
	}
	
	public void setEnterDate(Date date) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("student", "enter_date", date, "id = "+this.id);
	}
	
	public void setEnterDate(String date) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setEnterDate(Date.valueOf(date));
	}
	
	public void setStudyYear(int years) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("student", "study_year", years, "id = "+this.id);
	}
	
	public void setStudyType(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		if (type.equals("本科") || type.equals("研究生") || type.equals("硕士") || type.equals("博士"))
			DB.setPara("student", "study_type", type, "id = "+this.id);
		else
			throw new Exception("ErrorStudentType");
	}
	
	public List<Course> getCourses() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		List<Course> courses = new LinkedList<Course>();
		RowSet rs = DB.select("course_id", "studyCourse", "student_id = "+this.id);
		while (rs.next()) {
			courses.add(new Course(rs.getInt("course_id")));
		}
		return courses;
	}
	
	public void selectCourse(Course course) throws Exception {
		 RowSet rs = DB.select("*", "selectCourse", "course_id = "+course.courseId+" && student_id = "+this.id);
		 if (rs.next())
			 throw new Exception("ThisCourseHasBeenSelected");
		 Connection conn = DB.getConnection();
		 PreparedStatement pStmt = conn.prepareStatement("insert into selectCourse(course_id,student_id) values(?,?)");
		 pStmt.setInt(1, course.courseId);
		 pStmt.setInt(2, this.id);
		 pStmt.executeUpdate();
		 conn.close();
	}
	
	public void unSelectCourse(Course course) throws Exception {
		DB.delete("selectCourse", "course_id = "+course.courseId);
	}
	
	public void cancelSelect(Course course) throws Exception {
		RowSet rs = DB.select("*", "selectCourse", "course_id = "+course.courseId+" && student_id = "+this.id);
		if (rs.next())
			throw new Exception("ThisCourseHasBeenSelected");
		DB.delete("selectCourse", "course_id = "+course.courseId+" && student_id = "+this.id);
	}

	public int getScore(Course course) throws Exception {
		RowSet rs = DB.select("score", "studyCourse", "course_id = "+course.courseId+" && student_id = "+this.id);
		if (rs.next())
			return rs.getInt("score");
		else
			throw new Exception("ScoreNotFound");
	}
}
