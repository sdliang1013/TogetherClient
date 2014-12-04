package com.caul.android.filter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.caul.android.net.CaulRequest;
import com.caul.android.net.CaulResponse;
import com.caul.core.exception.CaulException;

public class CaulHttpFilterChain implements CaulHttpAppendChain<CaulHttpFilter> {

	private List<CaulHttpFilter> filterList;
	private int index;
	private int count;

	public CaulHttpFilterChain() {
		filterList = new CopyOnWriteArrayList<CaulHttpFilter>();
		index = 0;
		count = 0;
	}

	@Override
	public void next(CaulRequest request, CaulResponse response) throws CaulException, IOException {
		if (this.index < this.count) {
			this.filterList.get(index++).filter(request, response, this);
		}
	}

	public void release() {
		this.filterList.clear();
		this.index = 0;
		this.count = 0;
	}

	public void reuse() {
		this.index = 0;
	}

	@Override
	public void append(CaulHttpFilter t) throws CaulException {
		this.filterList.add(t);
		this.count++;
	}

}
