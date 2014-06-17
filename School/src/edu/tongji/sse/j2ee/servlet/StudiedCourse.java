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
 * Servlet implementation class TaughtCourse
 */
@WebServlet("/StudiedCourse")
public class StudiedCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudiedCourse() {
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
			List<Course> courses = student.getCourses();
			JsonList terms = new JsonList();
			courses:
			for (Course c : courses) {
				if (c.getSeason() != School.getCurrentSeason() && !c.isApplying()) {
					JsonMap jsonCourse = new JsonMap();
					jsonCourse.put("cID", new JsonVal<Integer>(c.courseId));
					jsonCourse.put("name", new JsonVal<String>(c.getName()));
					jsonCourse.put("time", new JsonVal<String>(c.getWeekday()+" "+c.getTime().toString()));
					jsonCourse.put("place", new JsonVal<String>(c.getPlace()));
					for (JsonObj term : terms) {
						JsonMap tc = (JsonMap) term;
						if (tc.get("term").equals(School.toLine(c.getSeason()))) {
							((JsonList)(tc.get("courses"))).add(jsonCourse);
							continue courses;
						}
					}
					JsonMap term = new JsonMap();
					term.put("term", new JsonVal<String>(School.toLine(c.getSeason())));
					term.put("courses", new JsonList(jsonCourse));
					terms.add(term);
				}
			}
			response.getWriter().print((new JsonMap("taughtCourse",terms)).toJsonStr());
		} catch (UserIdNotFound | Exception e) {
			e.printStackTrace();
		}
	}

}
