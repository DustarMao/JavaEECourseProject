package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.tongji.sse.j2ee.bean.Course;
import edu.tongji.sse.j2ee.bean.Teacher;
import edu.tongji.sse.j2ee.bean.User;

/**
 * Servlet implementation class TeachingCourse
 */
@WebServlet("/TeachingCourse")
public class TeachingCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeachingCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null)
			return;
		if (((User)session.getAttribute("user")).isStude())
			return;
		Teacher user = new Teacher((User)session.getAttribute("user"));
		String json = new String();
		json += "{ \"teachingCourse\" : [";
		if (user.getTeachingCourse()!=null)
		for (Course c : user.getTeachingCourse()) {
			json += ("{\"cID\" : "+c.getcID()+","
					+ "\"name\" : \""+c.getName()+"\","
					+ "\"time\" : \""+c.getTime()+"\","
					+ "\"place\" : \""+c.getPlace()+"\"}");
		}
		json += "]};";
		response.getWriter().print(json);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
