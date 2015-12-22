package com.echo.yanji_user;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.echo.yanji_utils.SharedPreferencesHelper;


public class LoginUtils {
	public static boolean isFirstIn(Context context) {
		String fistIn = SharedPreferencesHelper.getSharedPreferences(context)
				.getString("first_in", "0");
		if ("0".equalsIgnoreCase(fistIn)) {
			return true;
		} else {
			return false;
		}
	}

	public static void setFirstIn(Context context) {
		Editor editor = SharedPreferencesHelper.getSharedPreferences(context)
				.edit();
		editor.putString("first_in", "1");
		editor.commit();
	}

	public static void isLogined(Context context) {
		Editor editor = SharedPreferencesHelper.getSharedPreferences(context)
				.edit();
		editor.putString("isLogined", "1");
		editor.commit();
	}
	public static String getLoginState(Context context) {
		String isLogined = SharedPreferencesHelper.getSharedPreferences(context)
				.getString("isLogined", "0");
		return isLogined;
	}
	public static void setAuthToken(Context context,String authToken) {
		Editor editor = SharedPreferencesHelper.getSharedPreferences(context)
				.edit();
		editor.putString("authToken", authToken);
		editor.commit();
	}
	public static String getAuthToken(Context context) {
		String authToken = SharedPreferencesHelper.getSharedPreferences(context)
				.getString("authToken", null);
		return authToken;
	}

	public static void setUserName(Context context,String username) {
		Editor editor = SharedPreferencesHelper.getSharedPreferences(context)
				.edit();
		editor.putString("username", username);
		editor.commit();
	}
	public static String getUserName(Context context) {
		String authToken = SharedPreferencesHelper.getSharedPreferences(context)
				.getString("username", null);
		return authToken;
	}
}
