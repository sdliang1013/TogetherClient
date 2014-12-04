package com.caul.android.togetherclient.logon;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.caul.android.filter.CaulHttpFilterChain;
import com.caul.android.net.CaulRequest;
import com.caul.android.net.CaulResponse;
import com.caul.android.net.HttpConnection;
import com.caul.android.net.HttpConnectionPost;
import com.caul.android.togetherclient.R;
import com.caul.android.utils.CachedUtils;
import com.caul.android.utils.HttpConnectionUtil;

public class LogonActivity extends Activity implements ViewFactory,
		OnTouchListener {

	private Button submitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logon);
		submitBtn = (Button) this.findViewById(R.id.btn_submit);

		submitBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View vw) {
				LogonActivity.this.onClick(vw);
			}

		});
	}

	public void onClick(View view) {
		EditText unEdit = (EditText) this.findViewById(R.id.text_userName);
		EditText pwdEdit = (EditText) this.findViewById(R.id.text_pwd);
		final String un = unEdit.getText().toString();
		final String pwd = pwdEdit.getText().toString();
		Toast.makeText(this, "用户名:" + un + ";密码:" + pwd, Toast.LENGTH_SHORT)
				.show();
		CachedUtils.getThreadPoolExecutor().submit(new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				try {
					HttpConnection conn = new HttpConnectionPost(
							new CaulHttpFilterChain());
					CaulRequest request = new CaulRequest();
					CaulResponse response = new CaulResponse();
					request.setPostData(new ConcurrentHashMap<String, String>());
					request.setUri(CachedUtils.getConfigValue("SERVERPATH")
							+ CachedUtils.getConfigValue("URL_GETTOKEN"));
					request.setCharset("UTF-8");
					request.setTimeout(10000);
					request.getPostData().put("un", un);
					request.getPostData().put("pwd", pwd);
					conn.sendData(request, response);
					StringBuffer sb = HttpConnectionUtil.getResponseBody(
							response.getResponse(), "UTF-8");
					Toast.makeText(LogonActivity.this, sb.toString(),
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(LogonActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
				return null;
			}
		});

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		return null;
	}
}
