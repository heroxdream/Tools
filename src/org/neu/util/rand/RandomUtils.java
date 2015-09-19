package org.neu.util.rand;

import gnu.trove.set.hash.TLongHashSet;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class RandomUtils {

	private static Random rand = new Random();

	public static int[] randomIntArray(int max, int size) {
		int[] array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(max);
		}
		return array;
	}
	
	public static long[] randomLongArray(int max, int size) {
		int MAX = Integer.MAX_VALUE / 1024;
		long[] array = new long[(int) (size * 1.2)];
		for (int i = 0; i < array.length; i++) {
			long value = 0L;
			while((value = ((long)rand.nextInt(MAX)) * rand.nextInt(Integer.MAX_VALUE)) == 0) {
			}
			array[i] = value;
		}
		TLongHashSet set = new TLongHashSet(array);
		return ArrayUtils.subarray(set.toArray(), 0, size);
	}
	
	public static int[] getIndexs(int size) {
		int[] indexs = new int[size];
		for (int i = 0; i < indexs.length; i++) {
			indexs[i] = i;
        }
		return indexs;
	}

}
