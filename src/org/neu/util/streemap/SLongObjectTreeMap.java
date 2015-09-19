package org.neu.util.streemap;

import gnu.trove.set.hash.TLongHashSet;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;


public class SLongObjectTreeMap<T> {
	
	private long[] keys;
	
	private T[] values;

    public int size() {
	    return keys.length;
    }

    public boolean isEmpty() {
	    return keys.length == 0;
    }

    public boolean containsKey(long key) {
	    return Arrays.binarySearch(keys, key) >= 0;
    }

    public boolean containsValue(Object value) {
    	throw new IllegalStateException("containsValue(Object value) didn't implement..");
    }

	public T get(long key) {
	    int pos = Arrays.binarySearch(keys, key);
	    return pos >= 0 ?  values[pos] : null;
    }

	public T put(long key, T value) {
	    throw new IllegalStateException("Can't modify Static TreeMap..");
    }

    public T remove(Object key) {
		throw new IllegalStateException("Can't modify Static TreeMap..");
    }

    public void putAll(Map<? extends Long, ? extends T> m) {
		throw new IllegalStateException("Can't modify Static TreeMap..");
    }

    public void clear() {
		throw new IllegalStateException("Can't modify Static TreeMap..");
    }

    public TLongHashSet keySet() {
	    return new TLongHashSet(keys);
    }

    public T[] values() {
	    return values;
    }

    public Set<Map.Entry<Long, T>> entrySet() {
		throw new IllegalStateException("entrySet() didn't implement..");
    }
	
	

}
