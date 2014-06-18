package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.json.*;
import edu.tongji.sse.j2ee.school.Course;
import edu.tongji.sse.j2ee.school.School;
import edu.tongji.sse.j2ee.school.Student;
import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class CourseInforJson
 */
@WebServlet("/CourseInforJson")
public class CourseInforJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseInforJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String cID = request.getParameter("cID");
		try {
			Course course = new Course(Integer.parseInt(cID));
			JsonMap json = new JsonMap();
			JsonMap courseInfor = new JsonMap();
			courseInfor.put("cID",new JsonVal<Integer>(course.courseId));
			courseInfor.put("name", new JsonVal<String>(course.getName()));
			courseInfor.put("teacher", new JsonVal<String>(course.getTeacher().getName()+"("+course.getTeacher().id+")"));
			courseInfor.put("grade", new JsonVal<Float>(course.getGrade()));
			courseInfor.put("size", new JsonVal<Integer>(course.getSize()));
			JsonList students = new JsonList();
			for (Student s : course.getStudents()) {
				JsonMap student = new JsonMap();
				student.put("ID", new JsonVal<Integer>(s.id));
				student.put("name", new JsonVal<String>(s.getName()));
				student.put("sex", new JsonVal<String>(s.isMale()?"ÄÐ":"Å®"));
				student.put("phone", new JsonVal<String>(s.getPhone()));
				student.put("email", new JsonVal<String>(s.getEmail()));
				student.put("score", new JsonVal<Integer>(course.getStuScore(s)));
				students.add(student);
			}
			courseInfor.put("students",students);
			courseInfor.put("isCurrent", new JsonVal<Boolean>(course.getSeason()==School.currentSeason));
			JsonMap exam = new JsonMap();
			exam.put("place", new JsonVal<String>(course.getExam().getPlace()));
			exam.put("date", new JsonVal<String>(course.getExam().getDate().toString()));
			exam.put("time", new JsonVal<String>(course.getExam().getTime().toString()));
			courseInfor.put("exam", exam);
			json.put("CourseInfor", courseInfor);
			json.put("isTeacher", new JsonVal<Boolean>(((User)request.getSession().getAttribute("user")).isTeacher()));
			response.getWriter().print(json.toJsonStr());
		} catch (Exception | UserIdNotFound e) {
			e.printStackTrace();
		}
	}

}
