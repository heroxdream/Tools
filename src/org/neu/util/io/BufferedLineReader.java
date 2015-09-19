package org.neu.util.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferedLineReader {
	private BufferedReader reader;

	public BufferedLineReader(BufferedReader reader) {
		this.reader = reader;
	}
	
	public BufferedLineReader(String fileName, int buffSize) throws FileNotFoundException{
		this.reader = new BufferedReader(new FileReader(fileName), buffSize);
	}
	
	public String readLine() throws IOException{
		return this.reader.readLine();
	}
	
	public int readLines(int maxLines, List<String> list) throws IOException{
		String line = null;
		int count = 0;
		while((line=readLine())!=null && count<maxLines){
			list.add(line);
			count++;
		}
		return list.size();
	}
	
	public List<String> readLines(int maxLines) throws IOException{
		List<String> list = new ArrayList<String>();
		readLines(maxLines, list);
		return list.size()>0 ? list : null;
	}
	
	public void close() throws IOException{
		reader.close();
	}
}
