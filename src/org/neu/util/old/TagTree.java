package org.neu.util.old;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.neu.util.io.FileUtils;


public class TagTree extends Trie<String, String> {

	private final static Logger log = LogManager.getLogger(TagTree.class);

	private static TagTree instence;

	private TagTree() {

	}

	public static synchronized TagTree getInstence() {
		if (instence == null) {
			try {
				String file = TagTree.class.getClassLoader().getResource("tag.txt").getPath();
				log.info("load tag file : " + file);
				instence = loadTagTree(file);
			} catch (IOException e) {
				// e.printStackTrace();
				log.error("load tag file error : " + e.getMessage(), e);
			}
		}
		return instence;
	}

	public String getDefaultTag(String tag) {
		Node<String, String> node = search(tag.toLowerCase());
		if (node != null && node.getContent() != null) {
			return node.getContent().split("\\*")[0];
		} else {
			return tag;
		}
	}

	public List<String> getParantTags(String tag) {
		tag = tag.toLowerCase().trim();
		List<String> tags = new ArrayList<String>();
		Node<String, String> nodes = search(tag);
		if (nodes != null) {
			for (Node<String, String> node : search(tag).getPathNodes()) {
				tags.add(node.getContent().split("\\*")[0].trim());
			}
		}
		return tags;
	}

	public void putIntoNodeKeyMap(String content, String[] ks) {
		for (String tag : content.split("\\*")) {
			super.putIntoNodeKeyMap(tag.trim().toLowerCase(), ks);
		}
	}

	public static TagTree loadTagTree(String file) throws IOException {
		List<String> lines = FileUtils.readLines(file, "UTF-8");

		TagTree taqgTree = new TagTree();
		for (String line : lines) {
			if (line.indexOf('\t') > 0) {
				String[] parts = line.split("\\t");
				taqgTree.insert(parts[0].split("\\."), parts[1]);
			}
		}
		// taqgTree.print();
		return taqgTree;
	}
	
	public Set<String> findAllTags() {
		Set<String> tags = new HashSet<String>();
		Set<String> tagGroups = findAllNodeContent();
		for(String tagGroup: tagGroups) {
			for(String tag: tagGroup.split("\\*")) {
				tags.add(tag.trim());
			}
		}
		return tags;
	}
	
	public Set<String> findNorepeat() {
		Set<String> tags = new HashSet<String>();
		Set<String> tagGroups = findAllNodeContent();
		for(String tagGroup: tagGroups) {
			tags.add(tagGroup.split("\\*")[0]);
		}
		return tags;
	}
	public static void main(String[] args) {

		TagTree tagTree = TagTree.getInstence();
		System.out.println(tagTree.findAllNodeContent());
		System.out.println(tagTree.findAllTags());
	}

}
