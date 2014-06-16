package edu.tongji.sse.j2ee.json;

public class JsonVal<T> implements JsonObj {

	public T val;
	
	public JsonVal(T val) {
		this.val = val;
	}
	
	@Override
	public String toJsonStr() {
		if (val == null)
			return "null";
		if (val instanceof String)
			return "\""+val+"\"";
		else
			return val.toString();
	}

}
