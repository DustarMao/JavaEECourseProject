package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class ChangeUserInfor
 */
@WebServlet("/ChangeUserInfor")
public class ChangeUserInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserInfor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		response.setCharacterEncoding("UTF-8");
		try {
			user.setName(request.getParameter("pName"));
			user.setResidentId(request.getParameter("pRID"));
			if (request.getParameter("pSex") == "ÄÐ") {
				user.setSex(true);
			} else {
				user.setSex(false);
			}
			user.setBirthday(Date.valueOf(request.getParameter("pBirthday")));
			user.setBirthplace(request.getParameter("pBirthplace"));
			user.setPhone(request.getParameter("pPhone"));
			user.setEmail(request.getParameter("pEmail"));
			user.setQQ(request.getParameter("pQQ"));
			user.setWebsite(request.getParameter("pWebsite"));
			user.setAddress(request.getParameter("pAddress"));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
