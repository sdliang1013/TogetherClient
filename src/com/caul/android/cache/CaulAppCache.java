package com.caul.android.cache;

import com.caul.android.application.CaulApplication;
import com.caul.core.exception.CaulException;

public class CaulAppCache {

	private CaulAppCache() {
	}

	private CaulApplication application;
	private static CaulAppCache INSTANCE;

	public static CaulAppCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CaulAppCache();
		}
		return INSTANCE;
	}

	public void setApplication(CaulApplication application) {
		this.application = application;
	}


	/**
	 * 获取Application变量
	 * 
	 * @param key
	 * @param cls
	 * @return
	 */
	public <T> T getAttribute(String key, Class<T> cls) {
		return getAttribute(key, cls, null);
	}
	
	/**
	 * 获取Application变量
	 * 
	 * @param key
	 * @param cls
	 * @param defaultValue
	 * @return
	 */
	public <T> T getAttribute(String key, Class<T> cls, T defaultValue) {
		if (application == null) {
			throw new CaulException.Builder().message("CaulApplication未设置!").builder();
		}
		T t = application.getAttribute(key, cls);
		t = t == null ? defaultValue : t;
		return t;
	}

	/**
	 * 设置Application变量
	 * 
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key, Object value) {
		if (application == null) {
			throw new CaulException.Builder().message("CaulApplication未设置!").builder();
		}
		application.setAttribute(key, value);
	}
}
