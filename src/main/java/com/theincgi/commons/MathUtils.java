package com.theincgi.commons;

public class MathUtils {
	public static float distance( float x1, float y1, float x2, float y2 ) {
		float dx = x2-x1;
		float dy = y2-y1;
		return (float) Math.sqrt( dx*dx + dy*dy );
	}
}
