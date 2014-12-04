package com.caul.android.togetherclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.caul.android.components.switcher.CaulImageSwitcher;
import com.caul.android.togetherclient.init.AppInit;

public class MainActivity extends FragmentActivity implements ViewFactory, OnTouchListener {

	/**
	 * ImagaSwitcher 的引用
	 */
	private CaulImageSwitcher mImageSwitcher;
	
	private int exitCount = 0;

	/**
	 * 图片id数组
	 */
	private int[] imgIds;

	private int[] textViewIds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AppInit.initApp(savedInstanceState, this);//初始化
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewIds = new int[] { R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8 };
		imgIds = new int[] { R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
				R.drawable.item05, R.drawable.item06, R.drawable.item07 };

		// 实例化ImageSwitcher
		mImageSwitcher = new CaulImageSwitcher((ImageSwitcher) findViewById(R.id.mainSwitcher), imgIds);
		mImageSwitcher.init(this, this, this);

		for (int i = 0; i < textViewIds.length; i++) {
			final MainTextView textEnum = MainTextView.getById(textViewIds[i]);
			TextView tv = (TextView) findViewById(textEnum.getId());
			tv.setText(textEnum.getName());
			tv.setBackgroundColor(textEnum.getBgColor());
			tv.setTextColor(textEnum.getTextColor());
			tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					openView(textEnum.getActivity());
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public View makeView() {
		return mImageSwitcher.makeView(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		mImageSwitcher.onSwitch(v, event);

		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	
	private void openView(Class<? extends Activity> activityClz) {
		Intent i = new Intent(this, activityClz);
		this.startActivity(i);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		String str = "code:"+event.getKeyCode()+";event:"+event.getAction()+";exitCount:"+(++exitCount);
		Toast.makeText(this, str , Toast.LENGTH_SHORT).show();
		if(event.getKeyCode() == 4){//退出
//			return false;
		}
		return super.onKeyUp(keyCode, event);
	}
}
