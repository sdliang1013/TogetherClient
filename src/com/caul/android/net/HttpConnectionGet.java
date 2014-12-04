package com.caul.android.net;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.caul.android.filter.CaulHttpAppendChain;
import com.caul.android.filter.CaulHttpFilter;
import com.caul.android.filter.CaulHttpFilterChain;
import com.caul.android.utils.HttpConnectionUtil;

public class HttpConnectionGet extends HttpConnection {

	public HttpConnectionGet(final CaulHttpAppendChain<CaulHttpFilter> chain) {
		super(chain);
	}

	@Override
	protected void doSend(CaulRequest request, CaulResponse response) throws IOException {
		HttpResponse httpResponse = HttpConnectionUtil.get(request.getUri(), request.getTimeout());
		response.setResponse(httpResponse);
	}
	
	public static void main(String[] args) {
		try {
			HttpConnection conn = new HttpConnectionGet(new CaulHttpFilterChain());
			CaulRequest request = new CaulRequest();
			CaulResponse response = new CaulResponse();
			request.setUri("http://localhost:9090/together/sys/login/getToken.spr?un=sdliang1013&pwd=c898f62c63cdcd98b559baec4c0f0230");
			request.setCharset("UTF-8");
			request.setTimeout(10000);
			conn.sendData(request, response);
			StringBuffer sb = HttpConnectionUtil.getResponseBody(response.getResponse(), "UTF-8");
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
