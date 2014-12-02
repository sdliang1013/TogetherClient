package com.caul.android.togetherclient;

import android.app.Activity;

import com.caul.android.togetherclient.pinche.PincheActivity;
import com.caul.android.togetherclient.swim.SwimActivity;

public enum MainTextView {

	SWIM(R.id.tv3,0XFFFF6666,0xFFFFFFFF, "游泳", SwimActivity.class), 
	FOOD(R.id.tv4,0XFF1E67C0,0xFFFFFFFF, "美食", Activity.class),//R.layout.activity_food), 
	BIKE(R.id.tv5,0XFFD47756,0xFFFFFFFF, "单车", Activity.class),//R.layout.activity_bike), 
	PINCHE(R.id.tv6,0XFF5A626F,0xFFFFFFFF, "拼车", PincheActivity.class),//R.layout.activity_pinche), 
	FILM(R.id.tv7,0XFFEE7434,0xFFFFFFFF, "电影", Activity.class),//R.layout.activity_film), 
	CUSTOM(R.id.tv8,0XFF3EADEB,0xFFFFFFFF, "自定义", Activity.class);//R.layout.activity_custom);

	private final int id;
	private final String name;
	private final int textColor;
	private final int bgColor;
	private final Class<? extends Activity> activity;

	private MainTextView(int id, int bgColor, int textColor, String name, Class<? extends Activity> activity) {
		this.id = id;
		this.textColor = textColor;
		this.bgColor = bgColor;
		this.name = name;
		this.activity = activity;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Class<? extends Activity> getActivity() {
		return activity;
	}

	public int getTextColor() {
		return textColor;
	}

	public int getBgColor() {
		return bgColor;
	}

	public static MainTextView getById(int id) {
		if (id == SWIM.id) {
			return SWIM;
		} else if (id == FOOD.id) {
			return FOOD;
		} else if (id == BIKE.id) {
			return BIKE;
		} else if (id == PINCHE.id) {
			return PINCHE;
		} else if (id == FILM.id) {
			return FILM;
		} else if (id == CUSTOM.id) {
			return CUSTOM;
		}
		return null;
	}
}
