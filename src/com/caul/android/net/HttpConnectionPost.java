package com.caul.android.net;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.caul.android.filter.CaulHttpAppendChain;
import com.caul.android.filter.CaulHttpFilter;
import com.caul.android.utils.HttpConnectionUtil;

public class HttpConnectionPost extends HttpConnection {

	public HttpConnectionPost(final CaulHttpAppendChain<CaulHttpFilter> chain) {
		super(chain);
	}

	@Override
	protected void doSend(CaulRequest request, CaulResponse response) throws IOException {
		HttpResponse httpResponse = HttpConnectionUtil.post(request.getUri(), request.getPostData(),
				request.getTimeout());
		response.setResponse(httpResponse);

	}

}
