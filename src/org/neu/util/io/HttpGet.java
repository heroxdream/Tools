package org.neu.util.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.util.EncodingSniffer;

public class HttpGet {
	
	private static final Logger log = LogManager.getLogger(HttpGet.class);
	
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0";
	
	private static final int TIME_OUT = 30 * 1000;
	
	private static HttpClient client = null;
	private static MultiThreadedHttpConnectionManager connectionManager;
	private static HttpClientParams clientParams;
	
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void init(){
		log.info("HttpUtils init.");
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setDefaultMaxConnectionsPerHost(8000);
		params.setConnectionTimeout(TIME_OUT);
		params.setSoTimeout(TIME_OUT);
		params.setMaxTotalConnections(8000);
		clientParams = new HttpClientParams();
		clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		clientParams.setSoTimeout(TIME_OUT);
		clientParams.setConnectionManagerTimeout(TIME_OUT);
//		DefaultMethodRetryHandler retryhandler = new DefaultMethodRetryHandler();
//		retryhandler.setRequestSentRetryEnabled(false);  
//		retryhandler.setRetryCount(3);  
//		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, retryhandler);
		client = new org.apache.commons.httpclient.HttpClient(clientParams, connectionManager);
	}
	
	public static void dispose(){
		connectionManager.shutdown();
	}
	
	public static String getString(String url) throws Exception{

		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true); //new added by hanx
		method.addRequestHeader("User-Agent", USER_AGENT);
		client.executeMethod(method);
		byte[] body = TextFileUtils.inputStream2Byte(method.getResponseBodyAsStream());
		String charSet = getCharset(method, new ByteArrayInputStream(body));
		String responseBody = new String(body, charSet);
		method.releaseConnection();
		return responseBody;
	}
	
	public static InputStream getAsStream(String url) throws IOException{
		GetMethod method = new GetMethod(url);
		method.setFollowRedirects(true);
		method.addRequestHeader("User-Agent", USER_AGENT);
		client.executeMethod(method);
		InputStream in = method.getResponseBodyAsStream();
		return in;
	}
	
	public static byte[] getBytes(String url) throws Exception {
		return getBytes(url, null);
	}
	
	public static byte[] getBytes(String url, String referer) throws Exception {
		GetMethod method = new GetMethod(url);
		try {
			method.addRequestHeader("User-Agent", USER_AGENT);
			if(referer != null) {
				method.addRequestHeader("referer", referer);
			}
			client.executeMethod(method);
			InputStream is = method.getResponseBodyAsStream();
			byte[] responseBody = TextFileUtils.inputStream2Byte(is);
			return responseBody;
		} finally {
			method.releaseConnection();
		}
	}

	private static String getCharset(HttpMethod method, InputStream in) throws IOException {
		String charSet = EncodingSniffer.sniffEncoding(getResponseHeaders(method), in);
		charSet = StringUtils.isBlank(charSet) ? "GBK" : charSet; 
		charSet = "gb2312".equalsIgnoreCase(charSet) ? "GBK" : charSet;
		return charSet;
	}
	
	private static List<com.gargoylesoftware.htmlunit.util.NameValuePair> getResponseHeaders(HttpMethod method){
		List<com.gargoylesoftware.htmlunit.util.NameValuePair> headers = new ArrayList<com.gargoylesoftware.htmlunit.util.NameValuePair>();
        for (final Header header : method.getResponseHeaders()) {
            headers.add(new com.gargoylesoftware.htmlunit.util.NameValuePair(header.getName(), header.getValue()));
        }
        return headers;
	}
	
	public static String postString(String url, NameValuePair[] parameters) throws HttpException, IOException {
		log.info("url = " + url);
		PostMethod post = null;
		String response = null;
		try {
			post = new PostMethod(url);
			post.addParameters(parameters);
			post.addRequestHeader("User-Agent", USER_AGENT);
			HttpMethodParams param = post.getParams();
			param.setContentCharset("UTF-8");
			client.executeMethod(post);
			response = post.getResponseBodyAsString();
		} finally {
			if(post != null) {
				post.releaseConnection();
			}
		}
		return response;
	}

}
