package com.caul.android.togetherclient.filter;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.caul.android.cache.CaulAppCache;
import com.caul.android.filter.CaulHttpChain;
import com.caul.android.filter.CaulHttpFilter;
import com.caul.android.net.CaulRequest;
import com.caul.android.net.CaulResponse;
import com.caul.android.utils.Constant;
import com.caul.core.exception.CaulException;
import com.caul.core.exception.ExceptionCode;

public class LogonFilter implements CaulHttpFilter {

	@Override
	public void filter(CaulRequest request, CaulResponse response, CaulHttpChain chain) throws CaulException,
			IOException {
		String token = CaulAppCache.getInstance().getAttribute(Constant.APP_CACHE_KEY_TOKEN, String.class);
		if (StringUtils.isEmpty(token)) {
//			throw new CaulException.Builder().errorCode(ExceptionCode.LOGON_NOTLOGON).message("您未登录,请先登陆").builder();
		}
		chain.next(request, response);
	}

}
