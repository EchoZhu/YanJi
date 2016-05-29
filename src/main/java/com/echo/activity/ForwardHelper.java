package com.echo.activity;

import android.app.Activity;
import android.content.Intent;

import com.echo.yanji.R;

public class ForwardHelper {
	/**
	 * 跳转到 欢迎页（轮播页面）
	 * @param activity
	 */
	public static void toWelcomeViewPager(Activity activity) {
		Intent in = new Intent(activity, WelcomeActivity.class);
		activity.startActivity(in);
		activity.overridePendingTransition(R.anim.activity_open_in_anim,
				R.anim.activity_open_out_anim);
	}
	/**
	 * 跳转到 登陆页
	 * @param activity
	 */
	public static void toLogin(Activity activity){
		Intent intent = new Intent(activity,A1_login.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
	}
	/**
	 * 跳转到 注册页
	 * @param activity
	 */
	
	public static void toRegist(Activity activity){
		Intent intent = new Intent(activity,A2_regist.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
		
	}
	
	/**
	 * 跳转到 注册页 第二页
	 * @param activity
	 */
	public static void toRegist2(Activity activity){
		Intent intent = new Intent(activity,A2_regist2.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
		
	}
	
	/**
	 *  跳转到 重新设置密码页面
	 * @param activity
	 */
	public static void toForgetPWD(Activity activity){
		Intent intent = new Intent(activity,A3_forgetpwd.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
		
	}
	
	/**
	 *  跳转到 连接设备页面
	 * @param activity
	 */
	public static void toConect(Activity activity){
		Intent intent = new Intent(activity,A4_conect.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
		
	}
	
	/**
	 *  跳转到 我的资料页面
	 * @param activity
	 */
	public static void toMyinfo(Activity activity){
		Intent intent = new Intent(activity,A5_myinfo.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
	}
	
	/**
	 *  跳转到 检测图像页面
	 * @param activity
	 */
	public static void toTest(Activity activity){
		Intent intent = new Intent(activity,A6_test.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim,
				R.anim.activity_open_out_anim);
		activity.finish();
	}
	/**
	 *  返回到 检测图像页面
	 * @param activity
	 */
	public static void backToTest(Activity activity){
		Intent intent = new Intent(activity,B0_table.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_close_in_anim,
				R.anim.activity_close_out_anim);
		activity.finish();
	}
	/**
	 *  跳转到 颜值各项数据展示界面
	 * @param activity
	 */
	public static void toYanzhiDetail(Activity activity){
		Intent intent = new Intent(activity,A7_yanzhidetail.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim,
				R.anim.activity_open_out_anim);
	}
	/**
	 *  跳转到 颜值分析页面
	 * @param activity
	 */
	public static void toAnalyse(Activity activity){
		Intent intent = new Intent(activity,A8_analyse.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
	}
	/**
	 *  跳转到 解决方案页面
	 * @param activity
	 */
	public static void toSolution(Activity activity){
		Intent intent = new Intent(activity,A9_solution.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim, 
				R.anim.activity_open_out_anim);
	}
	/**
	 *  跳转到 历史页面
	 * @param activity
	 */
	public static void toHistory(Activity activity){
		Intent intent = new Intent(activity,A10_history.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim,
				R.anim.activity_open_out_anim);
	}
	/**
	 *  跳转到 table页面
	 * @param activity
	 */
	public static void toTable(Activity activity){
		Intent intent = new Intent(activity,B0_table.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_open_in_anim,
				R.anim.activity_open_out_anim);
		activity.finish();
	}

}
