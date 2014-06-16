package edu.tongji.sse.j2ee.school;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import javax.sql.RowSet;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;

public class Teacher extends User {

	Teacher(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		super(id);
	}

	protected RowSet getTeaTuple() throws UserIdNotFound, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		RowSet rs = DB.select("*", "teacher", "id ="+this.id);
		if (rs.next())
			return rs;
		else
			throw new UserIdNotFound();
	}
	
	public String getTeacherType() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTeaTuple().getString("teacher_type");
	}
	
	public Date getStartDate() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTeaTuple().getDate("start_date");
	}
	
	public int getSalary() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserIdNotFound {
		return getTeaTuple().getInt("salary");
	}
	
	public void setTeacherType(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("teacher", "teacher_type", type, "id = "+this.id);
	}
	
	public void setStratDate(Date date) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("teacher", "start_date", date, "id = "+this.id);
	}
	
	public void setStratDate(String date) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setStratDate(Date.valueOf(date));
	}
	
	public void setSalary(int salary) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB.setPara("teacher", "salary", salary, "id = "+this.id);
	}
	
	public List<Course> getCourses() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		List<Course> courses = new LinkedList<Course>();
		RowSet rs = DB.select("course_id", "course", "teacher = "+this.id);
		while (rs.next()) {
			courses.add(new Course(rs.getInt("course_id")));
		}
		return courses;
	}
	
	public Course applyCourse(String name, int weekday, Time time, String place, float grade) throws Exception {
		return Course.newCourse(Course.getNewId(), name, this, School.getCurrentSeason(), place, weekday, time, grade, true);
	}

	public Exam setExam(Course course, String place, Date date, Time time) throws Exception {
		if (course.getTeacher().equals(this)) {
			return Exam.addExam(course, place, date, time);
		}
		throw new Exception("IllegalCourseOfThisTeacher");
	}

}
