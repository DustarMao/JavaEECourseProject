package edu.tongji.sse.j2ee.bean;

public class Student extends User {
	private Course[] studingCourse;
	private Course[] studiedCourse;
	
	public Course[] getStudingCourse() {
		return studingCourse;
	}

	public void setStudingCourse(Course[] studingCourse) {
		this.studingCourse = studingCourse;
	}

	public Course[] getStudiedCourse() {
		return studiedCourse;
	}

	public void setStudiedCourse(Course[] studiedCourse) {
		this.studiedCourse = studiedCourse;
	}

	public Student() {
		super();
	}
	
}
