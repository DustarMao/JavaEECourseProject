package edu.tongji.sse.j2ee.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class Course {
	final int courseId;
	Course(int id) throws Exception {
		ResultSet rs = DB.select("*", "course", "course_id = "+id);
		if (rs.next())
			courseId = id;
		else
			throw new Exception("CourseNotFound");
	}
	
	protected ResultSet getTuple() throws Exception {
		ResultSet rs = DB.select("*", "course", "course_id = "+courseId);
		if (rs.next()) {
			return rs;
		}
		else {
			throw new Exception("CourseNotFound");
		}
	}
	
	// Getter
	public String getName() throws Exception {
		return getTuple().getString("name");
	}
	
	public Teacher getTeacher() throws Exception {
		return new Teacher(getTuple().getInt("teacher"));
	}
	
	public float getSeason() throws Exception {
		return getTuple().getFloat("season");
	}
	
	public String getPlace() throws Exception {
		return getTuple().getString("place");
	}
	
	public String getWeekday() throws Exception {
		switch (getTuple().getInt("weekday")) {
		case 1:
			return "Monday";
		case 2:
			return "Tuesday";
		case 3:
			return "Wednesday";
		case 4:
			return "Thursday";
		case 5:
			return "Friday";
		case 6:
			return "Saturday";
		case 0: case 7:
			return "Sunday";
		default:
			throw new Exception("IllegalWeekdayValue");	
		}
	}
	
	public Time getTime() throws Exception {
		return getTuple().getTime("time");
	}
	
	public float getGrade() throws Exception {
		return getTuple().getFloat("grade");
	}
	
	public int getSize() throws Exception {
		return getTuple().getInt("size");
	}
	
	public boolean isApplying() throws Exception {
		return getTuple().getBoolean("applying");
	}
	
	// Setter
	public void setName(String name) throws Exception {
		DB.setPara("course", "name", name, "course_id = "+courseId);
	}
	
	public void setTeacher(Teacher teacher) throws Exception {
		DB.setPara("course", "teacher", teacher.id, "course_id = "+courseId);
	}
	
	public void setSeason(float season) throws Exception {
		DB.setPara("course", "season", season, "course_id = "+courseId);
	}
	
	public void setPlace(String place) throws Exception {
		DB.setPara("course", "place", place, "course_id = "+courseId);
	}
	
	public void setWeekday(int weekday) throws Exception {
		DB.setPara("course", "weekday", weekday, "course_id = "+courseId);
	}
	
	public void setTime(Time time) throws Exception {
		DB.setPara("course", "time", time, "course_id = "+courseId);
	}
	
	public void setTime(String time) throws Exception {
		setTime(Time.valueOf(time));
	}
	
	public void setGrade(float grade) throws Exception {
		DB.setPara("course", "grade", grade, "course_id = "+courseId);
	}
	
	public void setSize(int size) throws Exception {
		DB.setPara("course", "size", size, "course_id = "+courseId);
	}
	
	public void setApplying(boolean isApplying) throws Exception {
		DB.setPara("course", "applying", isApplying, "course_id = "+courseId);
	}
	
	// Students
	public List<Student> getStudents() throws Exception{
		List<Student> stus = new LinkedList<Student>();
		ResultSet rs = DB.select("student_id", "studyCourse", "course_id = "+courseId);
		while (rs.next()) {
			stus.add(new Student(rs.getInt("student_id")));
		}
		return stus;
	}
	
	public void addStudent(Student nStu) throws Exception {
		Connection conn = DB.getConnection();
		if (getSize() > DB.select("*", "studyCourse", "course_id = "+courseId).getRow()) {
			PreparedStatement pStmt = conn.prepareStatement("insert into studyCourse(course_id,student_id) values(?,?)");
			pStmt.setInt(1, courseId);
			pStmt.setInt(2, nStu.id);
			pStmt.executeUpdate();
			conn.close();
		}
		else {
			throw new Exception("CourseOverflow");
		}
	}
	
	public void removeStudent(Student oStu) throws Exception {
		DB.delete("studyCourse", "student_id = "+oStu.id+" && course_id = "+courseId);
	}
	
	public int getStuScore(Student stu) throws Exception {
		ResultSet rs = DB.select("score", "studyCourse", "student_id = "+stu.id+" && course_id = "+courseId);
		if (rs.next()) {
			return rs.getInt("score");
		}
		else {
			throw new Exception("StudentNotFound");
		}
	}
	
	public void setStuScore(Student stu, int score) throws Exception {
		Connection conn = DB.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("update studyCourse set score = ? where course_id = ? && student_id = ?");
		pStmt.setInt(1, score);
		pStmt.setInt(2, courseId);
		pStmt.setInt(3, stu.id);
		pStmt.executeUpdate();
		conn.close();
	}

	// static
	public static int getNewId() throws Exception {
		ResultSet rs = DB.select("course_id", "course");
		int newId = 1;
		while (rs.next()) {
			if (rs.getInt("course_id") >= newId) {
				newId = rs.getInt("course_id")+1;
			}
		}
		return newId;
	}

	public static Course newCourse(
			int id,
			String name,
			Teacher teacher,
			float season,
			String place,
			int weekday,
			Time time,
			float grade,
			boolean isApplying) throws Exception{
		Connection conn = DB.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("insert into course(course_id,name,teacher,season,place,weekday,time,grade,applying) values(?,?,?,?,?,?,?,?,?)");
		pStmt.setInt(1, id);
		pStmt.setString(2, name);
		pStmt.setInt(3, teacher.id);
		pStmt.setFloat(4, season);
		pStmt.setString(5, place);
		pStmt.setInt(6, weekday);
		pStmt.setTime(7, time);
		pStmt.setFloat(8, grade);
		pStmt.setBoolean(9, isApplying);
		pStmt.executeUpdate();
		conn.close();
		return new Course(id);
	}

	public static void removeCourse(Course course) throws Exception {
		DB.delete("course", "course_id = "+course.courseId);
	}

	public static List<Course> getSelectedAvailable(Student stu) throws Exception {
		List<Course> courses = new LinkedList<Course>();
		ResultSet courseRs = DB.select("course_id", "course");
		while (courseRs.next()) {
			ResultSet studyRs = DB.select("*", "studyCourse", 
					"course_id = "+courseRs.getInt("course_id")+" && student_id = "+stu.id);
			if (!studyRs.next()) {
				courses.add(new Course(courseRs.getInt("course_id")));
			}
		}
		return courses;
	}

	public static List<Course> getApplyingCourses() throws Exception {
		List<Course> courses = new LinkedList<Course>();
		ResultSet rs = DB.select("course_id", "course", "applying = "+true);
		while (rs.next()) {
			courses.add(new Course(rs.getInt("course_id")));
		}
		return courses;
	}

}
