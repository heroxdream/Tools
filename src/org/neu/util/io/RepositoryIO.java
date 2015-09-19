package org.neu.util.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositoryIO {

	private final static Logger log = LogManager.getLogger(RepositoryIO.class);

	public static int BUFF_SIZE = 1024 * 1024 * 256;

	public static Object readObject(String file) {
		ObjectInputStream ois = null;
		try {
			log.info("read object start: " + file);
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file), BUFF_SIZE));
			Object obj = ois.readObject();
			log.info("read object finished..");
			return obj;

		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					log.error("close file error: file=" + file, e);
				}
			}
		}
		return null;
	}

	public static void write(Object obj, String file) {
		ObjectOutputStream oos = null;
		try {
			log.info("write object start: " + file);
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file), BUFF_SIZE));
			oos.writeObject(obj);
			oos.close();
			log.info("write object finished..");
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					log.error("write file error: file=" + file, e);
				}
			}
		}
	}

	public static Externalizable readExternal(Externalizable obj, String file) {
		try {

			log.info("read Externalizable start: " + file);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file), BUFF_SIZE));
			obj.readExternal(ois);
			ois.close();
			log.info("read Externalizable finished..");
			return obj;

		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static void writeExternal(Externalizable obj, String file) {

		try {
			log.info("write Externalizable start: " + file);
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file), BUFF_SIZE));
			obj.writeExternal(oos);
			oos.close();
			log.info("write Externalizable finished..");
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

}
