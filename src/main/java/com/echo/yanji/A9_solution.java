package com.echo.yanji;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.yanji_base.BaseActivity;


/**
 * 解决方案页面
 * @author zhuyikun
 *
 *  2015-10-30
 */
public class A9_solution extends BaseActivity {

	private ImageView iv_back_solution,iv_home;
	private TextView tv_recommend_product_detail,tv_recommend_diet_detail,tv_recommend_live_detail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a9_solution);
		initUI();

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()){
			case R.id.iv_back_solution:
				this.finish();
				break;
			case R.id.iv_home:
				ForwardHelper.toTable(this);
				break;

		}
	}

	private void initUI() {
		iv_back_solution = (ImageView) findViewById(R.id.iv_back_solution);
		iv_home = (ImageView) findViewById(R.id.iv_home);

		tv_recommend_product_detail = (TextView) findViewById(R.id.tv_recommend_product_detail);
		tv_recommend_diet_detail = (TextView) findViewById(R.id.tv_recommend_diet_detail);
		tv_recommend_live_detail = (TextView) findViewById(R.id.tv_recommend_live_detail);

		setListener(iv_back_solution,iv_home);
	}


}
