package com.caul.android.net;

import java.io.IOException;

import com.caul.android.filter.CaulHttpAppendChain;
import com.caul.android.filter.CaulHttpChain;
import com.caul.android.filter.CaulHttpFilter;
import com.caul.core.exception.CaulException;

public abstract class HttpConnection implements CaulHttpFilter {

	private CaulHttpChain chain;

	public HttpConnection(final CaulHttpAppendChain<CaulHttpFilter> chain) {
		this.chain = chain;
		chain.append(this);
	}

	protected abstract void doSend(CaulRequest request, CaulResponse response) throws IOException;

	@Override
	public void filter(CaulRequest request, CaulResponse response, CaulHttpChain chain) throws CaulException,
			IOException {
		doSend(request, response);
	}

	public void sendData(CaulRequest request, CaulResponse response) throws IOException {
		if (chain != null) {
			chain.next(request, response);
		}
	}
}
