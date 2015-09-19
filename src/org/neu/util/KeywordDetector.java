package org.neu.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.neu.util.collection.MapSet;
import org.neu.util.collection.ValueCounter;
import org.neu.util.io.FileUtils;

public class KeywordDetector {
	
	private static Logger log = LogManager.getLogger(KeywordDetector.class);
	
	private MapSet<Character, String> keywords = new MapSet<Character, String>();
	
	public KeywordDetector() {
		
	}

	public KeywordDetector(String keywordsFile) throws IOException {
		this(FileUtils.readLines(keywordsFile));
	}
	
	public KeywordDetector(Collection<String> keywords) {
		for (String keyword : keywords) {
			this.keywords.putOne(keyword.charAt(0), keyword);
		}
		log.info("keywords ready!");
	}

	public Map<String, Integer> frequency(String text){
		ValueCounter<String> counter = new ValueCounter<String>();
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if(keywords.containsKey(ch)) {
				for (String keyword : keywords.get(ch)) {
					if(text.indexOf(keyword, i) >= 0) {
						counter.put(keyword);
					}
				}
			}
		}
		return counter.getMap();
	}
	
	public boolean contains(String text) {
		return contains(text, keywords);
	}
	
	public boolean containsAll(String text) {
		return containsAll(text, keywords);
	}
	
	public static boolean contains(String text, MapSet<Character, String> keywords) {
		if(StringUtils.isNotBlank(text)) {
			for (int i = 0; i < text.length(); i++) {
				char ch = text.charAt(i);
				if(keywords.containsKey(ch)) {
					for (String keyword : keywords.get(ch)) {
						if(text.indexOf(keyword, i) >= 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean containsAll(String text, MapSet<Character, String> keywords) {
		if(StringUtils.isNotBlank(text)) {
			for (String keyword : keywords.allValues()) {
				if(!text.contains(keyword)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	public static boolean containsAll(String text, Collection<String> keywords, int fromIndex) {
		if(StringUtils.isNotBlank(text)) {
			for (String keyword : keywords) {
				if(text.indexOf(keyword, fromIndex) < 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
