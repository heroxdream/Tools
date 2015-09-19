package org.neu.util.collection;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapSet<K, V> extends TreeMap<K, Set<V>>{

    private static final long serialVersionUID = 1460402813389744396L;
    
    private Comparator<V> setComparator;
    
    public MapSet() {
		super();
	}
    
    public MapSet(Comparator<V> setComparator) {
    	super();
		this.setComparator = setComparator;
	}
    
    public void filter(int minValue, int maxValue) {
		Set<K> set = new HashSet<K>(this.keySet());
		for(K k: set) {
			if(this.get(k).size() > maxValue || this.get(k).size() < minValue) {
				this.remove(k);
			}
		}
	}

    public V putOne(K key, V value) {
        Set<V> values = super.get(key);
        if(values == null) {
        	if(setComparator != null) {
        		values = new TreeSet<V>(setComparator);
        	} else {
        		values = new TreeSet<V>();
        	}
            super.put(key, values);
        }
        values.add(value);
        return value;
    }
    
    public List<K> sortBySize() {
        Random rand = new Random();
        TreeMap<String, K> map = new TreeMap<String, K>();
        for(K k: keySet()) {
            Set<V> values = get(k);
            map.put(values.size() + "" + rand.nextInt(10000000), k);
        }
        
        List<K> keys = new ArrayList<K>();
        for(String key: map.keySet()) {
            keys.add(map.get(key));
        }
        return keys;
    }
    
    public Set<V> allValues() {
        Set<V> values = new TreeSet<V>();
        for(Set<V> set: super.values()) {
            if(set != null) {
                values.addAll(set);
            }
        }
        return values;
    }
    
    public void merg(MapSet<K, V> mapSet) {
        for(K k: mapSet.keySet()) {
            for(V v: mapSet.get(k)) {
                putOne(k, v);
            }
        }
    }
    
    public Map<K, Integer> getCounterMap() {
    	Map<K, Integer> counter = new HashMap<K, Integer>();
    	for(K k: keySet()) {
    		counter.put(k, get(k).size());
    	}
    	return counter;
    }
    
    public Set<V> get(Object k) {
    	Set<V> set = super.get(k);
    	return set == null ? new HashSet<V>() : set;
    }

}
