package com.echo.yanji;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.echo.yanji_base.BaseActivity;
import com.echo.yanji_view.CircleProgressBar;


/**
 * 颜值分析页面
 * @author zhuyikun
 *
 *  2015-10-29
 */
public class A8_analyse extends BaseActivity {

	private ImageView iv_back_analyse;
	private CircleProgressBar circleProgressbar1,circleProgressbar2,
	circleProgressbar3,circleProgressbar4,circleProgressbar5;
	private Button btn_getSolution;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a8_analyse);
		initUI();
		initData();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_back_analyse:
			this.finish();
			break;
		case R.id.btn_getSolution:
			ForwardHelper.toSolution(this);
			break;

		default:
			break;
		}
	}
	private void initData() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				circleProgressbar1.setProgressNotInUiThread(10);
				circleProgressbar2.setProgressNotInUiThread(20);
				circleProgressbar3.setProgressNotInUiThread(30);
				circleProgressbar4.setProgressNotInUiThread(40);
				circleProgressbar5.setProgressNotInUiThread(50);
			};
		}.start();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		iv_back_analyse = (ImageView) findViewById(R.id.iv_back_analyse);
		circleProgressbar1 = (CircleProgressBar) findViewById(R.id.circleProgressbar1);
		circleProgressbar2 = (CircleProgressBar) findViewById(R.id.circleProgressbar2);
		circleProgressbar3 = (CircleProgressBar) findViewById(R.id.circleProgressbar3);
		circleProgressbar4 = (CircleProgressBar) findViewById(R.id.circleProgressbar4);
		circleProgressbar5 = (CircleProgressBar) findViewById(R.id.circleProgressbar5);
		btn_getSolution = (Button) findViewById(R.id.btn_getSolution);

		setListener(iv_back_analyse,btn_getSolution);
	}
	
}
