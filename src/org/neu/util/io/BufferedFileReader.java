package org.neu.util.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

public class BufferedFileReader {
    
    private final static Logger log = LogManager.getLogger(BufferedFileReader.class);
    
    private String file;
    
    private byte[] buffer;
    
    private byte[] buffer2;
    
    private InputStream is;
    
    private int size = 0;
    
    private int point = 0;
    
    private int length = 0;
    
    public BufferedFileReader(String file) {
        this(file, 1024 * 1024 * 4);
    }
    
    public BufferedFileReader(String file, int size) {
        this.file = file;
        this.size = size;
        this.buffer = new byte[size];
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        
    }
    
    public String readLine() {
        
        int start = point;
        int end = point;
        while(point <= length) {
            if(point == length) {
                try {
                    int remain = length - start;
                    buffer2 = new byte[size];
                    System.arraycopy(buffer, start, buffer2, 0, remain);
                    buffer = buffer2;
                    
                    int count = is.read(buffer, remain, size - remain);
                    if(count > 0) {
                        log.info("reading new block " + count + " from file " + file );
                        length = count;
                    }
                    if(count == -1) {
                        return null;
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
                point = 0;
                start = 0;
            }
            
            if(buffer.length -1 == point ) {
                end = point;
            }
            
            if(buffer[point] == '\r' || buffer[point] == '\n') {
                end = point;
                while(buffer[point] == '\r' || buffer[point] == '\n') {
                    point ++;
                }
                break;
            }
            point ++;
        }
        
        return new String(buffer, start, end - start);
    }

    public void close() {
        try {
            is.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        
        System.out.println(System.currentTimeMillis());
        BufferedFileReader reader = new BufferedFileReader("D:/projects/Eclipse/Dataprocess/dist/tag_nearest_10.log.10times", 1024 * 1024 * 128 );
        String line = null;
        while((line = reader.readLine())!= null) {
            
        }
        System.out.println(System.currentTimeMillis());
    }

    private static void test1() {
        try {
            BufferedFileReader reader = new BufferedFileReader("D:/projects/Eclipse/Dataprocess/dist/tag_nearest_10.log", 1024 * 1024 );
            List<String> lines = new ArrayList<String>();
            String line = null;
            while((line = reader.readLine())!= null) {
                lines.add(line);
            }
            
            BufferedFileWriter writer = new BufferedFileWriter("D:/projects/Eclipse/Dataprocess/dist/tag_nearest_10.log.10times", "UTF-8", 1024 * 1024 * 4);
            for(int i=0; i< 400; i++ ) {
                for(String temp: lines) {
                    writer.writeLine(temp);
                }
            }
            writer.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
}
