package org.neu.util.rand;

import gnu.trove.set.hash.TLongHashSet;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.neu.util.array.ArraySumUtil;

public class RandomUtils {

	private static Random rand = new Random();

	public static double[] randomZeroOneArray(int size) {
		double[] array = new double[size];
		IntStream.range(0, size).forEach(i -> array[i] = rand.nextDouble());
		return array;
	}

	public static double[] randomOneOneArray(int size) {
		double[] array = new double[size];
		IntStream.range(0, size).forEach(i -> array[i] = rand.nextDouble() - rand.nextDouble());
		return array;
	}

	public static double[] randomGaunextssianArray(int size) {
		double[] array = new double[size];
		IntStream.range(0, size).forEach(i -> array[i] = rand.nextGaussian());
		return array;
	}

	public static int[] randomIntArray(int max, int size) {
		int[] array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(max);
		}
		return array;
	}

    public static int[] randomIntRangeArray(int minInclude, int maxExclude, int size) {
        int[] array = randomIntArray(maxExclude - minInclude, size);
        for (int i = 0; i < array.length; i++) {
            array[i] += minInclude;
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
	
	public static int[] getIndexes(int size) {
		int[] indexes = new int[size];
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = i;
        }
		return indexes;
	}

	public static double[] randomSumOneArray(int size) {
		double[] array = new double[size];
		for (int i = 0; i < size; i++) {
			array[i] = Math.abs(rand.nextGaussian());
		}
		array = ArraySumUtil.normalize(array);
		return array;
	}

	public static double[] randomOnlyOneArray(int size) {
		double[] array = new double[size];
		array[rand.nextInt(size)] = 1;
		return array;
	}

    public static void main(String[] args) {
        int[] a = randomIntRangeArray(3, 7, 20);
        System.out.println(Arrays.toString(a));
    }

}
