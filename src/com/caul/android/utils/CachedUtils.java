package com.caul.android.utils;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedUtils {
	
	private static final String CONF_PATH = "conf.system";

	private static ResourceBundle resourceBundle;

	private static ExecutorService executor;
	
	public static void setResouceBundle(ResourceBundle bundle){
		resourceBundle = bundle;
	}

	public static void setThreadPoolExecutor(ExecutorService executorService){
		executor = executorService;
	}
	
	public static ExecutorService getThreadPoolExecutor() {
		if (executor == null) {
			executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(3));
		}
		return executor;
	}
	
	public static ResourceBundle getResourceBundle(){
		if(resourceBundle == null){
			resourceBundle = ResourceBundle.getBundle(CONF_PATH);
		}
		return resourceBundle;
	}
	
	public static String getConfigValue(String key){
		return getResourceBundle().getString(key);
	}
}
