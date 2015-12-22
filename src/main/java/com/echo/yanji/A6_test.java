package com.echo.yanji;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.yanji_base.BaseActivity;
import com.echo.yanji_utils.ToastUtils;
import com.echo.yanji_view.RoundImageView;

/**
 * 检测图像页面
 * @author zhuyikun
 *
 *  2015-10-28
 */
public class A6_test extends BaseActivity  {

	private ImageView iv_myinfo_test,iv_pic_a,iv_pic_b,iv_pic_c;
	private Button btn_takephoto,btn_getyanzhi;
	private TextView tv_tips;
	private long firstTime = 0;
	private RoundImageView iv_mirror,iv_mirror1;

	private boolean PHOTOISTAKED = false;
	private Handler mHandle;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a6_test);
		initUI();
		setOnclick();
		/**
		 * 图像处理线程
		 */
		mHandle = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int[] color = (int[]) msg.obj;
				iv_mirror1.setBackgroundColor(Color.argb(color[3],color[0], color[1], color[2]));
				progressDialog.dismiss();
			}
		};
		new myThread().run();
		progressDialog.setTitle("正在处理，请稍后");
		progressDialog.show();
	}

	/**
	 * 图像处理线程
	 */
	class myThread implements Runnable {
		public void run() {
				BitmapDrawable db = (BitmapDrawable) getResources().getDrawable(
						R.mipmap.mirror);
				Bitmap bitmap = db.getBitmap();
				int width = bitmap.getWidth();
				int hight = bitmap.getHeight();
				int[] pixs = new int[width*hight];
			    int sum_r = 0;
			    int sum_g = 0;
			    int sum_b = 0;
			    int sum_a = 0;
			    int cunt = 0;
				bitmap.getPixels(pixs, 0, width, 0, 0, width, hight);
				for (int i =0;i<width*hight;i++){
					int r = Color.red(pixs[i]);
					int g = Color.green(pixs[i]);
					int b = Color.blue(pixs[i]);
					int a = Color.alpha(pixs[i]);

					//去除黑色点
					if (r!=0 && g!=0 && b!=0){
						sum_r+=r;
						sum_g+=g;
						sum_b+=b;
						sum_a+=a;
						cunt++;
					}
				}
				int[] color = new int[4];
				color[0] = sum_r/cunt;
				color[1] = sum_g/cunt;
				color[2] = sum_b/cunt;
				color[3] = sum_a/cunt;

				Message message = new Message();
				message.obj = color;
				mHandle.sendMessage(message);
		}
	}
	private void setOnclick() {
		iv_myinfo_test.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ForwardHelper.toMyinfo(A6_test.this);
			}
		});
		btn_takephoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PHOTOISTAKED = true;
				if (PHOTOISTAKED) {
					btn_getyanzhi.setVisibility(View.VISIBLE);
					tv_tips.setVisibility(View.GONE);
					btn_takephoto.setText("重新拍照");
				}
			}
		});
		btn_getyanzhi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(A6_test.this, com.echo.yanji.A7_yanzhidetail.class);
				intent.putExtra("From", "A6_test");
				startActivity(intent);
				overridePendingTransition(R.anim.activity_open_in_anim,
						R.anim.activity_open_out_anim);
			}
		});

	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 2000){
				ToastUtils.show(getApplicationContext(), "再按一次退出程序");
				firstTime = secondTime;
				return  true;
			}
			((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).restartPackage(getPackageName());
			finish();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}
	private void initUI() {
		// TODO Auto-generated method stub
		iv_mirror = (RoundImageView) findViewById(R.id.iv_mirror);
		iv_mirror1 = (RoundImageView) findViewById(R.id.iv_mirror1);
//		iv_mirror1.bringToFront();
		iv_myinfo_test = (ImageView) findViewById(R.id.iv_myinfo_test);
		iv_pic_a = (ImageView) findViewById(R.id.iv_pic_a);
		iv_pic_b = (ImageView) findViewById(R.id.iv_pic_b);
		iv_pic_c = (ImageView) findViewById(R.id.iv_pic_c);
		btn_takephoto = (Button) findViewById(R.id.btn_takephoto);
		btn_getyanzhi = (Button) findViewById(R.id.btn_getyanzhi);
		tv_tips = (TextView) findViewById(R.id.tv_tips);
		progressDialog = new ProgressDialog(this);

	}
}
