package com.echo.yanji;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.yanji_base.BaseActivity;
import com.echo.yanji_data.TitleValueEntity;
import com.echo.yanji_view.SpiderWebChart;

import java.util.ArrayList;
import java.util.List;


/**
 * 颜值各项数据展示页面
 * @author zhuyikun
 *
 *  2015-10-29
 */
public class A7_yanzhidetail extends BaseActivity {

	private ImageView  iv_back_yanzhidetail,iv_headtag;
	private TextView tv_tips;
	private Button btn_getAnalyse;
	private Intent intent;
	private String FromWhere;
	int i = 1;

	SpiderWebChart spiderwebchart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a7_yanzhidetail);
		initUI();
		initSpiderWebChart();

	}
	private void initSpiderWebChart() {
		this.spiderwebchart = (SpiderWebChart) findViewById(R.id.spiderWebChart);

		List<TitleValueEntity> data1 = new ArrayList<TitleValueEntity>();
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title1), 3));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title2), 4));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title3), 9));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title4), 8));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5), 10));

		List<List<TitleValueEntity>> data = new ArrayList<List<TitleValueEntity>>();
		data.add(data1);
		spiderwebchart.setData(data);
		spiderwebchart.setLatitudeNum(5);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_headtag:
			ForwardHelper.toMyinfo(this);
			break;
		case R.id.iv_back_yanzhidetail:
			this.finish();
			break;

		case R.id.btn_getAnalyse:
			ForwardHelper.toAnalyse(this);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			return  true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void initUI() {
		// TODO Auto-generated method stub
		iv_headtag = (ImageView) findViewById(R.id.iv_headtag);
		iv_back_yanzhidetail = (ImageView) findViewById(R.id.iv_back_yanzhidetail);
		tv_tips = (TextView) findViewById(R.id.tv_tips);
		btn_getAnalyse = (Button) findViewById(R.id.btn_getAnalyse);

		setListener(iv_headtag, btn_getAnalyse, iv_back_yanzhidetail);

		intent = getIntent();
		FromWhere = intent.getStringExtra("From");
		if (FromWhere != null){
			if (FromWhere.equalsIgnoreCase("A6_test")){
				iv_back_yanzhidetail.setVisibility(View.VISIBLE);
			}else{
				iv_back_yanzhidetail.setVisibility(View.INVISIBLE);
			}
		}

	}

}
