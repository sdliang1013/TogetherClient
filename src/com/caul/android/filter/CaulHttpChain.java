package com.caul.android.filter;

import java.io.IOException;

import com.caul.android.net.CaulRequest;
import com.caul.android.net.CaulResponse;
import com.caul.core.exception.CaulException;

public interface CaulHttpChain {

	public abstract void next(CaulRequest request, CaulResponse response) throws CaulException, IOException;
}
