package com.caul.android.application;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Application;

public class CaulApplication extends Application{

	private Map<String, Object> customMap;
	public CaulApplication() {
		super();
		customMap = new ConcurrentHashMap<String, Object>();
	}
	
	public void setAttribute(String key,Object value){
		customMap.put(key, value);
	}
	
	public void removeAttribute(String key){
		customMap.remove(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key,Class<T> cls){
		return (T)customMap.get(key);
	}
}
