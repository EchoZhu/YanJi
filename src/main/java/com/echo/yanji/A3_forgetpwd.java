package com.echo.yanji;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.yanji_base.BaseActivity;


/**
 * 忘记密码页
 * @author zhuyikun
 *
 */
public class A3_forgetpwd extends BaseActivity {

	private ImageView iv_back_forget;
	private EditText et_getphonenumber_f,et_resetpwd,et_getverifycode;
	private Button btn_resetpwd;
	private TextView tv_getverifycode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a3_forgetpwd);
		initUI();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_back_forget:
			this.finish();
			break;
		case R.id.tv_getverifycode:
			Toast.makeText(this, "tv_getverifycode", 300).show();
			break;
		case R.id.btn_resetpwd:
			Toast.makeText(this, "btn_getverifycode", 300).show();
			break;

		default:
			break;
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		iv_back_forget = (ImageView) findViewById(R.id.iv_back_forget);
		et_getphonenumber_f = (EditText) findViewById(R.id.et_getphonenumber_f);
		et_resetpwd = (EditText) findViewById(R.id.et_resetpwd);
		et_getverifycode = (EditText) findViewById(R.id.et_getverifycode);
		tv_getverifycode = (TextView) findViewById(R.id.tv_getverifycode);
		btn_resetpwd = (Button) findViewById(R.id.btn_resetpwd);
		
		setListener(iv_back_forget,tv_getverifycode,btn_resetpwd);
		
	}
}
