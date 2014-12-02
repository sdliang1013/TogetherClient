package com.caul.android.components.switcher;

import android.app.Activity;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.caul.android.togetherclient.R;

public class CaulImageSwitcher {

	private boolean initFlag = false;
	/**
	 * ImagaSwitcher 的引用
	 */
	private ImageSwitcher mImageSwitcher;
	/**
	 * 图片id数组
	 */
	private int[] imgResourceIds;

	private ImageView[] imgViews;
	/**
	 * 当前选中的图片id序号
	 */
	private int currentPosition;
	/**
	 * 按下点的X坐标
	 */
	private float downX;

	public CaulImageSwitcher(ImageSwitcher mImageSwitcher, int[] imgIds) {
		this.mImageSwitcher = mImageSwitcher;
		this.imgResourceIds = imgIds;
	}

	/**
	 * 初始化
	 * 
	 * @param activity
	 * @param viewFactory
	 * @param listener
	 * @return
	 */
	public boolean init(Activity activity, ViewFactory viewFactory, OnTouchListener listener) {
		if (!initFlag) {
			// 设置Factory
			this.mImageSwitcher.setFactory(viewFactory);
			// 设置OnTouchListener，我们通过Touch事件来切换图片
			this.mImageSwitcher.setOnTouchListener(listener);

			imgViews = new ImageView[imgResourceIds.length];
			for (int i = 0; i < imgResourceIds.length; i++) {
				imgViews[i] = new ImageView(activity);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				layoutParams.rightMargin = 1;
				layoutParams.leftMargin = 1;
				activity.addContentView(imgViews[i], layoutParams);
			}
			// 这个我是从上一个界面传过来的，上一个界面是一个GridView
			currentPosition = activity.getIntent().getIntExtra("position", 0);
			mImageSwitcher.setImageResource(imgResourceIds[currentPosition]);
		}
		initFlag = true;
		return initFlag;
	}

	public View makeView(Activity target) {
		final ImageView i = new ImageView(target);
		i.setBackgroundColor(0xff000000);
		i.setScaleType(ImageView.ScaleType.CENTER_CROP);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i;
	}

	/**
	 * 滑动
	 * 
	 * @param v
	 * @param event
	 * @return
	 */
	public boolean onSwitch(View v, MotionEvent event) {
		if (!initFlag) {
			throw new RuntimeException("CaulImageSwitcher should be init!");
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			// 手指按下的X坐标
			downX = event.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float lastX = event.getX();
			// 抬起的时候的X坐标大于按下的时候就显示上一张图片
			if (lastX == downX) {
				Toast.makeText(v.getContext(), "第" + (currentPosition + 1) + "张", Toast.LENGTH_SHORT).show();
			}

			if (lastX > downX) {
				if (currentPosition > 0) {
					// 设置动画，这里的动画比较简单，不明白的去网上看看相关内容
					mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.left_in));
					mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.right_out));
					currentPosition--;
					mImageSwitcher.setImageResource(imgResourceIds[currentPosition % imgResourceIds.length]);
					setImageBackground(currentPosition);
				} else {
					Toast.makeText(v.getContext(), "已经是第一张", Toast.LENGTH_SHORT).show();
				}
			}

			if (lastX < downX) {
				if (currentPosition < imgResourceIds.length - 1) {
					mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in));
					mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out));
					currentPosition++;
					mImageSwitcher.setImageResource(imgResourceIds[currentPosition]);
					setImageBackground(currentPosition);
				} else {
					Toast.makeText(v.getContext(), "到了最后一张", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		}

		}

		return true;
	}

	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		if (!initFlag) {
			throw new RuntimeException("CaulImageSwitcher should be init!");
		}
		for (int i = 0; i < imgViews.length; i++) {
			if (i == selectItems) {
				// imgViews[i].setBackgroundResource();
			} else {
				// imgViews[i].setBackgroundResource();
			}
		}
	}

}
