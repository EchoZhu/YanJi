package com.echo.yanji;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.echo.yanji_base.BaseTabActivity;
import com.echo.yanji_utils.ToastUtils;

/**
 * 检测图像页面
 * @author zhuyikun
 *
 *  2015-10-28
 */
public class B0_table extends BaseTabActivity {


	public TabHost m_tab;
	private Intent intent_1;
	private Intent intent_2;
	private Intent intent_3;
	// 单选按钮组
	private RadioGroup m_rgroup;
	// 5个单选按钮
	private RadioButton m_radio_woyan;
	private RadioButton m_radio_lishi;
	private RadioButton m_radio_test;


	
	private long firstTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b6_table);
		m_tab = B0_table.this.getTabHost();
		initUI();
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

//			((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).restartPackage(getPackageName());
//			finish();
//			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);

		}
		return super.onKeyDown(keyCode, event);
	}

	private void initUI() {
		// TODO Auto-generated method stub

		intent_1 = new Intent(this, A7_yanzhidetail.class);
		intent_1.putExtra("From","B0_table");
		intent_2 = new Intent(this, A10_history.class);
		intent_3 = new Intent(this, A6_test.class);

		m_tab.addTab(buildTagSpec("test1", 0, intent_1));
		m_tab.addTab(buildTagSpec("test2", 1, intent_2));
		m_tab.addTab(buildTagSpec("test3", 2, intent_3));

		m_rgroup = (RadioGroup) findViewById(R.id.radiogroup);

		m_radio_woyan = (RadioButton) findViewById(R.id.tab_radio1);
		m_radio_lishi = (RadioButton) findViewById(R.id.tab_radio2);
		m_radio_test = (RadioButton) findViewById(R.id.tab_radio3);

		m_rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == m_radio_woyan.getId()) {
					m_tab.setCurrentTabByTag("test1");
				} else if (checkedId == m_radio_lishi.getId()) {
					m_tab.setCurrentTabByTag("test2");
				}else if (checkedId == m_radio_test.getId()) {
					m_tab.setCurrentTabByTag("test3");
				}
			}
		});
		m_tab.setCurrentTab(2);
		
	}
	private TabHost.TabSpec buildTagSpec(String tagName, int tagLable,
										 Intent content) {
		return m_tab.newTabSpec(tagName).setIndicator(tagLable + "")
				.setContent(content);
	}
}
