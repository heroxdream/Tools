package org.neu.util;

public class NumberUtils {
	
	public static int parseInt(String str) {
		return parseInt(str, 0);
	}
	
	public static int parseInt(String str, int defaultValue) {
		try {
	        return Integer.parseInt(str);
        } catch (Exception e) {
	        return defaultValue;
        }
	}

	public static long parseLong(String str) {
		try {
	        return Long.parseLong(str);
        } catch (Exception e) {
	        return 0;
        }
	}
	
}
