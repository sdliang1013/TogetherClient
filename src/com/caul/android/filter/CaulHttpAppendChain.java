package com.caul.android.filter;

import com.caul.core.exception.CaulException;

public interface CaulHttpAppendChain<T> extends CaulHttpChain {

	public void append(T t) throws CaulException;
}
