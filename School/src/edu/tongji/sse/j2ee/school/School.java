package edu.tongji.sse.j2ee.school;

public final class School {
	private School() {}
	public static float currentSeason = 2014.2f;
	public static String toLine(float season) {
		int small = (int)(season*10) - ((int)season)*10;
		int num = (int)season;
		return ""+num+" Term"+small;
	}
}
