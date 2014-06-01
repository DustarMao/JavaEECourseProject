package edu.tongji.sse.j2ee.bean;

public class User {
	private int uID;
	private boolean stude;
	private boolean admin;
	
	public User() {}
	
	public User(int uID, boolean isStudent, boolean isAdmin) {
		this.uID = uID;
		this.stude = isStudent;
		this.admin = isAdmin;
	}
	
	public int getuID() {
		return uID;
	}

	public boolean isStude() {
		return stude;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
