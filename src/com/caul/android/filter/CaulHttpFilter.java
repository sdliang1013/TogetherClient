package com.caul.android.filter;

import java.io.IOException;

import com.caul.android.net.CaulRequest;
import com.caul.android.net.CaulResponse;
import com.caul.core.exception.CaulException;

public interface CaulHttpFilter {

	public void filter(CaulRequest request, CaulResponse response, CaulHttpChain chain) throws CaulException,
			IOException;
}
