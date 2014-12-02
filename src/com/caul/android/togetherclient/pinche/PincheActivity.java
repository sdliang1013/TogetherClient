package com.caul.android.togetherclient.pinche;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.caul.android.togetherclient.R;

public class PincheActivity extends Activity implements ViewFactory, OnTouchListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pinche);
		TextView tv = (TextView) findViewById(R.id.pinche_txt);
		tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View vw) {
				onViewClick(vw);
			}
		});
	}
	
	protected void onViewClick(View vw) {
		Toast.makeText(this, "拼车界面", Toast.LENGTH_SHORT).show();
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
