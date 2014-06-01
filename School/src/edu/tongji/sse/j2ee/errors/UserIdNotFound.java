package edu.tongji.sse.j2ee.errors;

@SuppressWarnings("serial")
public class UserIdNotFound extends Error {
	public final int uID;
	public UserIdNotFound() {
		super("User ID Not Found");
		uID = 0;
	}
	public UserIdNotFound(int id) {
		super("User ID "+id+" Not Found");
		uID = id;
	}
}
