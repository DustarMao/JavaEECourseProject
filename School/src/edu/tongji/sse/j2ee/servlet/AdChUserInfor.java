package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class AdChUserInfor
 */
@WebServlet("/AdChUserInfor")
public class AdChUserInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdChUserInfor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User(Integer.parseInt(request.getParameter("pID")));
			String type = request.getParameter("pType");
			user.setDepartment(request.getParameter("pDepart"));
			if (type.equals("Admin"))
				user.setAdmin(true);
			else
				user.setAdmin(false);
			if (type.equals("Tea")) {
				user.setTeacher(true);
				user.toTeacher().setTeacherType(request.getParameter("tTeacherType"));
			} else {
				user.setTeacher(false);
				user.toStudent().setStudyType(request.getParameter("sStudyType"));
				user.toStudent().setStudyYear(Integer.parseInt(request.getParameter("sStudyYear")));
				user.toStudent().setMajor(request.getParameter("sMajor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (UserIdNotFound e) {
			e.printStackTrace();
		}
	}

}
