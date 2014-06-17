package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.errors.UserIdNotFound;
import edu.tongji.sse.j2ee.json.*;
import edu.tongji.sse.j2ee.school.User;

/**
 * Servlet implementation class UserInfor
 */
@WebServlet("/UserInforJson")
public class UserInforJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInforJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		JsonMap userInfor = new JsonMap();
		try {
			userInfor.put("name", new JsonVal<String>(user.getName()));
			userInfor.put("id", new JsonVal<Integer>(user.id));
			userInfor.put("type", new JsonVal<String>(user.isAdmin()?"管理员":(user.isTeacher()?"教师":"学生")));
			userInfor.put("isTeacher", new JsonVal<Boolean>(user.isTeacher()));
			userInfor.put("isAdmin", new JsonVal<Boolean>(user.isAdmin()));
			userInfor.put("birthday", new JsonVal<String>(user.getBirthday().toString()));
			userInfor.put("birthplace", new JsonVal<String>(user.getBirthplace()));
			userInfor.put("sex", new JsonVal<Boolean>(user.isMale()));
			userInfor.put("rID", new JsonVal<String>(user.getResidentId()));
			userInfor.put("department", new JsonVal<String>(user.getDepartment()));
			userInfor.put("phone", new JsonVal<String>(user.getPhone()));
			userInfor.put("Email", new JsonVal<String>(user.getEmail()));
			userInfor.put("website", new JsonVal<String>(user.getWebsite()));
			userInfor.put("QQ", new JsonVal<String>(user.getQQ()));
			userInfor.put("address", new JsonVal<String>(user.getAddress()));
			if (user.isTeacher()) {
				userInfor.put("startDate", new JsonVal<String>(user.toTeacher().getStartDate().toString()));
				userInfor.put("teacherType", new JsonVal<String>(user.toTeacher().getTeacherType()));
				userInfor.put("salary", new JsonVal<Integer>(user.toTeacher().getSalary()));
			} else {
				userInfor.put("major", new JsonVal<String>(user.toStudent().getMajor()));
				userInfor.put("enterDate", new JsonVal<String>(user.toStudent().getEnterDate().toString()));
				userInfor.put("studyYear", new JsonVal<Integer>(user.toStudent().getStudyYear()));
				userInfor.put("studnetType", new JsonVal<String>(user.toStudent().getStudyType()));
			}
		} catch (Exception | UserIdNotFound e) {
			e.printStackTrace();
		}
		JsonMap outcome = new JsonMap();
		outcome.put("user", userInfor);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(outcome.toJsonStr());
	}

}
