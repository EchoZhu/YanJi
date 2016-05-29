package com.echo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.echo.base.BaseActivity;
import com.echo.user.LoginUtils;
import com.echo.utils.Share;
import com.echo.utils.ToastUtils;
import com.echo.utils.VolleyUtil;
import com.echo.yanji.R;

/**
 * 我的资料页面
 * @author zhuyikun
 *
 *  2015-10-27
 */
public class A5_myinfo extends BaseActivity {

	private ImageView iv_back_myinfo,iv_headtag;
	private RelativeLayout rl_nickname,rl_changepwd,rl_checkupdate,
						   rl_shareapp,rl_aboutus,rl_reportcomment;
	private TextView tv_nickname;
	private Switch switch_notify;
	private Button btn_logout;

	private RequestQueue mRequestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a5_myinfo);
		initUI();

		mRequestQueue = Volley.newRequestQueue(this);
		switch_notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					Log.e("test", "打开");
				} else {
					Log.e("test", "关闭");
				}
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_back_myinfo:
			this.finish();
			break;
		case R.id.iv_headtag:
			Toast.makeText(this, "iv_headtag", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_nickname:
			Toast.makeText(this, "rl_nickname", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_changepwd:
			Toast.makeText(this, "rl_changepwd", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_shareapp:
			new Share(this,"颜迹","颜迹","http://v2ex.com");
			break;
		case R.id.rl_checkupdate:
			Toast.makeText(this, "rl_checkupdate", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_aboutus:
			Toast.makeText(this, "rl_aboutus", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_reportcomment:
			Toast.makeText(this, "rl_reportcomment", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_logout:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("确认退出？");
			builder.setTitle("提示：");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					String userName = LoginUtils.getUserName(A5_myinfo.this);
					String authToken = LoginUtils.getAuthToken(A5_myinfo.this);

					if (userName!=null && authToken != null){
						VolleyUtil.toLogout(A5_myinfo.this, mRequestQueue, userName, authToken);
					}else{
						ToastUtils.show(A5_myinfo.this, "登陆状态有误，请重新登陆");
						ForwardHelper.toLogin(A5_myinfo.this);
					}
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					Log.e("123", "取消");
				}
			});
			builder.create().show();



			break;

		default:
			break;
		}
	}
	private void initUI() {
		// TODO Auto-generated method stub
		iv_back_myinfo = (ImageView) findViewById(R.id.iv_back_myinfo);
		iv_headtag = (ImageView) findViewById(R.id.iv_headtag);
		rl_nickname = (RelativeLayout) findViewById(R.id.rl_nickname);
		rl_changepwd = (RelativeLayout) findViewById(R.id.rl_changepwd);
		rl_checkupdate = (RelativeLayout) findViewById(R.id.rl_checkupdate);
		rl_shareapp = (RelativeLayout) findViewById(R.id.rl_shareapp);
		rl_aboutus = (RelativeLayout) findViewById(R.id.rl_aboutus);
		rl_reportcomment = (RelativeLayout) findViewById(R.id.rl_reportcomment);
		switch_notify = (Switch) findViewById(R.id.switch_notify);
		btn_logout = (Button)findViewById(R.id.btn_logout);
		tv_nickname = (TextView)findViewById(R.id.tv_nickname);

		setListener(iv_back_myinfo, iv_headtag, rl_nickname, rl_changepwd, rl_checkupdate,
				rl_shareapp, rl_aboutus, rl_reportcomment, btn_logout);
	}
}
