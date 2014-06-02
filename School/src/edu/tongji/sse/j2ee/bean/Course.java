package edu.tongji.sse.j2ee.bean;

public class Course {
	private static String currentPeroid;
	public static String getCurrentPeroid() {
		// TODO
		return currentPeroid;
	}
	public static void setCurrentPeroid(String currentPeroid) {
		Course.currentPeroid = currentPeroid;
	}
	private int cID;
	private String name;
	private String peroid;
	private String time;
	private String place;
	private Teacher teacher;
	private Student[] students;
	
	public Course(int ID) {
		cID = ID;
	}
	
	public String getTime() {
		// TODO
		return time;
	}
	public void setTime(String time) {
		// TODO
		this.time = time;
	}
	public String getPlace() {
		// TODO
		return place;
	}
	public void setPlace(String place) {
		// TODO
		this.place = place;
	}
	public Teacher getTeacher() {
		// TODO
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		// TODO
		this.teacher = teacher;
	}
	public int getcID() {
		// TODO
		return cID;
	}
	public String getName() {
		// TODO
		return name;
	}
	public String getPeroid() {
		// TODO
		return peroid;
	}
	public Student[] getStudents() {
		// TODO
		return students;
	}
	
}
