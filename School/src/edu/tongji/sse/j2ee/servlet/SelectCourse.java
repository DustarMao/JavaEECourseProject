package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.school.Course;
import edu.tongji.sse.j2ee.school.Student;
import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class SelectCourse
 */
@WebServlet("/SelectCourse")
public class SelectCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Student stu = ((User)request.getSession().getAttribute("user")).toStudent();
			Course cou = new Course(Integer.parseInt(request.getParameter("cID")));
			boolean selected = Boolean.parseBoolean(request.getParameter("selected"));
			if (selected)
				stu.unSelectCourse(cou);
			else
				stu.selectCourse(cou);
		} catch (Exception | UserIdNotFound e) {
			e.printStackTrace();
		}
	}

}
