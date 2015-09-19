/*
 * 知众网络
 * Copyright © 2012 zhizhong.com. All Rights Reserved.
 */

 /**
 * Project  : KeyWordsRecognize
 * Package  : com.zhizhong.search.keyword.spider
 * File     : Md5Util.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2012-8-24
 */
package org.neu.util;

import java.security.MessageDigest;

/**
 *
 *
 * @author solosky <solosky772@qq.com>
 *
 */
public class Md5Util {
	
	public static String md5(String str) {
		try {
			MessageDigest dist = MessageDigest.getInstance("MD5");
			byte[] data = dist.digest(str.getBytes("utf8"));
			return byte2HexStringWithoutSpace(data, 0, data.length);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String byte2HexStringWithoutSpace(byte[] b, int offset, int len) {
		char[] hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		if (b == null)
			return "null";

		// 检查索引范围
		int end = offset + len;
		if (end > b.length)
			end = b.length;

		StringBuffer sb = new StringBuffer();

		for (int i = offset; i < end; i++) {
			sb.append(hex[(b[i] & 0xF0) >>> 4]).append(hex[b[i] & 0xF]);
		}
		return sb.toString();
	}
}
