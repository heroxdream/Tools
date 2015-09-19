package org.neu.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StringIO {

	public static String formatText(String str, int length) {
		String result = str;
		int len = 0;
		for (char c : str.toCharArray()) {
			if (c >= 0 && c <= 127) {
				len++;
			} else {
				len += 2;
			}
		}
		for (int i = 0; i < Math.max(0, length - len); i++) {
			result += "_";
		}
		return result;
	}

	public static String readFully(String fileName) throws IOException {
		return readFully(new File(fileName));
	}

	public static String readFully(File file) throws IOException {
		return readFully(new FileReader(file));
	}

	public static String readFully(URL url) throws IOException {
		return readFully(url.openStream());
	}

	public static String readFully(InputStream stream) throws IOException {
		return readFully(new InputStreamReader(stream));
	}

	public static String readFully(Reader reader) throws IOException {
		char[] arr = new char[8 * 1024]; // 8K at a time
		StringBuffer buf = new StringBuffer();
		int numChars;
		while ((numChars = reader.read(arr, 0, arr.length)) > 0) {
			buf.append(arr, 0, numChars);
		}
		return buf.toString();
	}

	public static List<Long> readLongs(String file) throws IOException {
		String line = null;
		List<Long> lines = new ArrayList<Long>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		while ((line = reader.readLine()) != null) {
			lines.add(Long.parseLong(line));
		}
		return lines;
	}

	public static List<Integer> readInts(String file) throws IOException {
		String line = null;
		List<Integer> lines = new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		while ((line = reader.readLine()) != null) {
			lines.add(Integer.parseInt(line));
		}
		return lines;
	}

}
