package com.echo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.echo.base.BaseActivity;
import com.echo.utils.Share;
import com.echo.view.ChartView;
import com.echo.yanji.R;

/**
 * 历史纪录页面
 */
public class A10_history extends BaseActivity {
    private TextView tv_week,tv_month,tv_year,tv_color,tv_zhi,tv_run,tv_ze,tv_qixue,tv_mycolor,tv_myhistory;
    private Button btn_share;
    private RelativeLayout relativeLayout1;
    private ChartView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a10_history);
        initUI();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_week:
                setBackground1(tv_week, tv_month, tv_year);
                setChart(relativeLayout1,myView,
                        new String[]{"周一","周二","周三","周四","周五","周六","周日"},
                        new String[]{"","2","4","6","8","10"},
                        new String[]{"1","2","4","3","6","8","9"});
                break;
            case R.id.tv_month:
                setBackground1(tv_month, tv_year, tv_week);
                relativeLayout1.removeAllViews();
                myView.SetInfo(
                        new String[]{"1号","5号","10号","15号","20号","25号","30号"},   //X轴刻度
                        new String[]{"","2","4","6","8","10"},   //Y轴刻度
                        new String[]{"6","9","10","2","1","0","9"},  //数据
                        ""
                );
                relativeLayout1.addView(myView);

                break;
            case R.id.tv_year:
                setBackground1(tv_year,tv_month,tv_week);
                relativeLayout1.removeAllViews();
                myView.SetInfo(
                        new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"},   //X轴刻度
                        new String[]{"","2","4","6","8","10"},   //Y轴刻度
                        new String[]{"3","4","5","6","7","8","9"},  //数据
                        ""
                );
                relativeLayout1.addView(myView);
                break;

            case R.id.tv_color:
                setBackground2(tv_color, tv_zhi, tv_run, tv_ze, tv_qixue);
                break;
            case R.id.tv_zhi:
                setBackground2(tv_zhi, tv_color, tv_run, tv_ze, tv_qixue);
                break;
            case R.id.tv_run:
                setBackground2(tv_run, tv_zhi, tv_color, tv_ze, tv_qixue);
                break;
            case R.id.tv_ze:
                setBackground2(tv_ze, tv_zhi, tv_run, tv_color, tv_qixue);
                break;
            case R.id.tv_qixue:
                setBackground2(tv_qixue, tv_zhi, tv_run, tv_ze, tv_color);
                break;



            case R.id.btn_share:
//                Share.invit(this, ShareContent, ShareTitle, ShareUrl);
                new Share(A10_history.this,"我用颜迹检测了我的皮肤，你也来试一试吧","颜迹","www.baidu.com");
                break;
            default:
                break;
        }
    }

    /**
     * 设置折线图
     * @param layoutView
     * @param myView
     * @param xLable
     * @param yLable
     * @param datas
     */
    private void setChart(ViewGroup layoutView,ChartView myView,String[] xLable,String[] yLable,String[] datas) {
        layoutView.removeAllViews();
        myView.SetInfo(
                xLable,   //X轴刻度
                yLable,   //Y轴刻度
                datas,    //数据
                ""        //标题
        );
        layoutView.addView(myView);
    }

    private void setBackground2(TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5) {
        tv1.setBackgroundColor(Color.parseColor("#a78aec"));
        tv2.setBackgroundColor(Color.parseColor("#d99df4"));
        tv3.setBackgroundColor(Color.parseColor("#d99df4"));
        tv4.setBackgroundColor(Color.parseColor("#d99df4"));
        tv5.setBackgroundColor(Color.parseColor("#d99df4"));

    }

    private void setBackground1(View v1,View v2,View v3) {
        v1.setBackgroundColor(Color.parseColor("#d99df4"));
        v2.setBackgroundColor(Color.parseColor("#fafafa"));
        v3.setBackgroundColor(Color.parseColor("#fafafa"));

        ((TextView)v1).setTextColor(Color.parseColor("#ffffff"));
        ((TextView)v2).setTextColor(Color.parseColor("#000000"));
        ((TextView)v3).setTextColor(Color.parseColor("#000000"));
    }

    private void initUI() {
        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        tv_week = (TextView) findViewById(R.id.tv_week);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_year = (TextView) findViewById(R.id.tv_year);
        tv_color = (TextView) findViewById(R.id.tv_color);
        tv_zhi = (TextView) findViewById(R.id.tv_zhi);
        tv_run = (TextView) findViewById(R.id.tv_run);
        tv_ze = (TextView) findViewById(R.id.tv_ze);
        tv_qixue = (TextView) findViewById(R.id.tv_qixue);
        btn_share = (Button) findViewById(R.id.btn_share);

        myView=new ChartView(this);
        myView.SetInfo(
                new String[]{"周一","周二","周三","周四","周五","周六","周日"},   //X轴刻度
                new String[]{"","2","4","6","8","10"},   //Y轴刻度
                new String[]{"1","2","4","3","6","8","9"},  //数据
                ""
        );
        relativeLayout1.addView(myView);

        setListener(tv_week, tv_month, tv_year,
                tv_color, tv_zhi, tv_run, tv_ze, tv_qixue, btn_share);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
