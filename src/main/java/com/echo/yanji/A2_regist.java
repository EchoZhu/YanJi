package com.echo.yanji;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.echo.yanji_base.BaseActivity;
import com.echo.yanji_data.YanJiUser;
import com.echo.yanji_utils.AndroidUtils;
import com.echo.yanji_utils.CheckUtil;
import com.echo.yanji_utils.ToastUtils;
import com.echo.yanji_utils.VolleyUtil;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 注册页面
 * @author zhuyikun
 *
 */
public class A2_regist extends BaseActivity {

	private ImageView iv_back_regist;
	private EditText et_getphonenumber_r,et_setpwd1,et_setpwd2,et_getverifycode;
	private TextView tv_getverifycode;
	private Button btn_nextstep;

	private static long lastSendValidCodeTime;
	private String mobilePhone = null;
	private String verifyCode = null;
	private String mobileText = null;
	private String password = null;

	private YanJiUser user;
	private AVUser avUser;

	private RequestQueue mRequestQueue;


	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a2_regist);
		initUI();
		avUser = new AVUser();
		user = new YanJiUser();

		mRequestQueue =  Volley.newRequestQueue(A2_regist.this);

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_back_regist:
			this.finish();
			break;
		case R.id.tv_getverifycode://获取验证码
			if(checkSendValidCodeInput()){
				lastSendValidCodeTime = System.currentTimeMillis();
				disableValidBtn();
				getValidCode();
			}
			break;
		case R.id.btn_nextstep://下一步
			if(checkInput())
			{
				verifyCode(mobilePhone, verifyCode);
		    }
			break;

		default:
			break;
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		iv_back_regist = (ImageView) findViewById(R.id.iv_back_regist);
		et_getphonenumber_r = (EditText) findViewById(R.id.et_getphonenumber_r);
		et_setpwd1 = (EditText) findViewById(R.id.et_setpwd1);
		et_setpwd2 = (EditText) findViewById(R.id.et_setpwd2);
		et_getverifycode = (EditText) findViewById(R.id.et_getverifycode);
		tv_getverifycode = (TextView) findViewById(R.id.tv_getverifycode);
		btn_nextstep = (Button) findViewById(R.id.btn_nextstep);

		setListener(iv_back_regist, tv_getverifycode, btn_nextstep);
		
	}
	public boolean checkSendValidCodeInput() {
		if (!AndroidUtils.checkNetwork(this)) {
			ToastUtils.show(this, ToastUtils.OP_ERROR_NETWORK_OUT);
			return false;
		}
		String mobileText = et_getphonenumber_r.getText().toString();

		if (CheckUtil.checkNull(mobileText)) {
			ToastUtils.show(this, ToastUtils.REPASSWD_MOBILE_NULL);
			return false;
		}
		if (!CheckUtil.checkMobile(mobileText)) {
			ToastUtils.show(this, ToastUtils.REPASSWD_MOBILE_FORMAT);
			return false;
		}

		return true;
	}

	/**
	 * 检查输入
	 * 电话号码，输入是否为空，是否为合法号码
	 * 密码是否为空，前后两次输入是否一致
	 * @return
	 */
	public boolean checkInput() {
		if (!AndroidUtils.checkNetwork(this)) {
			ToastUtils
					.show(A2_regist.this, ToastUtils.OP_ERROR_NETWORK_OUT);
			return false;
		}
		mobilePhone = et_getphonenumber_r.getText().toString();
		verifyCode = et_getverifycode.getText().toString();

		if (CheckUtil.checkNull(mobilePhone)) {
			ToastUtils.show(A2_regist.this, ToastUtils.LOGIN_MOBILE_NULL);
			return false;
		}
		if (!CheckUtil.checkMobile(mobilePhone)) {
			ToastUtils.show(this, ToastUtils.REPASSWD_MOBILE_FORMAT);
			return false;
		}
		String pwd1 = et_setpwd1.getText().toString();
		String pwd2 = et_setpwd2.getText().toString();
		if (CheckUtil.checkNull(pwd1)){
			ToastUtils.show(A2_regist.this, ToastUtils.REG_PASSWD_NULL);
			return false;
		}
		if(!CheckUtil.checkSame(pwd1, pwd2)){
			ToastUtils.show(A2_regist.this, ToastUtils.REG_PASSWD_SAME);
			return false;
		}else{
			password = pwd2;
		}
		if (CheckUtil.checkNull(verifyCode)) {
			ToastUtils.show(A2_regist.this, ToastUtils.REG_VALID_NULL);
			return false;
		}
		//判断验证码是否为 6 位纯数字
		if (!CheckUtil.isSMSCodeValid(verifyCode)) {
			ToastUtils.show(A2_regist.this, ToastUtils.REG_VALID_ILLEGAL);
			return false;
		}

		return true;
	}

	/**
	 * 显示倒计时
	 */
	public void disableValidBtn() {
		tv_getverifycode.setEnabled(false);
		et_getphonenumber_r.setEnabled(false);
		final Timer timer = new Timer();
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				tv_getverifycode.setText("请等待(" + (60 - getInterval())
						+ "s)");
				if (getInterval() > 60) {
					tv_getverifycode.setText("获取验证码");
					tv_getverifycode.setEnabled(true);
					et_getphonenumber_r.setEnabled(true);
					timer.cancel();
				}
			}
		};
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		timer.schedule(task, 100, 1000);
	}
	public long getInterval() {
		return (System.currentTimeMillis() - lastSendValidCodeTime) / 1000;
	}

	/**
	 * 获取验证码
	 */
	public void getValidCode() {
		mobileText = et_getphonenumber_r.getText().toString();

		//请求短信验证码
		new AsyncTask<Void, Void, Void>() {
			boolean res;

			@Override
			protected Void doInBackground(Void... params) {
				try {
					AVOSCloud.requestSMSCode(mobileText, "颜迹", "注册", 10);
					res = true;
				} catch (AVException e) {
					e.printStackTrace();
					res = false;
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				super.onPostExecute(aVoid);
				if (res) {
					ToastUtils.show(getApplicationContext(), "发送成功");
				} else {
					ToastUtils.show(getApplicationContext(), "发送失败");
				}
			}
		}.execute();

	}

	/**
	 * 验证短信验证码正确性
	 * @param phone
	 * @param code
	 */
	private void verifyCode(String phone,String code) {
		AVOSCloud.verifySMSCodeInBackground(code, phone,
				new AVMobilePhoneVerifyCallback() {
					@Override
					public void done(AVException e) {
						if (e == null) {
							user.setMobile(mobilePhone);
							user.setLoginCode(password);
							user.setType("registed");

							avUser.setUsername(mobilePhone);
							avUser.setMobilePhoneNumber(mobilePhone);
							avUser.setPassword(password);
							avUser.signUpInBackground(new SignUpCallback() {
								public void done(AVException e) {
									if (e == null) {
										// successfully
										VolleyUtil.toRegist(A2_regist.this,mRequestQueue,mobilePhone,password);
									} else {
										ToastUtils.show(getApplicationContext(), "网络连接异常，请检查网络连接。");
									}
								}
							});

						} else {
							e.printStackTrace();
							ToastUtils.show(getApplicationContext(), "验证码不正确");
						}
					}

				});
	}
}
