package com.echo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.echo.base.BaseActivity;
import com.echo.user.LoginUtils;
import com.echo.yanji.R;

public class MainActivity extends BaseActivity {

	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化leancloud
		AVOSCloud.initialize(MainActivity.this, "S3rOnJxuFKH8tw3Km4yNGXMg", "671WNxuA62dB1ejGNADsVpCe");
		//追踪程序打开情况
		AVAnalytics.trackAppOpened(getIntent());
		mHandler = new Handler(){
			public void handleMessage(Message msg) {
				loginFoward();
			};
		};
		startSplashActivity();
	}
	/**
	 * 一秒钟延迟
	 */
	private void startSplashActivity() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mHandler.sendMessage(mHandler.obtainMessage());
			}
		}).start();
	}
		/**
		 *  如果是第一次登陆，则展示三张欢迎动画
		 *	如果不是第一次登陆，则展示一张图片
		 */
		public void loginFoward() {
			if (LoginUtils.isFirstIn(this)) {
				ForwardHelper.toWelcomeViewPager(this);
				LoginUtils.setFirstIn(this);//修改登陆状态
			} else {
				String isLogined = LoginUtils.getLoginState(this);
				if (isLogined.equals("0")){
					ForwardHelper.toLogin(this);
				}else{
					ForwardHelper.toConect(this);
//					ForwardHelper.toTable(this);
				}
			}
			finish();
		}
}
