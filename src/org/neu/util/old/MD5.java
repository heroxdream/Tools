package org.neu.util.old;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

//import sun.misc.BASE64Encoder;

public class MD5 {
	
	private static char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符
		     '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',  'E', 'F'}; 
	
	public static String encodeByHex(String s) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(s.getBytes());
			byte tmp[] = md5.digest();
			char str[] = new char[16 * 2];
			int j = 0;
			for (int i = 0; i < 16; i++) { 
				byte byte0 = tmp[i];
				str[j++] = hexDigits[byte0 >>> 4 & 0xf];
				str[j++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);     
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static String encodeByBase64(String s) {
		if(s == null) {
			return null;
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(s.getBytes());
			return new BASE64Encoder().encode(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(encodeByHex("50rainbowfox@163.com"));
	}
	
}
