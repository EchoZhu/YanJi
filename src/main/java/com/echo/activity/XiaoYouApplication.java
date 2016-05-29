/**
 * @(#)XiaoYouAppction.java, 2015年4月3日. Copyright 2012 Yodao, Inc.
 *                                     All rights reserved. YODAO
 *                                     PROPRIETARY/CONFIDENTIAL. Use is subject
 *                                     to license terms.
 */
package com.echo.activity;

import android.app.Application;


/**
 * @author zyk
 * 
 */
public class XiaoYouApplication extends Application {

	private static XiaoYouApplication xiaoYouAppction;

	@Override
	public void onCreate() {
		super.onCreate();
//		ImageViewerHelper.initialImageViewConfig(getApplicationContext());
		xiaoYouAppction = this;
	}

	public static XiaoYouApplication getInstance() {
		return xiaoYouAppction;
	}
}
