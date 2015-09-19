package org.neu.util.array;

import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.list.array.TFloatArrayList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.array.TLongArrayList;

import java.util.Arrays;

public class ArrayUnionUtil {
	
	public static void main(String[] args) {
		int[] a1 = {10,12,13,14};
		int[] a2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		System.out.println(Arrays.toString(sortAndUnion(a1, a2)));
	}

	public static int[] sortAndintersect(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		return intersect(a1, a2);
	}
	
	public static long[] sortAndintersect(long[] a1, long[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		return intersect(a1, a2);
	}

	public static int[] intersect(int[] a1, int[] a2) {

		int point1 = 0;
		int point2 = 0;

		TIntArrayList list = new TIntArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				list.add(a1[point1]);
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				point1++;
			} else {
				point2++;
			}
		}
		return list.toArray();
	}
	
	public static double[] intersect(double[] a1, double[] a2) {

		int point1 = 0;
		int point2 = 0;

		TDoubleArrayList list = new TDoubleArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				list.add(a1[point1]);
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				point1++;
			} else {
				point2++;
			}
		}
		return list.toArray();
	}
	
	
	public static long[] intersect(long[] a1, long[] a2) {

		int point1 = 0;
		int point2 = 0;

		TLongArrayList list = new TLongArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				list.add(a1[point1]);
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				point1++;
			} else {
				point2++;
			}
		}
		return list.toArray();
	}

	public static int intersect(int[] a1, int length1, int[] a2, int length2, int[] res) {
		int point1 = 0;
		int point2 = 0;
		int pointr = 0;

		while (point1 < length1 && point2 < length2) {
			if (a1[point1] == a2[point2]) {
				res[pointr++] = a1[point1];
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				point1++;
			} else {
				point2++;
			}
		}
		return pointr;
	}

	public static int[] sortAndUnion(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		return union(a1, a2);
	}
	
	public static long[] sortAndUnion(long[] a1, long[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		return union(a1, a2);
	}

	public static int[] union(int[] a1, int[] a2) {

		int point1 = 0;
		int point2 = 0;

		TIntArrayList list = new TIntArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				list.add(a1[point1]);
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				list.add(a1[point1]);
				point1++;
			} else {
				list.add(a2[point2]);
				point2++;
			}
		}
		if (point1 < a1.length) {
			for (int i = point1; i < a1.length; i++) {
				list.add(a1[i]);
			}
		}
		if (point2 < a2.length) {
			for (int i = point2; i < a2.length; i++) {
				list.add(a2[i]);
			}
		}
		return list.toArray();
	}
	
	public static long[] union(long[] a1, long[] a2) {

		int point1 = 0;
		int point2 = 0;

		TLongArrayList list = new TLongArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				list.add(a1[point1]);
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				list.add(a1[point1]);
				point1++;
			} else {
				list.add(a2[point2]);
				point2++;
			}
		}
		if (point1 < a1.length) {
			for (int i = point1; i < a1.length; i++) {
				list.add(a1[i]);
			}
		}
		if (point2 < a2.length) {
			for (int i = point2; i < a2.length; i++) {
				list.add(a2[i]);
			}
		}
		return list.toArray();
	}


	public static int[] sortAndMinus(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);
		return minus(a1, a2);
	}

	public static int[] minus(int[] a1, int[] a2) {

		int point1 = 0;
		int point2 = 0;

		TIntArrayList list = new TIntArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				list.add(a1[point1]);
				point1++;
			} else {
				point2++;
			}
		}
		if (point1 < a1.length) {
			for (int i = point1; i < a1.length; i++) {
				list.add(a1[i]);
			}
		}
		return list.toArray();
	}
	
	public static long[] minus(long[] a1, long[] a2) {

		int point1 = 0;
		int point2 = 0;

		TLongArrayList list = new TLongArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				list.add(a1[point1]);
				point1++;
			} else {
				point2++;
			}
		}
		if (point1 < a1.length) {
			for (int i = point1; i < a1.length; i++) {
				list.add(a1[i]);
			}
		}
		return list.toArray();
	}

	public static float[] minus(float[] a1, float[] a2) {

		int point1 = 0;
		int point2 = 0;

		// TIntArrayList list = new TIntArrayList();
		TFloatArrayList list = new TFloatArrayList();
		while (point1 < a1.length && point2 < a2.length) {
			if (a1[point1] == a2[point2]) {
				point1++;
				point2++;
			} else if (a1[point1] < a2[point2]) {
				list.add(a1[point1]);
				point1++;
			} else {
				point2++;
			}
		}
		if (point1 < a1.length) {
			for (int i = point1; i < a1.length; i++) {
				list.add(a1[i]);
			}
		}
		return list.toArray();
	}
}
