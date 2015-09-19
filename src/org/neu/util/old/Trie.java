package org.neu.util.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie<K, V> {

	private Map<V, K[]> nodeKeyMap = new HashMap<V, K[]>();
	
	private Node<K, V> root = new Node<K, V>(null, null, null);

	class Node<K, V> {

		K charactor;

		V content;

		boolean end;

		List<Node<K, V>> children;
		
		Node<K, V> parent;
 
		public Node(Node<K, V> parent, K charactor, V content) {
			this.parent = parent;
			this.charactor = charactor;
			this.content = content;
			children = new ArrayList<Node<K, V>>();
		}

		public Node<K, V> getChild(K k) {
			if (children != null) {
				for (Node<K, V> child : children) {
					if (child.charactor.equals(k)) {
						return child;
					}
				}
			}
			return null;
		}
		
		public V getContent() {
			return content;
		}

		public String getPath() {
			String current = charactor == null ? "" : charactor.toString();
			return parent == null ? current : (parent.getPath() + "." +  current);
		}
		
		public List<Node<K, V>> getPathNodes() {
			if(parent != null) {
				List<Node<K, V>> path = parent.getPathNodes();
				path.add(this);
				return path;
			} else {
				return new ArrayList<Node<K, V>>();
			}
		}
		
		public void print(String indent) {
			System.out.println(indent + getPath() + "\t" + content);
	    	for(Node<K, V> child: children) {
	    		child.print("____" + indent);
	    	}
	    }
	}

	public void putIntoNodeKeyMap(V v, K[] ks) {
		nodeKeyMap.put(v, ks);
	}
	
	public void insert(K[] ks, V v) {

		if (search(ks) == null) {
			Node<K, V> current = root;
			for (K k : ks) {
				Node<K, V> child = current.getChild(k);
				if (child != null) {
					current = child;
				} else {
					current.children.add(new Node<K, V>(current, k, null));
					current = current.getChild(k);
				}
			}
			current.content = v;
			current.end = true;
			putIntoNodeKeyMap(v, ks);
		}
	}
	
	public Node<K, V> search(V v) {
		return search(nodeKeyMap.get(v));
	}

	public Node<K, V> search(K[] ks) {

		if(ks != null) {
			Node<K, V> current = root;
			for (K k : ks) {
				if (current.getChild(k) == null) {
					return null;
				} else {
					current = current.getChild(k);
				}
			}
			return current.end ? current : null;
		}
		return null;
	}
	
	public Set<V> findAllNodeContent() {
		return nodeKeyMap.keySet();
	}

	public void print() {
		root.print("");
	}
	
}
