package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.json.*;
import edu.tongji.sse.j2ee.school.Notice;

/**
 * Servlet implementation class GetNotices
 */
@WebServlet("/GetNotices")
public class GetNotices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNotices() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JsonList notices = new JsonList();
			for (Notice n : Notice.getNotices()) {
				JsonMap notice = new JsonMap();
				notice.put("nID", new JsonVal<Integer>(n.id));
				notice.put("title", new JsonVal<String>(n.getTitle()));
				notice.put("href", new JsonVal<String>(n.getHref()));
				notices.add(notice);
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print((new JsonMap("notices",notices)).toJsonStr());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
