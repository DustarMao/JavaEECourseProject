package edu.tongji.sse.j2ee.json;

import java.util.LinkedList;

public class JsonMap extends LinkedList<Entry<String, JsonObj>> implements JsonObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8923413639854431491L;

	public JsonMap() {
		super();
	}

	public void put(String key, JsonObj val) {
		if (get(key) == null) {
			add(new Entry<String,JsonObj>(key,val));
		}
		else {
			set(key,val);
		}
	}
	
	public JsonObj get(String key) {
		for (Entry<String, JsonObj> entry : this) {
			if (entry.key.equals(key))
				return entry.val;
		}
		return null;
	}
	
	public JsonObj set(String key, JsonObj val) {
		for (Entry<String, JsonObj> entry : this) {
			if (entry.key.equals(key)) {
				JsonObj oVal = entry.val;
				entry.val = val;
				return oVal;
			}
		}
		return null;
	}
	
	public String toJsonStr() {
		String str = "{";
		for (Entry<String, JsonObj> entry : this) {
			str += "\""+entry.key+"\":"+entry.val.toJsonStr()+",";
		}
		str = str.substring(0, str.length()-1);
		str += "}";
		return str;
	}
}
class Entry<K,V> {
	public final K key;
	public V val;
	public Entry(K key, V val) {
		this.key = key;
		this.val = val;
	}
}
