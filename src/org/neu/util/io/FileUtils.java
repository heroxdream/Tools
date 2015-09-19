package org.neu.util.io;

import gnu.trove.list.TIntList;
import gnu.trove.list.TLongList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.set.TIntSet;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TIntHashSet;
import gnu.trove.set.hash.TLongHashSet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.neu.util.Utils;

public class FileUtils {
	private final static Logger log = LogManager.getLogger(Utils.class);

	public static Object readObject(String file) throws IOException, ClassNotFoundException {
		log.info("read object start: " + file);

		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file), 1024 * 1024 * 1));
		Object obj = ois.readObject();
		ois.close();

		log.info("read object finished..");

		return obj;
	}

	public static boolean deleteFileIfExsits(String file) {
		File f = new File(file);
		return f.exists() ? f.delete() : true;
	}

	public static void replaceFile(String bakFile, String toFile, String newFile) {
		deleteFileIfExsits(bakFile);
		renameFile(toFile, bakFile);
		renameFile(newFile, toFile);
	}

	public static boolean renameFile(String srcFile, String destFile) {
		File src = new File(srcFile);
		return src.renameTo(new File(destFile));
	}

	public static String readFileAsString(String file) {
		return readFileAsString(file, "UTF-8");
	}

	public static String readFileAsString(String file, String charSet) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet), 1024 * 1024 * 4);
			String text = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				text += line + "\n";
			}
			return text;
		} catch (Exception e) {
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static LinkedHashSet<String> readWords(String file) throws Exception {
		File fileObj = new File(file);
		if (fileObj.isDirectory()) {
			return readWordsFromFolder(fileObj);
		} else {
			return readWordsFromFile(fileObj);
		}
	}

	public static LinkedHashSet<String> readWordsFromFile(File file) throws Exception {
		LinkedHashSet<String> words = new LinkedHashSet<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024 * 1024 * 4);
		String line = "";
		while ((line = reader.readLine()) != null) {
			words.add(line);
		}
		reader.close();
		return words;
	}

	public static LinkedHashSet<String> readWordsFromFolder(File file) throws Exception {
		LinkedHashSet<String> words = new LinkedHashSet<String>();
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				words.addAll(readWordsFromFolder(subFile));
			}
		} else {
			words.add(file.getName());
		}
		return words;
	}

	public static LinkedHashSet<String> readWordsFromThisFolder(File file) throws Exception {
		LinkedHashSet<String> words = new LinkedHashSet<String>();
		for (File subFile : file.listFiles()) {
			if (subFile.isFile())
				words.add(subFile.getName());
		}
		return words;
	}

	public static Set<File> readFolderSet(File folder) {
		LinkedHashSet<File> folders = new LinkedHashSet<File>();
		if (folder.isDirectory()) {
			folders.add(folder);
			for (File subFile : folder.listFiles()) {
				folders.addAll(readFolderSet(subFile));
			}
		}
		return folders;
	}

	public static List<Long> readLongs(String file) throws IOException {
		String line = null;
		List<Long> lines = new ArrayList<Long>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024 * 1024 * 4);
		while ((line = reader.readLine()) != null) {
			lines.add(Long.parseLong(line));
		}
		reader.close();
		return lines;
	}

	public static List<Integer> readInts(String file) throws IOException {
		String line = null;
		List<Integer> lines = new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024 * 1024 * 4);
		while ((line = reader.readLine()) != null) {
			lines.add(Integer.parseInt(line));
		}
		reader.close();
		return lines;
	}

	public static TIntList readTIntList(String file) throws IOException {
		String line = null;
		TIntList lines = new TIntArrayList();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024 * 1024 * 4);
		while ((line = reader.readLine()) != null) {
			lines.add(Integer.parseInt(line));
		}
		reader.close();
		return lines;
	}

	public static TIntSet readTIntSet(String file) throws IOException {
		String line = null;
		TIntSet lines = new TIntHashSet();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024 * 1024 * 4);
		while ((line = reader.readLine()) != null) {
			lines.add(Integer.parseInt(line));
		}
		reader.close();
		return lines;
	}

	public static TLongList readTLongList(String file) throws IOException {
		String line = null;
		TLongList lines = new TLongArrayList();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024 * 1024 * 4);
		while ((line = reader.readLine()) != null) {
			lines.add(Integer.parseInt(line));
		}
		reader.close();
		return lines;
	}

	public static TLongSet readTLongSet(String file) throws IOException {
		String line = null;
		TLongSet lines = new TLongHashSet();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024 * 1024 * 4);
		while ((line = reader.readLine()) != null) {
			lines.add(Long.parseLong(line));
		}
		reader.close();
		return lines;
	}

	public static void writeIntArray(String file, int[] intArray) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)), 1024 * 1024 * 4);
		for (int val : intArray) {
			writer.append(Integer.toString(val));
			writer.append("\n");
		}

		writer.close();
	}

	public static void writeLongArray(String file, long[] longArray) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)), 1024 * 1024 * 4);
		for (long val : longArray) {
			writer.append(Long.toString(val));
			writer.append("\n");
		}

		writer.close();
	}

	public static List<String> readLines(String file) throws IOException {
		String line = null;
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} finally {
			reader.close();
		}
		return lines;
	}

	public static List<String> readLines(String file, String charset) throws IOException {
		String line = null;
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), charset));
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} finally {
			reader.close();
		}
		return lines;
	}
	
	public static Set<String> readLinesToSet(String file) throws IOException{
		return readLinesToSet(file, null);
	}
	
	public static Set<String> readLinesToSet(String file, String charset) throws IOException{
		String line = null;
		Set<String> lines = new HashSet<String>();
		BufferedReader reader = null;
		if(charset != null) {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), charset));
		} else {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
		}
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} finally {
			reader.close();
		}
		return lines;
	}

	/**
	 * 获取文件夹下所有制定后缀名的文件
	 * @param rootDir
	 * @param extentions	后缀名列表
	 * @return
	 */
	public static List<File> listFilesDeep(String rootDir, String[] extentions) {
		List<File> list = new ArrayList<File>();
		doListFiles(new File(rootDir), extentions, list);
		return list;
	}

	private static void doListFiles(File currentDir, String[] extentions, List<File> list) {
		if (currentDir.isDirectory()) {
			File[] files = currentDir.listFiles();
			for (File currentFile : files) {
				if (currentFile.isDirectory()) {
					doListFiles(currentFile, extentions, list);
				} else {
					String name = currentFile.getName();
					for (String extention : extentions) {
						if (name.endsWith(extention)) {
							list.add(currentFile);
						}
					}
				}
			}
		}
	}

	public static void writeLines(String file, Collection<String> lines) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file)), "UTF-8"));
		try {
			for (String line : lines) {
				writer.append(line);
				writer.append("\n");
			}
		} finally {
			try {
				writer.close();
			} catch (IOException ignore) {
			}
		}
	}

	public static void appendLines(String file, Collection<String> lines) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		try {
			for (String line : lines) {
				writer.append(line);
				writer.append("\r\n");
			}
		} finally {
			try {
				writer.close();
			} catch (IOException ignore) {
			}
		}
	}

	/**
	 * 如果不存在就创建文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean createEmptyFileIfNotExist(String file) throws IOException {
		File f = new File(file);
		if (!f.exists()) {
			return f.createNewFile();
		} else {
			return true;
		}

	}

	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFile(String srcFile, String destFile) throws IOException {
		org.apache.commons.io.FileUtils.copyFile(new File(srcFile), new File(destFile));
	}

	/**
	 * 复制文件到指定目录
	 * 
	 * @param srcFile
	 * @param destDir
	 * @throws IOException
	 */
	public static void copyFileToDir(String srcFile, String destDir) throws IOException {
		org.apache.commons.io.FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isFileExist(String file) {
		File f = new File(file);
		return f.exists();
	}

	public static String getExtension(File file) {
		int index = file.getName().lastIndexOf('.');
		return index < 0 ? "" : file.getName().substring(index + 1);
	}

	public static String getNameWithoutExtension(File file) {
		int index = file.getName().lastIndexOf('.');
		return index < 0 ? file.getName() : file.getName().substring(0, index);
	}

	public static boolean mkdirs(String dir) {
		return new File(dir).mkdirs();
	}
}
