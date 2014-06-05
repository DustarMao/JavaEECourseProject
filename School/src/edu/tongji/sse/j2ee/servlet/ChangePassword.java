package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.bean.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		response.setCharacterEncoding("UTF-8");
		if (user==null) {
			response.getWriter().print("用户不存在");
			return;
		}
		if (user.isPassword(request.getParameter("oPass"))) {
			if (request.getParameter("nPass")=="") {
				response.getWriter().print("输入新密码");
				return;
			}
			if (user.setPassword(request.getParameter("nPass"), request.getParameter("oPass"))) {
				response.getWriter().print("密码修改成功");
				return;
			}
			else {
				response.getWriter().print("密码错误");
				return;
			}
		}
		else {
			response.getWriter().print("密码错误");
			return;
		}
	}

}
