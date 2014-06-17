package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class TeachingCourse
 */
@WebServlet("/StudingCourse")
public class StudingCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudingCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try {
			Student student = ((User)request.getSession().getAttribute("user")).toStudent();
			float currentTerm = School.getCurrentSeason();
			List<Course> courses = student.getCourses();
			JsonList jl = new JsonList();
			for (Course c : courses) {
				if (c.getSeason() == currentTerm && !c.isApplying()) {
					JsonMap jm = new JsonMap();
					jm.put("cID", new JsonVal<Integer>(c.courseId));
					jm.put("name", new JsonVal<String>(c.getName()));
					jm.put("time", new JsonVal<String>(c.getWeekday()+" "+c.getTime().toString()));
					jm.put("place", new JsonVal<String>(c.getPlace()));
					jl.add(jm);
				}
			}
			JsonMap json = new JsonMap();
			json.put("term", new JsonVal<String>(School.toLine(currentTerm)));
			json.put("courses", jl);
			response.getWriter().print((new JsonMap("currentTerm",json)).toJsonStr());
		} catch ( UserIdNotFound | Exception e) {
			e.printStackTrace();
		}
	}

}
