/**
 * @(#)TimeUtil.java, 2014年10月14日. Copyright 2012 Yodao, Inc.
 *                                     All rights reserved. YODAO
 *                                     PROPRIETARY/CONFIDENTIAL. Use is subject
 *                                     to license terms.
 */
package com.echo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lukun
 * 
 */
public class TimeUtil {

	/*
	 * 获取最新刷新时间
	 */
	public static String getRefreshTime() {
		return MsgTimeUtil.getChatRefreshTime(System.currentTimeMillis())
				+ " 刷新";
	}

	/*
	 * 获取时间段长度
	 */
	public static String getTimestr(long sec, long usec) {
		if (usec < 0) {// 除0的情况
			sec -= 1;
			usec += (1000000);
		}
		if (sec < 60) {
			return sec + " 秒" + usec + "微秒";
		} else if (sec < 3600) {
			return (sec / 60) + "分" + getTimestr(sec % 60, usec);
		} else if (sec < 86400) {
			return (sec / 3600) + "时" + getTimestr(sec % 3600, usec);
		} else {
			return (sec / 86400) + "天" + getTimestr(sec % 86400, usec);
		}
	}

	/*
	 * 将数据包的时间转化为实体时间 sec,usec
	 */
	public static String getTime(long sec) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String r = "";
		Date d = new Date();
		d.setTime(sec);
		r = sdf.format(d);
		return r;
	}

	public static String getTime(long sec, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String r = "";
		Date d = new Date();
		d.setTime(sec);
		r = sdf.format(d);
		return r;
	}

	public static long getDateTime(String time, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String r = "";
		Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			return Long.parseLong(time);
		}
		return d.getTime();
	}

	public static long getTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String r = "";
		Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.getTime();
	}

	public static long getTime(String time, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String r = "";
		Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.getTime();
	}

	/*
	 * 日期获取 按照毫秒
	 */
	public static String getDate(long time) {
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		return sfd.format(time);
	}

	public static String getShortDate(long time) {
		SimpleDateFormat sfd = new SimpleDateFormat("MM.dd");
		return sfd.format(time);
	}

	//判断某个时间是否过期
	public static int getProductTimeType(long time) {
		long nowTime =System.currentTimeMillis();
		if(time<nowTime){
			return 0;//过期
		}else if(time>nowTime &&(time-nowTime)<86400000){//限时抢
			return 1;//限时抢
		}else {//等待
			return 2;//等待超过一天
		}
	}
	
	//判断某个时间是否过期
		public static int[] getDeadLineTime(long time) {
			long nowTime =System.currentTimeMillis();
			long interval = (time-nowTime)/1000;
			int seconds = (int)(interval%60);
			int minutes = (int)(interval/60%60);
			int hours = (int)(interval/3600);
			return new int[]{hours,minutes,seconds};
		}
	
	public static String getLiveArrangeDate(long startTime, long endTime) {
		String s = getShortDate(startTime);
		String e = getShortDate(endTime);
		return "(" + s + "-" + e + ")";
	}

	/*
	 * 日期获取 按照毫秒
	 */
	public static long getDate(String time) {
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sfd.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static String lastWeekYYMMDD(String time, int backDate)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		date = sfd.parse(time);
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_YEAR, backDate);
		date = calendar.getTime();
		return sfd.format(date);
	}

	public static String lastMonthYYMMDD(String time, int backDate)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyyMM");
		Date date;
		date = sfd.parse(time);
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, backDate);
		date = calendar.getTime();
		return sfd.format(date);
	}

	public static String lastYearYYMMDD(String time, int backDate)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy");
		Date date;
		date = sfd.parse(time);
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, backDate);
		date = calendar.getTime();
		return sfd.format(date);
	}

	public static String lastDayYYMMDD(String time, int backDate)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMdd");
		Date date;
		date = sfd.parse(time);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, backDate);
		date = calendar.getTime();
		return sfd.format(date);
	}

	public static String getDayTimeMinute(long time) {
		SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
		return sfd.format(time);
	}

	/*
	 * 获取时间
	 */
	public static String getDayTime(long time) {
		SimpleDateFormat sfd = new SimpleDateFormat("HH:mm:ss");
		return sfd.format(time);
	}

	public static String getDuractionTime(long starttime, long nowtime) {
		long dure = nowtime - starttime;
		return getChinatime(dure / 1000);
	}

	public static String getLiveTime(long liveStartTime, long liveEndTime,
			String retAddress) {
		Date date = new Date();
		long now = date.getTime();

		if (now < liveStartTime) {// 出发前
			long interval=(liveStartTime - now)/86400000 + 1 ;
			if (interval == 1) {
				return "今天从" + retAddress + "出发";
			}
			return (interval-1) + "天后从" + retAddress + "出发";
		} else if (now < liveEndTime) {
			long interval=(liveEndTime - now)/86400000 + 1 ;
			if (interval == 1) {
				return "今天回到" + retAddress;
			}
			return (interval-1) + "天后回到" + retAddress;
		} else if (now >= liveEndTime) {
			long interval=(now-liveEndTime)/86400000 + 1 ;
			if (interval == 1) {
				return "今天回来";
			}
			return "已回到" + retAddress + (interval-1) + "天";
		}
		return "未知";
	}

	public static String getChinatime(long time) {
		if (time < 60) {
			return time + "秒";
		} else if (time < 3600) {
			return time / 60 + "分" + getChinatime(time % 60);
		} else if (time < 86400) {
			return time / 3600 + "小时" + getChinatime(time % 3600);
		} else {
			return time / 86400 + "天" + getChinatime(time % 86400);
		}
	}

}
