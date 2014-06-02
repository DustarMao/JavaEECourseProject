package edu.tongji.sse.j2ee.bean;

public class Teacher extends User {
	private Course[] teachingCourse;
	private Course[] taughtCourse;
	
	public Teacher(User user) {
		super(user);
	}
	
	public Course[] getTeachingCourse() {
		// TODO
		return teachingCourse;
	}
	public Course[] getTaughtCourse() {
		// TODO
		return taughtCourse;
	}
	

}
