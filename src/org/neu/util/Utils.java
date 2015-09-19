package org.neu.util;

import java.util.Map;


public class Utils {

	

	public static long longValue(int i) {
		if (i >= 0) {
			return i;
		}
		return 0L - Integer.MIN_VALUE - Integer.MIN_VALUE + i;
	}

	public static void quietSleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ignore) {
		}
	}
	
	public static <K, V> String toString(Map<K, V> map) {
		StringBuffer sb  = new StringBuffer("[");
		for (K key : map.keySet()) {
			sb.append(key + ":" + map.get(key));
			sb.append(",");
		}
		if(sb.length() > 1) {
			sb.replace(sb.length()-1, sb.length(), "]");
		}
		return sb.length() == 1 ? "" : sb.toString();
	}
	
	public static void main(String[] args) {
		// long l = 1855327087L;
		// int i = (int)l;
		// System.out.println(i);
//		System.out.println(Integer.MIN_VALUE);
		// System.out.println(longValue(i));
		// int[] intersect = intersect(new int[]{1, 2, 5, 6}, new int[]{0, 2});
		// for(int i: intersect) {
		// System.out.print(i + ",");
		// }
	}

}
