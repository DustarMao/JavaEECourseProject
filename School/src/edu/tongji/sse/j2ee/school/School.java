package edu.tongji.sse.j2ee.school;

import javax.sql.RowSet;

public final class School {
	private School() {}
	
	public static float getCurrentSeason() throws Exception {
		RowSet rs = DB.select("*", "school");
		if (rs.next()) {
			return rs.getFloat("current_season");
		}
		else {
			throw new Exception("CanNotGetCurrentSeason");
		}
	}
	public static void setCurrentSeason(float cSeason) throws Exception {
		if (cSeason*10%10 == 1 || cSeason*10%10 == 2) {
			DB.update("school", "current_season = "+cSeason);
		}
	}
}
