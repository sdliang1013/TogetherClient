package com.caul.android.togetherclient.swim;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.caul.android.togetherclient.R;

public class SwimActivity extends Activity implements ViewFactory, OnTouchListener {

	private ListView mListView;

	private SimpleAdapter simpleAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swim);
		mListView = (ListView) this.findViewById(R.id.listview_swim);

		// 创建简单适配器SimpleAdapter
		simpleAdapter = new SimpleAdapter(this, this.getItem(), R.layout.listview_common, 
				new String[] { "itemTitle", "itemPhoto", "itemSummary", "itemAuthor", "itemPublishtime" }, 
				new int[] { R.id.title, R.id.photograph, R.id.summary, R.id.author, R.id.publishtime });

		// 加载SimpleAdapter到ListView中
		mListView.setAdapter(simpleAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int itemIdx, long itemId) {
				SwimActivity.this.onItemClick(adapter, view, itemIdx, itemId);
			}
		});
	}
	
	public void onItemClick(AdapterView<?> adapter, View view, int itemIdx, long itemId) {
		Map<String, Object> dataMap = (Map<String, Object>)adapter.getItemAtPosition(itemIdx);
		Toast.makeText(this, dataMap.get("itemSummary").toString(), Toast.LENGTH_SHORT).show();
	}

	private List<? extends Map<String, ?>> getItem() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemTitle", "嘎斯各位"+i);
			map.put("itemPhoto", R.drawable.item06);
			map.put("itemSummary", "测试内容:"+i);
			map.put("itemAuthor", "user_"+i);
			map.put("itemPublishtime", new Date().toLocaleString());
			list.add(map);
		}
		return list;
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
