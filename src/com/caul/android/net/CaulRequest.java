package com.caul.android.net;

import java.util.Map;

import org.apache.http.HttpRequest;

public class CaulRequest {

	String uri;
	String charset;
	int timeout;
	Map<String, String> postData;
	HttpRequest request;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public HttpRequest getRequest() {
		return request;
	}
	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	public Map<String, String> getPostData() {
		return postData;
	}
	public void setPostData(Map<String, String> postData) {
		this.postData = postData;
	}
	
}
