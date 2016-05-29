package com.echo.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.echo.base.BaseActivity;
import com.echo.utils.CheckUtil;
import com.echo.utils.ToastUtils;
import com.echo.utils.VolleyUtil;
import com.echo.yanji.R;

/**
 * 登陆页面
 *
 * @author zhuyikun
 */
public class A1_login extends BaseActivity {

    private EditText et_getphonenumber, et_getpassword;
    private TextView tv_forgetpwd, tv_regist;
    private Button btn_login;

    private String phone;
    private String pwd;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_login);
        initUI();
        mRequestQueue = Volley.newRequestQueue(A1_login.this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkInput()) {
                    //请求服务器登陆接口
//					Log.e("test",phone+":"+pwd);
//					VolleyUtil.toLogin(this, mRequestQueue, phone, pwd);
                    LoginTask loginTask = new LoginTask();
                    loginTask.execute();
//                    this.finish();
                }
                break;
            case R.id.tv_regist:
                ForwardHelper.toRegist(this);
                break;
            case R.id.tv_forgetpwd:
                ForwardHelper.toForgetPWD(this);
                break;

            default:
                break;
        }
    }

    private void initUI() {
        // TODO Auto-generated method stub
        et_getphonenumber = (EditText) findViewById(R.id.et_getphonenumber);
        et_getpassword = (EditText) findViewById(R.id.et_getpassword);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
        tv_regist = (TextView) findViewById(R.id.tv_regist);

        setListener(btn_login, tv_forgetpwd, tv_regist);
    }

    public boolean checkInput() {
//		if (!AndroidUtils.checkNetwork(this)) {
//			ToastUtils.show(A1_login.this, ToastUtils.OP_ERROR_NETWORK_OUT);
//			return false;
//		}
        phone = et_getphonenumber.getText().toString();
        pwd = et_getpassword.getText().toString();

        if (CheckUtil.checkNull(phone)) {
            ToastUtils.show(A1_login.this, ToastUtils.LOGIN_MOBILE_NULL);
            return false;
        }
        if (!CheckUtil.checkMobile(phone)) {
            ToastUtils.show(this, ToastUtils.REPASSWD_MOBILE_FORMAT);
            return false;
        }
        if (CheckUtil.checkNull(pwd)) {
            ToastUtils.show(this, ToastUtils.REG_PASSWD_NULL);
            return false;
        }

        return true;
    }

    public class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            VolleyUtil.toLogin(A1_login.this, mRequestQueue, phone, pwd);
            return null;
        }
    }
}
