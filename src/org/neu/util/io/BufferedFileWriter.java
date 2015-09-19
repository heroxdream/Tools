package org.neu.util.io;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class BufferedFileWriter extends Writer {
    
    private BufferedWriter writer;
    
    private int size;
    
    public BufferedFileWriter(String file) {
        this(file, "UTF-8", 1024 * 1024 * 128);
    }

    public BufferedFileWriter(String file, String charSet, int size) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (file), charSet), size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void close() throws IOException {
        writer.close();
        
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf, off, len);
        
    }

    public void writeLine(String line) throws IOException {
        writer.write(line + "\n");
    }

}
