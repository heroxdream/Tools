package org.neu.util.collection;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ValueSumer<K, V> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map<K, V> map = new TreeMap<K, V>();

	public void put(K k, V num) {
		V sum = map.get(k);
		if (sum == null) {
			map.put(k, num);
		} else {
			map.put(k, add(sum,num));
		}
	}

	public V get(K k)  {
		return map.get(k);
//		if(sum instanceof Long){
//			return sum == null ? (V)new Long(0) : sum;
//		}else if(sum instanceof Integer){
//			return sum == null ? (V)new Integer(0) : sum;
//		}else if(sum instanceof Double){
//			return sum == null ? (V)new Double(0) : sum;
//		}else {
//			throw new IllegalArgumentException ("ValueSumer不支持这种类型！");
//		}
	}

	@SuppressWarnings("unchecked")
	private V add(V v1,V v2){
		if(v1 instanceof Long){
			Long l1=(Long)v1;
			Long l2=(Long)v2;
			Long l=l1+l2;
			return (V)l;
		}else if(v1 instanceof Integer){
			Integer d1=(Integer)v1;
			Integer d2=(Integer)v2;
			Integer sum=d1+d2;
			return (V)sum;
		}else if(v1 instanceof Double){
			Double d1=(Double)v1;
			Double d2=(Double)v2;
			Double sum=d1+d2;
			return (V)sum;
		}else {
			throw new IllegalArgumentException("ValueSumer不支持这种类型！");
		}
	}

	public List<K> hot(int n) {
		return MapUtils.sortByValue(map).subList(0, Math.min(map.size(), n));
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	public int size() {
		return map.size();
	}

	public Map<K, V> getMap() {
		return map;
	}
	public static void main(String [] args) throws Exception{
//		ValueSumer<String,Long> v=new ValueSumer<String,Long>();
//		v.put("v", 1L);
//		v.put("v", 2L);
//		v.put("v", 3L);
//		System.out.println(v.get("v1"));
		
		ValueSumer<String,Double> v1=new ValueSumer<String,Double>();
		v1.put("v", 1.1);
		v1.put("v", 2.2);
		v1.put("v", 3.3);
		System.out.println(v1.get("v1"));
//		
//		ValueSumer<String,Integer> v2=new ValueSumer<String,Integer>();
//		v2.put("v", 1);
//		v2.put("v", 4);
//		v2.put("v", 3);
//		System.out.println(v2.getMap());
	}
	
}
