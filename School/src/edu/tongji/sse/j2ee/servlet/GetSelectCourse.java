package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.school.Course;
import edu.tongji.sse.j2ee.school.Student;
import edu.tongji.sse.j2ee.school.User;
import edu.tongji.sse.j2ee.json.*;

/**
 * Servlet implementation class GetSelectCourse
 */
@WebServlet("/GetSelectCourse")
public class GetSelectCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSelectCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Student student = ((User)request.getSession().getAttribute("user")).toStudent();
			List<Course> courses = Course.getSelectableCourses();
			JsonList coursesJson = new JsonList();
			for (Course c : courses) {
				JsonMap courseJson = new JsonMap();
				courseJson.put("cID", new JsonVal<Integer>(c.courseId));
				courseJson.put("name", new JsonVal<String>(c.getName()));
				courseJson.put("grade", new JsonVal<Float>(c.getGrade()));
				courseJson.put("place", new JsonVal<String>(c.getPlace()));
				courseJson.put("time", new JsonVal<String>(c.getWeekday()+" "+c.getTime()));
				courseJson.put("size", new JsonVal<Integer>(c.getSize()));
				courseJson.put("select", new JsonVal<Boolean>(c.isSelected(student)));
				coursesJson.add(courseJson);
			}
			JsonMap json = new JsonMap();
			json.put("courses", coursesJson);
			json.put("uID", new JsonVal<Integer>(student.id));
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toJsonStr());
		} catch (UserIdNotFound | Exception e) {
			e.printStackTrace();
		}
	}

}
