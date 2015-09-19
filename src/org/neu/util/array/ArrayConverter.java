package org.neu.util.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.neu.util.Utils;

public class ArrayConverter {

	public static long[] longArrayValue(int[] is) {
		long[] ls = new long[is.length];
		for (int i = 0; i < ls.length; i++) {
			ls[i] = Utils.longValue(is[i]);
		}
		return ls;
	}
	
	public static long[] toLongArray(String[] ss) {
		long[] ls = new long[ss.length];
		for (int i = 0; i < ls.length; i++) {
			try {
				ls[i] = Long.parseLong(ss[i]);
            } catch (Exception e) {
            }			
		}
		return ls;
    }
	
	public static List<Long> toLongList(String[] ss){
		List<Long> list = new ArrayList<Long>();
		for (String s : ss) {
			try {
				list.add(Long.parseLong(s));
			} catch (Exception e) {
			}
		}
		return list;
	}

	public static int[] intArrayValue(long[] ls) {
		int[] is = new int[ls.length];
		for (int i = 0; i < ls.length; i++) {
			is[i] = (int) ls[i];
		}
		return is;
	}

	public static double[] doubleArrayValue(float[] fs) {
		double[] ds = new double[fs.length];
		for (int i = 0; i < fs.length; i++) {
			ds[i] = fs[i];
		}
		return ds;
	}

	public static float[] floatArrayValue(double[] ds) {
		float[] fs = new float[ds.length];
		for (int i = 0; i < ds.length; i++) {
			fs[i] = (float) ds[i];
		}
		return fs;
	}

	public static int[] toArray(Collection<Integer> collection) {
		int[] array = new int[collection.size()];
		int count = 0;
		for (int value : collection) {
			array[count++] = value;
		}
		return array;
	}

	public static String[] toStringArray(Collection<String> collection) {
		String[] array = new String[collection.size()];
		int count = 0;
		for (String value : collection) {
			array[count++] = value;
		}
		return array;
	}

	public static long[] toLongArray(Collection<Long> collection) {
		long[] array = new long[collection.size()];
		int count = 0;
		for (long value : collection) {
			array[count++] = value;
		}
		return array;
	}

	@SuppressWarnings("rawtypes")
	public static float[] toFloatArray(Collection collection) {
		float[] array = new float[collection.size()];
		int count = 0;
		for (Object value : collection) {
			if (value instanceof Double) {
				array[count++] = ((Double) value).floatValue();
			}
			if (value instanceof Float) {
				array[count++] = (Float) value;
			}
		}
		return array;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> subList(Collection<? super T> list, int count) {
		return (List<T>) new ArrayList(list).subList(0, Math.min(count, list.size()));
	}

	public static List<Integer> toList(int[] ints) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i : ints) {
			list.add(i);
		}
		return list;
	}
	
	public static List<Long> toList(long[] longs){
		List<Long> list = new ArrayList<Long>();
		for (long i : longs) {
			list.add(i);
		}
		return list;
	}

	public static Set<Long> toLongSet(long[] ids) {
		Set<Long> set = new TreeSet<Long>();
		for (long id : ids) {
			set.add(id);
		}
		return set;
	}

	public static List<String> toUpperCase(List<String> lines) {
		List<String> uppers = new ArrayList<String>();
		for (String line : lines) {
			uppers.add(line.toUpperCase());
		}
		return uppers;
	}

	

}
