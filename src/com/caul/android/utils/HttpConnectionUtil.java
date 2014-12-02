package com.caul.android.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.net.http.AndroidHttpClient;

public class HttpConnectionUtil {

	/**
	 * http get 调用
	 * 
	 * @param uri
	 *            地址
	 * @param charset
	 *            编码
	 * @param timeout
	 *            超时
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer get(String uri, String charset, int timeout) throws IOException {
		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, timeout); // 设置连接超时
			HttpClient client = new DefaultHttpClient(httpParams); // 生成一个http客户端发送请求对象
			HttpGet httpGet = new HttpGet(uri); // 设定请求方式
			HttpResponse httpResponse = client.execute(httpGet); // 发送请求并等待响应
			// 判断网络连接是否成功
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				throw new IOException("网络错误异常,请检查！" + uri);
			}
			HttpEntity entity = httpResponse.getEntity(); // 获取响应里面的内容
			// InputStream is = entity.getContent(); // 得到服务气端发回的响应的内容（都在一个流里面）
			// 得到服务气端发回的响应的内容（都在一个字符串里面）
			return new StringBuffer(EntityUtils.toString(entity, charset));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * http get 调用
	 * 
	 * @param uri
	 *            地址
	 * @param postData
	 *            请求数据
	 * @param charset
	 *            编码
	 * @param timeout
	 *            超时
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer post(String uri, Map<String, String> postData, String charset, int timeout)
			throws IOException {
		List<NameValuePair> dataList = new CopyOnWriteArrayList<NameValuePair>();
		for (String key : postData.keySet()) {
			dataList.add(new BasicNameValuePair(key, postData.get(key)));
		}
		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, timeout); // 设置连接超时
			HttpClient client = new DefaultHttpClient(httpParams); // 生成一个http客户端发送请求对象
			HttpPost httpPost = new HttpPost(uri); // 设定请求方式
			if (dataList.size() > 0) {
				// 把键值对进行编码操作并放入HttpEntity对象中
				httpPost.setEntity(new UrlEncodedFormEntity(dataList, "UTF-8"));
			}
			HttpResponse httpResponse = client.execute(httpPost); // 发送请求并等待响应
			// 判断网络连接是否成功
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				throw new IOException("网络错误异常,请检查！" + uri);
			}
			HttpEntity entity = httpResponse.getEntity(); // 获取响应里面的内容
			// InputStream is = entity.getContent(); // 得到服务气端发回的响应的内容（都在一个流里面）
			// 得到服务气端发回的响应的内容（都在一个字符串里面）
			return new StringBuffer(EntityUtils.toString(entity, charset));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		// String content =
		// HttpConnectionUtil.getHttpContent("http://192.168.1.61:8001/");
		String content;
		String uri = "http://www.baidu.com";
		try {
			String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
			AndroidHttpClient androidHttpClient = AndroidHttpClient.newInstance(userAgent);
			HttpGet httpGet = new HttpGet(uri); // 设定请求方式
			HttpResponse httpResponse = androidHttpClient.execute(httpGet); // 发送请求并等待响应
			// 判断网络连接是否成功
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				throw new IOException("网络错误异常,请检查！" + uri);
			}
			androidHttpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity(); // 获取响应里面的内容
			// InputStream is = entity.getContent(); // 得到服务气端发回的响应的内容（都在一个流里面）
			// 得到服务气端发回的响应的内容（都在一个字符串里面）
			content = EntityUtils.toString(entity, "utf-8");
			System.out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
