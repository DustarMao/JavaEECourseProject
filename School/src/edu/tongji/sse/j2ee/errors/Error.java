package edu.tongji.sse.j2ee.errors;

@SuppressWarnings("serial")
public class Error extends Throwable {
	public final String infor;

	public Error() {
		infor = "Unknow Error";
	}
	
	public Error(String s) {
		infor = s;
	}
}
