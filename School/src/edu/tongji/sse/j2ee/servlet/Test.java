package edu.tongji.sse.j2ee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tongji.sse.j2ee.json.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter output = response.getWriter();
		JsonMap json = new JsonMap();
		json.put("name", new JsonVal<String>("MaoZili"));
		json.put("sex", new JsonVal<String>("Man"));
		json.put("age", new JsonVal<Integer>(20));
		JsonMap course[] = new JsonMap[3];
		course[0] = new JsonMap();
		course[0].put("name", new JsonVal<String>("JavaEE1"));
		course[0].put("time", new JsonVal<String>("Thur. 7,8"));
		course[0].put("place", new JsonVal<String>("Building Jishi, Room 530"));
		course[1] = new JsonMap();
		course[1].put("name", new JsonVal<String>("JavaEE2"));
		course[1].put("time", new JsonVal<String>("Thur. 7,8"));
		course[1].put("place", new JsonVal<String>("Building Jishi, Room 530"));
		course[2] = new JsonMap();
		course[2].put("name", new JsonVal<String>("JavaEE3"));
		course[2].put("time", new JsonVal<String>("Thur. 7,8"));
		course[2].put("place", new JsonVal<String>("Building Jishi, Room 530"));
		json.put("courses", new JsonList(course));
		output.print(json.toJsonStr());
	}

}
