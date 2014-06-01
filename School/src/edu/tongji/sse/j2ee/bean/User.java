package edu.tongji.sse.j2ee.bean;

public class User {
	private int uID;
	private boolean stude;
	private boolean admin;
	private String name;
	
	public User() {}
	
	public User(String ID, String name) {
		// TODO
	}
	
	public User(int uID, boolean isStudent, boolean isAdmin, String name) {
		this.uID = uID;
		this.stude = isStudent;
		this.admin = isAdmin;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
