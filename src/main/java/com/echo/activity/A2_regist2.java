package com.echo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.echo.base.BaseActivity;
import com.echo.utils.AndroidUtils;
import com.echo.utils.CheckUtil;
import com.echo.utils.ToastUtils;
import com.echo.yanji.R;


public class A2_regist2 extends BaseActivity {

	private ImageView iv_back_regist2;
	private EditText et_setnickname,et_chosegender,et_getage;
	private Button btn_regist;

	private String nickName;
	private String gender;
	private String age;
	private AVUser avUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a2_regist2);
		initUI();
		avUser = AVUser.getCurrentUser();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_back_regist2:
			this.finish();
			break;
		case R.id.btn_regist:
			if(checkInput()){
				avUser.put("nickName",nickName);
				avUser.put("age", age);
				avUser.put("gender", gender);
				avUser.saveInBackground(new SaveCallback() {
					@Override
					public void done(AVException e) {
						if (e == null) {
							ToastUtils.show(A2_regist2.this,"注册成功");
							ForwardHelper.toLogin(A2_regist2.this);
						} else {
							ToastUtils.show(getApplicationContext(), "注册失败,请重新注册");
						}
					}
				});

			}
			break;

		default:
			break;
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		iv_back_regist2 = (ImageView) findViewById(R.id.iv_back_regist2);
		et_setnickname = (EditText) findViewById(R.id.et_setnickname);
		et_chosegender = (EditText) findViewById(R.id.et_chosegender);
		et_getage = (EditText) findViewById(R.id.et_getage);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		
		setListener(iv_back_regist2, btn_regist);
	}
	public boolean checkInput() {
		if (!AndroidUtils.checkNetwork(this)) {
			ToastUtils
					.show(A2_regist2.this, ToastUtils.OP_ERROR_NETWORK_OUT);
			return false;
		}
		nickName = et_setnickname.getText().toString();
		gender = et_chosegender.getText().toString();
		age = et_getage.getText().toString();

		if (CheckUtil.checkNull(nickName)) {
			ToastUtils.show(A2_regist2.this, ToastUtils.NICKNAME_NAME_NULL);
			return false;
		}
		if (CheckUtil.checkNull(gender)) {
			ToastUtils.show(A2_regist2.this, ToastUtils.GENDER_NULL);
			return false;
		}
		if (CheckUtil.checkNull(age)) {
			ToastUtils.show(A2_regist2.this, ToastUtils.AGE_NULL);
			return false;
		}


		return true;
	}
}
