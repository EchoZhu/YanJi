/**
 * @(#)PackageUtil.java, 2013年10月25日. Copyright 2012 Yodao, Inc.
 *                                     All rights reserved. YODAO
 *                                     PROPRIETARY/CONFIDENTIAL. Use is subject
 *                                     to license terms.
 */
package com.echo.utils;

/**
 * @author lukun
 *
 */

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.echo.yanji.R;


public class PackageUtil {
	
	private static final String TAG = "PackageUtil";

	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					"com.tata.xiaoyou", 0).versionCode;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		return verCode;
	}

	public static String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					"com.tata.xiaoyou", 0).versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		return verName;

	}

	public static String getAppName(Context context) {
		String verName = context.getResources().getText(R.string.app_name)
				.toString();
		return verName;
	}
}