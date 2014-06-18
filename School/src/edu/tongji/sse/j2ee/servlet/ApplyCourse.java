package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.school.Course;
import edu.tongji.sse.j2ee.school.School;
import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class ApplyCourse
 */
@WebServlet("/ApplyCourse")
public class ApplyCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Course newCourse = Course.newCourse(
					Integer.parseInt(request.getParameter("cID")),
					request.getParameter("cName"),
					((User)request.getSession().getAttribute("user")).toTeacher(),
					School.currentSeason,
					request.getParameter("cPlace"),
					Integer.parseInt(request.getParameter("cWeekday")),
					Time.valueOf(request.getParameter("cTime")),
					Float.parseFloat(request.getParameter("cGrade")),
					false);
			newCourse.setSize(Integer.parseInt(request.getParameter("cSize")));
		} catch (Exception | UserIdNotFound e) {
			e.printStackTrace();
		}
	}

}
