package de.keygalp.engine.utils;

public class Utils {

	public static float map(float x, float a, float b, float c, float d) {
		if (x > b)
			x = b;
		if (x < a)
			x = a;
		float y = (x - a) / (b - a) * (d - c) + c;

		return y;
	}

	public static float lerp(float v0, float v1, float t) {
		return (1 - t) * v0 + t * v1;
	}
	
	public static double lerp(double v0, double v1, double t) {
		return (1 - t) * v0 + t * v1;
	}

	public static float distSq(float x1, int y1, int x2, int y2) {
		float dx = x1 - x2;
		float dy = y1 - y2;
		return (dx * dx + dy * dy);
	}

	public static double distSq(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return (dx * dx + dy * dy);
	}
}
