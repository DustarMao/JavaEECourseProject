package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.school.Course;
import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class ChangeScore
 */
@WebServlet("/ChangeScore")
public class ChangeScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Course course = new Course(Integer.parseInt(request.getParameter("cID")));
			course.setStuScore((new User(Integer.parseInt(request.getParameter("sID"))).toStudent()), Integer.parseInt(request.getParameter("nScore")));
		} catch (Exception | UserIdNotFound e) {
			e.printStackTrace();
		}
	}

}
