package com.echo.yanji;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.echo.yanji_adapter.WelcomePagerAdapter;
import com.echo.yanji_base.BaseActivity;

import java.util.ArrayList;


public class A0_welcome extends BaseActivity {

	private ViewPager mViewPager;
	
	private float xDown, xMove;
	
	private int currIndex = 0;
	
	private WelcomePagerAdapter mWelcomePagerAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a0_welcome);
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
		
		LayoutInflater mInflater = LayoutInflater.from(this);
		View view1 = mInflater.inflate(R.layout.whats1, null);
		View view2 = mInflater.inflate(R.layout.whats2, null);
		View view3 = mInflater.inflate(R.layout.whats3, null);
		View view4 = mInflater.inflate(R.layout.whats4, null);
		
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		
		view4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startbutton();
			}
		});
		
		view4.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					xDown = event.getRawX();
					break;
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_UP:
					xMove = event.getRawX();
					if (xMove < xDown) {
						startbutton();
					}
					break;
				default:
					break;
				}
				
				
				return false;
			}
		});
		mWelcomePagerAdapter = new WelcomePagerAdapter(views);
		mViewPager.setAdapter(mWelcomePagerAdapter);
		
	}
	
	public void startbutton() {
		ForwardHelper.toLogin(this);
		this.finish();
	}
}
