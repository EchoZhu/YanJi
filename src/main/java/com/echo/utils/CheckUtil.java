/**
 * @(#)CheckUtil.java, 2015年3月29日. Copyright 2012 Yodao, Inc.
 *                                     All rights reserved. YODAO
 *                                     PROPRIETARY/CONFIDENTIAL. Use is subject
 *                                     to license terms.
 */
package com.echo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lukun
 * 
 */
public class CheckUtil {

	public static boolean checkSame(String str1, String str2) {
		return str1 != null && str1.equalsIgnoreCase(str2);
	}
	
	public static boolean checkBuyNumInt(String str) {
		try {
			int i = Integer.parseInt(str);
			if(i>9999999||i<=0){
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean checkPrice(String str) {
		try {
			double i = Double.parseDouble(str);
			if(i>9999999||i<=0){
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean checkServicePrice(String str) {
		try {
			double i = Double.parseDouble(str);
			if(i>9999999||i<0){
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean checkDouble(String str) {
		try {
			double i = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean checkInt(String str) {
		try {
			int i = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean checkNull(String str) {
		return Strings.isEmpty(str);
	}

	public static boolean checkLt(String startDate, String endDate) {
		long start = TimeUtil.getTime(startDate, "yyyy-MM-dd");
		long end = TimeUtil.getTime(endDate, "yyyy-MM-dd");
		if (start < end) {
			return true;
		}
		return false;
	}

	public static boolean checkMobile(String str) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	/**
	 * 判断验证码是否为 6 位纯数字，LeanCloud 统一的验证码均为 6  位纯数字。
	 * @param smsCode
	 * @return
	 */
	public static boolean isSMSCodeValid(String smsCode) {
		String regex = "^\\d{6}$";
		return smsCode.matches(regex);
	}
}
