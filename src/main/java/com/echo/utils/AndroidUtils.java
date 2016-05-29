package com.echo.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.echo.yanji.R;

import java.util.Calendar;
import java.util.Locale;


/**
 * @author lukun
 */
public class AndroidUtils {

	public static void bindDatePicker(final TextView dateEdit) {
		// 日期选择框
		final Calendar startDateCalendar = Calendar.getInstance(Locale.CHINA);
		final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				startDateCalendar.set(Calendar.YEAR, year);
				startDateCalendar.set(Calendar.MONTH, monthOfYear);
				startDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				int month = (startDateCalendar.get(Calendar.MONTH) + 1);
				int day = startDateCalendar.get(Calendar.DAY_OF_MONTH);
				String head = startDateCalendar.get(Calendar.YEAR) + "-"
						+ (month < 10 ? "0" + month : month) + "-"
						+ (day < 10 ? "0" + day : day);
				dateEdit.setText(head);
			}
		};
		// 日期选择框
		View.OnClickListener focusListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String dateText = dateEdit.getText().toString();
				if(TextUtils.isEmpty(dateText)){
					dateText = TimeUtil.getDate(System.currentTimeMillis());
				}
				if (dateText != null && !dateText.trim().equals("")) {
					String dates[] = dateText.split("-");
					if (dates.length == 3) {
						int year = Integer.parseInt(dates[0]);
						int month = Integer.parseInt(dates[1]);
						int day = Integer.parseInt(dates[2]);
						new DatePickerDialog(dateEdit.getContext(),
								dateSetListener, year, month - 1, day).show();
					}
				}
			}
		};
		dateEdit.setOnClickListener(focusListener);
	}

	/*
	 * 是否有版本更新
	 */
	public static boolean isUpdate(int lastestVersionCode, Context context) {
		int vercode = PackageUtil.getVerCode(context);
		if (lastestVersionCode  > vercode) {
			return true;
		}
		return false;
	}

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	public static boolean checkNetwork(Context context) {
		if (context != null) {
			if (!isNetworkConnected(context)) {
				ToastUtils.show(R.string.network_not_connected);
				return false;
			}
		}
		return true;
	}
}