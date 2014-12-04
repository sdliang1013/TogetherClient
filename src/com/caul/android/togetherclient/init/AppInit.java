package com.caul.android.togetherclient.init;

import android.app.Activity;
import android.os.Bundle;

import com.caul.android.application.CaulApplication;
import com.caul.android.cache.CaulAppCache;

public class AppInit {

	private AppInit(){}
	
	public static void initApp(Bundle bundle,Activity activity){
		CaulAppCache.getInstance().setApplication((CaulApplication) activity.getApplication());
	}
}
