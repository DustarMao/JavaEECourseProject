package edu.tongji.sse.j2ee.json;

import java.util.LinkedList;

public class JsonList extends LinkedList<JsonObj> implements JsonObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7345425872198402403L;
	
	public JsonList() {
		super();
	}
	
	public JsonList(JsonObj o) {
		super();
		this.add(o);
	}
	
	public JsonList(JsonObj[] array) {
		super();
		for (JsonObj jo : array) {
			this.add(jo);
		}
	}

	@Override
	public String toJsonStr() {
		String str = "[";
		for (JsonObj jo : this) {
			str += jo.toJsonStr()+",";
		}
		if (str.length()>1)
			str = str.substring(0, str.length()-1);
		str += "]";
		return str;
	}

}
