package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.school.User;
import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.errors.WrongPassword;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user;
		try {
			user = User.getUser(Integer.parseInt(request.getParameter("uID")),(String)request.getParameter("uPass"));
			request.getSession(true).setAttribute("user", user);
			response.getWriter().print("success");
		} catch (UserIdNotFound e) {
			response.getWriter().print("UserIdNotFound");
		} catch (WrongPassword e) {
			response.getWriter().print("WrongPassword");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
