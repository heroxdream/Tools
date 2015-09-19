package org.neu.util.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextFileUtils {
	
	 private final static Logger log = LogManager.getLogger(TextFileUtils.class);
	 
	 public static Object readObject(String file) throws IOException, ClassNotFoundException {
            log.info("read object start: " + file);
            
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file), 1024 * 1024 * 1));
            Object obj = ois.readObject();
            ois.close();
            
            log.info("read object finished..");
            
            return obj;
	    }
	 
	public static boolean deleteFileIfExsits(String file){
		File f = new File(file);
		return f.exists() ? f.delete() : true;
	}
	
	public static void replaceFile(String bakFile, String toFile, String newFile){
    	deleteFileIfExsits(bakFile);
    	renameFile(toFile, bakFile);
    	renameFile(newFile, toFile);
    }
	
  	public static boolean renameFile(String srcFile, String destFile){
    	File src = new File(srcFile);
    	return src.renameTo(new File(destFile));
    }
	
	public static String readFileAsString(String file) {
		return readFileAsString(file, "UTF-8");
	}
	
	public static String readFileAsString(String file, String charSet) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet), 1024*1024*4);
			String text = "";
			String line = "";
			while((line = reader.readLine()) != null) {
				text += line + "\n";
			}
			return text;
		} catch (Exception e) {
			return null;
		} finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
	
	
	
	public static String readInputStreamAsString(InputStream in, String charSet) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, charSet), 1024 * 1024 * 1);
			StringBuilder text = new StringBuilder();
			String line = "";
			while((line = reader.readLine()) != null) {
				text.append(line);
				text.append("\n");
			}
			return text.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		} finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static byte[] inputStream2Byte(InputStream in) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024 * 1024 * 16];
		int count = -1;
		while ((count = in.read(data, 0, 1024 * 1024 * 16)) != -1) {
			outStream.write(data, 0, count);
		}
		data = null;
		byte[] bytes = outStream.toByteArray();
		outStream.close();
		return bytes;
	}
	
	public static LinkedHashSet<String> readWords(String file) throws Exception {
		File fileObj = new File(file);
		if(fileObj.isDirectory()) {
			return readWordsFromFolder(fileObj);
		} else {
			return readWordsFromFile(fileObj);
		}
	}
	
	public static LinkedHashSet<String> readWordsFromFile(File file) throws Exception {
		LinkedHashSet<String> words = new LinkedHashSet<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"), 1024*1024*4);
		String line = "";
		while((line = reader.readLine()) != null) {
			words.add(line);
		}
		reader.close();
		return words;
	}
	
	public static LinkedHashSet<String> readWordsFromFolder(File file) throws Exception {
		LinkedHashSet<String> words = new LinkedHashSet<String>();
		if(file.isDirectory()) {
			for(File subFile: file.listFiles()) {
				words.addAll(readWordsFromFolder(subFile));
			}
		} else {
			words.add(file.getName());
		}
		return words;
	}
	
	public static LinkedHashSet<String> readWordsFromThisFolder(File file) throws Exception {
		LinkedHashSet<String> words = new LinkedHashSet<String>();
		for(File subFile: file.listFiles()) {
			if(subFile.isFile())
			words.add(subFile.getName());
		}
		return words;
	}
	
	public static Set<File> readFolderSet(File folder) {
		LinkedHashSet<File> folders = new LinkedHashSet<File>();
		if(folder.isDirectory()) {
			folders.add(folder);
			for(File subFile: folder.listFiles()) {
				folders.addAll(readFolderSet(subFile));
			}
		}
		return folders;
	}
	
	
	 public static List<Long> readLongs(String file) throws IOException{
       	String line = null;
       	List<Long> lines = new ArrayList<Long>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024*1024*4);
		while((line=reader.readLine())!=null){
			lines.add(Long.parseLong(line));
		}
		reader.close();
		return lines;
	}
	    
    public static List<Integer> readInts(String file) throws IOException{
       	String line = null;
       	List<Integer> lines = new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)), 1024*1024*4);
		while((line=reader.readLine())!=null){
			lines.add(Integer.parseInt(line));
		}
		reader.close();
		return lines;
    }
	
    public static void writeIntArray(String file, int[] intArray) throws IOException{
    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)), 1024*1024*4);
		for(int val: intArray){
			writer.append(Integer.toString(val));
			writer.append("\n");
		}
		
		writer.close();
    }
    
    public static void writeLongArray(String file, long[] longArray) throws IOException{
    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)), 1024*1024*4);
		for(long val: longArray){
			writer.append(Long.toString(val));
			writer.append("\n");
		}
		
		writer.close();
    }
	
    public static List<String> readLines(String file) throws IOException{
    	String line = null;
    	List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
		try {
			while((line=reader.readLine())!=null){
				lines.add(line);
			}
		} finally {
			reader.close();
		}
		return lines;
    }
    
    public static List<String> readLines(String file, String charset) throws IOException{
    	String line = null;
    	List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), charset));
		try {
			while((line=reader.readLine())!=null){
				lines.add(line);
			}
		} finally {
			reader.close();
		}
		return lines;
    }
	
    
    public static List<File> listFilesDeep(String rootDir, String[] matchPatterns){
    	List<File> list = new ArrayList<File>();
    	doListFiles(new File(rootDir), matchPatterns, list);
    	return list;
    }
    
    private static void doListFiles(File currentDir, String[] matchPatterns, List<File> list){
    	if(currentDir.isDirectory()){
    		File[] files = currentDir.listFiles();
    		for(File currentFile: files){
    			if(currentFile.isDirectory()){
    				doListFiles(currentFile, matchPatterns, list);
    			}else{
    				String name = currentFile.getName();
    				for(String pattern: matchPatterns){
    					if(name.indexOf(pattern)>=0){
    						list.add(currentFile);
    					}
    				}
    			}
    		}
    	}
    }
    
    public static void writeLines(String file,List<String> lines) throws IOException{
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file)),"UTF-8"));
    	try {
			for(String line: lines){
				writer.append(line);
				writer.append("\n");
			}
		} finally{
			try {
				writer.close();
			} catch (IOException ignore) {
			}
		}
    }
    
    public static void appendLines(String file,List<String> lines) throws IOException{
    	BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    	try {
			for(String line: lines){
				writer.append(line);
				writer.append("\r\n");
			}
		} finally{
			try {
				writer.close();
			} catch (IOException ignore) {
			}
		}
    }
    
    
    /**
     * 如果不存在就创建文件
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean createEmptyFileIfNotExist(String file) throws IOException{
    	File f = new File(file);
    	if(!f.exists()){
    		return f.createNewFile();
    	}else{
    		return true;
    	}
    	
    }
    
    /**
     * 复制文件
     * @param srcFile
     * @param destFile
     * @throws IOException 
     */
    public static void copyFile(String srcFile, String destFile) throws IOException{
    	org.apache.commons.io.FileUtils.copyFile(new File(srcFile), new File(destFile));
    }
    
    /**
     * 复制文件到指定目录
     * @param srcFile
     * @param destDir
     * @throws IOException
     */
    public static void copyFileToDir(String srcFile, String destDir) throws IOException{
    	org.apache.commons.io.FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
    }
    
    /**
     * 判断文件是否存在
     * @param file
     * @return
     */
    public static boolean isFileExist(String file){
    	File f = new File(file);
    	return f.exists();
    }
}
