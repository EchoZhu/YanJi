package com.echo.yanji;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.echo.yanji_base.BaseActivity;


/**
 * 连接设备页面
 * 
 * @author zhuyikun
 *
 *  2015-10-27
 */
public class A4_conect extends BaseActivity {

	private ImageView iv_headtag;
	private Button btn_conect,btn_setwifi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a4_conect);
		initUI();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_headtag:
			ForwardHelper.toMyinfo(this);
			break;
		case R.id.btn_conect:
//			ForwardHelper.toTable(this);
			Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
			startActivity(intent);
			break;
		case R.id.btn_setwifi:
			Intent intent1 =  new Intent(Settings.ACTION_WIFI_SETTINGS);
			startActivity(intent1);

			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode){
			return  false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void initUI() {
		// TODO Auto-generated method stub
		iv_headtag = (ImageView) findViewById(R.id.iv_headtag);
		btn_conect = (Button) findViewById(R.id.btn_conect);
		btn_setwifi = (Button) findViewById(R.id.btn_setwifi);

		setListener(iv_headtag,btn_conect,btn_setwifi);
	}
}
