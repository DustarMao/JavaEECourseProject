package edu.tongji.sse.j2ee.errors;

@SuppressWarnings("serial")
public class WrongPassword extends Error {

	public WrongPassword() {
		super("Password is Wrong");
	}

}
