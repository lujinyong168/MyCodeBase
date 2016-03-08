package com.lujinyong.thread.multipleThread.thread3.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 时间工具类
 */
public class DateUtil {
	private static SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private final static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	private final static String DATE_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获得当前时间
	 * 
	 * @return String yyyy-MM-dd hh:mm:ss
	 */
	public static String getNowDateTime() {
		Calendar cale = Calendar.getInstance();
		String time = timeToStr(cale);

		return time;
	}

	/**
	 * 要求date的格式为yyyy-HH-dd
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @return Calendar
	 */
	public static Calendar getCalendarForStr_yyyy_HH_dd(String date, int hour,
			int minute, int second) {
		Calendar cale = Calendar.getInstance();
		String[] strs = date.split("-");
		int year = Integer.parseInt(strs[0]);
		int month = Integer.parseInt(strs[1]);
		int day = Integer.parseInt(strs[2]);

		cale.set(Calendar.YEAR, year);
		cale.set(Calendar.MONTH, month - 1);
		cale.set(Calendar.DAY_OF_MONTH, day);
		cale.set(Calendar.HOUR_OF_DAY, hour);
		cale.set(Calendar.MINUTE, minute);
		cale.set(Calendar.SECOND, second);
		return cale;
	}

	/**
	 * 要求date的格式为yyyy-HH-dd
	 * 
	 * @param date
	 *            日期
	 * @return Calendar
	 */
	public static Calendar getCalendarForStr_yyyy_HH_dd(String date) {
		Calendar cale = Calendar.getInstance();
		String[] strs = date.split("-");
		int year = Integer.parseInt(strs[0]);
		int month = Integer.parseInt(strs[1]);
		int day = Integer.parseInt(strs[2]);

		cale.set(Calendar.YEAR, year);
		cale.set(Calendar.MONTH, month - 1);
		cale.set(Calendar.DAY_OF_MONTH, day);
		return cale;
	}

	/**
	 * 要求date的格式为yyyy-HH-dd
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @return Calendar
	 */
	public static Date getDateForStr_yyyy_HH_dd(String date, int hour,
			int minute, int second) {
		Calendar cale = Calendar.getInstance();
		String[] strs = date.split("-");
		int year = Integer.parseInt(strs[0]);
		int month = Integer.parseInt(strs[1]);
		int day = Integer.parseInt(strs[2]);

		cale.set(Calendar.YEAR, year);
		cale.set(Calendar.MONTH, month - 1);
		cale.set(Calendar.DAY_OF_MONTH, day);
		cale.set(Calendar.HOUR_OF_DAY, hour);
		cale.set(Calendar.MINUTE, minute);
		cale.set(Calendar.SECOND, second);
		return cale.getTime();
	}

	/**
	 * 获得当前日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getTodayDate() {
		Calendar cale = Calendar.getInstance();
		String date = cale.get(Calendar.YEAR) + "-";
		int month = cale.get(Calendar.MONTH) + 1;
		if (month < 10) {
			date += "0" + month + "-";
		} else {
			date += month + "-";
		}

		int day = cale.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			date += "0" + day;
		} else {
			date += day + "";
		}

		return date;
	}

	/**
	 * 获得当日开始时间
	 * 
	 * @return yyyy-MM-dd 0:0:0
	 */
	public static String getTodayStartDateTime() {
		String time = "";
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.HOUR_OF_DAY, 00);
		cale.set(Calendar.MINUTE, 00);
		cale.set(Calendar.SECOND, 00);
		time = timeToStr(cale);
		return time;
	}

	/**
	 * 获得当日结束时间
	 * 
	 * @return yyyy-MM-dd 23:59:59
	 */
	public static String getTodayEndDateTime() {
		String time = "";
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		time = timeToStr(cale);
		return time;
	}

	public static String getDateTime(int year, int month, int date, int hour,
			int minute, int second) {
		String time = "";
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.YEAR, year);
		cale.set(Calendar.MONTH, month);
		cale.set(Calendar.DATE, date);
		cale.set(Calendar.HOUR_OF_DAY, hour);
		cale.set(Calendar.MINUTE, minute);
		cale.set(Calendar.SECOND, second);

		time = timeToStr(cale);
		return time;
	}

	public static int getDateTime(int hour, int minute, int second) {

		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.HOUR_OF_DAY, hour);
		cale.set(Calendar.MINUTE, minute);
		cale.set(Calendar.SECOND, second);

		int time = (int) (cale.getTimeInMillis() / 1000);
		return time;

	}

	public static String timeToStr(Calendar cale) {
		return "" + cale.get(Calendar.YEAR) + "-"
				+ (cale.get(Calendar.MONTH) + 1) + "-"
				+ cale.get(Calendar.DAY_OF_MONTH) + " "
				+ cale.get(Calendar.HOUR_OF_DAY) + ":"
				+ cale.get(Calendar.MINUTE) + ":" + cale.get(Calendar.SECOND);
	}

	/**
	 * 跟据给定的格式返回现在的日期时间的字符串形式 pattern如：yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cale = Calendar.getInstance();
		Date date = cale.getTime();
		String dateTime = sdf.format(date);
		return dateTime;
	}

	/**
	 * 跟据给定的格式和date值返回的日期时间的字符串形式 pattern如：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateTime = sdf.format(date);
		return dateTime;
	}

	/**
	 * 跟据给定的格式和Calendar值返回的日期时间的字符串形式 pattern如：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime(Calendar cale, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateTime = sdf.format(cale.getTime());
		return dateTime;
	}

	/**
	 * 跟据给定的格式和long值返回的日期时间的字符串形式 pattern如：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime(long date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateTime = sdf.format(date);
		return dateTime;
	}

	/**
	 * 字符串转换为Calendar
	 * 
	 * @param strtime
	 * @return
	 */
	public static Calendar getCalendarByString(String strtime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse(strtime);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			return c;
		} catch (ParseException e) {
			e.printStackTrace();
			return Calendar.getInstance();
		}

	}

	/**
	 * 根据给定格式转换为Calendar
	 * 
	 * @pattern:yyyy-MM-dd,yyyy/MM/dd
	 */
	public static Calendar getCalendarByPattern(String timeStr, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date d = format.parse(timeStr);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			return c;
		} catch (ParseException e) {
			e.printStackTrace();
			return Calendar.getInstance();
		}
	}

	/**
	 * 字符串转换为Calendar
	 * 
	 * @param strtime
	 * @return
	 */
	public static Calendar getCalendarByPattern(String strtime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date d = format.parse(strtime);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			return c;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Calendar.getInstance();
		}

	}

	/**
	 * 获取当前的时间值的秒表示
	 * 
	 * @return
	 */
	public static int getNowTimeWithSecond() {
		Calendar c = Calendar.getInstance();
		int time = (int) (c.getTimeInMillis() / 1000);
		return time;
	}

	/**
	 * 获取今天天开始和结束时间值的秒表示
	 * 
	 * @return
	 */
	public static int[] getTodayWithSecond() {
		Calendar[] c = DateUtil.getToday(Calendar.getInstance());
		int[] time = new int[2];
		time[0] = (int) (c[0].getTimeInMillis() / 1000);
		time[1] = (int) (c[1].getTimeInMillis() / 1000);
		return time;
	}

	/**
	 * 获取昨天开始和结束时间值的秒表示
	 * 
	 * @return
	 */
	public static int[] getYesterdayWithSecond() {
		Calendar[] c = DateUtil.getYesterday(Calendar.getInstance());
		int[] time = new int[2];
		time[0] = (int) (c[0].getTimeInMillis() / 1000);
		time[1] = (int) (c[1].getTimeInMillis() / 1000);
		return time;
	}

	/**
	 * 获取当月开始和结束时间值的秒表示
	 * 
	 * @return
	 */
	public static int[] getMonthWithSecond() {
		Calendar[] c = DateUtil.getThisMonth(Calendar.getInstance());
		int[] time = new int[2];
		time[0] = (int) (c[0].getTimeInMillis() / 1000);
		time[1] = (int) (c[1].getTimeInMillis() / 1000);
		return time;
	}

	/**
	 * 获取指定的时间值的秒表示
	 * 
	 * @return
	 */
	public static int getTimeWithSecond(Calendar cale) {
		int time = (int) (cale.getTimeInMillis() / 1000);
		return time;
	}

	public static Date getDateFromTimestamp(int timestamp) {
		Date date = new Date();
		date.setTime(timestamp * 1000);
		return date;
	}

	public static String getYesterDayDate(String parttern) {
		int stamp = DateUtil.getYesterdayWithSecond()[0];
		return DateUtil.formatTimestampToStr(stamp, parttern);
	}

	/**
	 * 根据一个Calendar获得 那一天的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getToday(Calendar cale) {
		Calendar[] cales = new Calendar[2];

		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();

		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	/**
	 * 根据一个Calendar获得 那一天的前一天的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getYesterday(Calendar cale) {
		Calendar[] cales = new Calendar[2];
		cale.set(Calendar.DAY_OF_MONTH, cale.get(Calendar.DAY_OF_MONTH) - 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();

		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	/**
	 * 根据一个Calendar获得 那个周的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getThisWeek(Calendar cale) {
		Calendar[] cales = new Calendar[2];

		cale.set(Calendar.DAY_OF_WEEK, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();

		cale.set(Calendar.DAY_OF_WEEK, 7);
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	/**
	 * 根据一个Calendar获得 那个周的上一周的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getLastWeek(Calendar cale) {
		Calendar[] cales = new Calendar[2];

		cale.set(Calendar.DAY_OF_WEEK, 1);
		cale.set(Calendar.DAY_OF_MONTH, cale.get(Calendar.DAY_OF_MONTH) - 7);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();

		cale.set(Calendar.DAY_OF_WEEK, 7);
		cale.set(Calendar.DAY_OF_MONTH, cale.get(Calendar.DAY_OF_MONTH) - 7);
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	/**
	 * 根据一个Calendar获得 那个月的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getThisMonth(Calendar cale) {
		Calendar[] cales = new Calendar[2];

		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();
		int temp = cale.get(Calendar.MONTH);
		if (temp == 1) {
			cale.set(Calendar.DAY_OF_MONTH, 29);
		} else {
			cale.set(Calendar.DAY_OF_MONTH, 31);
		}

		temp = cale.get(Calendar.DAY_OF_MONTH);
		if (temp == 1) {
			cale.set(Calendar.DAY_OF_MONTH, temp - 1);
		}

		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	/**
	 * 根据一个Calendar获得 那年的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getThisYear(Calendar cale) {
		Calendar[] cales = new Calendar[2];

		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.MONTH, 0);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();
		cale.set(Calendar.MONTH, 11);
		cale.set(Calendar.DAY_OF_MONTH, 31);
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	/**
	 * @param 获取上一个月日期
	 * @return
	 */
	public Date nextMonth() {
		Date currentDate = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(GregorianCalendar.MONTH, -1);// 在月份上加1
		return cal.getTime();
	}

	/**
	 * 根据一个Calendar获得 那个月的上个月的开始时间和结束时间
	 * 
	 * @param cale
	 * @return
	 */
	public static Calendar[] getLastMonth(Calendar cale) {
		Calendar[] cales = new Calendar[2];
		cale.set(Calendar.MONTH, cale.get(Calendar.MONTH) - 1);

		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cales[0] = (Calendar) cale.clone();
		int temp = cale.get(Calendar.MONTH);
		if (temp == 1) {
			cale.set(Calendar.DAY_OF_MONTH, 29);
		} else {
			cale.set(Calendar.DAY_OF_MONTH, 31);
		}

		temp = cale.get(Calendar.DAY_OF_MONTH);
		if (temp == 1) {
			cale.set(Calendar.DAY_OF_MONTH, temp - 1);
		}

		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cales[1] = (Calendar) cale.clone();
		return cales;
	}

	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int currentDays = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, currentDays + days);
		return calendar.getTime();
	}

	/**
	 * 把日期格式的date转换为时间戳
	 * 
	 * @dateStr:格式为 2010-2-5
	 */
	public static int formatDateToInt(String dateStr) {
		int time = 0;
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByString(dateStr);
			time = getTimeWithSecond(d);
		}
		return time;
	}

	public static int formatDateToInt1(String dateStr) {
		int time = 0;
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByPattern(dateStr, "yyyy-MM-dd HH:mm:ss");
			time = getTimeWithSecond(d);
		}

		return time;
	}

	public static int formatDateToInt2(String dateStr, String pattern) {
		int time = 0;
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByPattern(dateStr, pattern);
			time = getTimeWithSecond(d);
		}
		return time;
	}

	public static int[] getStartEndTimeStampByDate(String dateStr,
			String pattern) {
		int[] start_end = new int[2];
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByPattern(dateStr, pattern);
			Calendar[] ds = getToday(d);
			start_end[0] = getTimeWithSecond(ds[0]);
			start_end[1] = getTimeWithSecond(ds[1]);
		}
		return start_end;
	}

	public static int[] getStartEndTimeStampByMonth(String dateStr,
			String pattern) {
		int[] start_end = new int[2];
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByPattern(dateStr, pattern);
			Calendar[] ds = getThisMonth(d);
			start_end[0] = getTimeWithSecond(ds[0]);
			start_end[1] = getTimeWithSecond(ds[1]);
		}

		return start_end;
	}

	public static int[] getStartEndTimeStampByYear(String dateStr,
			String pattern) {
		int[] start_end = new int[2];
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByPattern(dateStr, pattern);
			Calendar[] ds = getThisYear(d);
			start_end[0] = getTimeWithSecond(ds[0]);
			start_end[1] = getTimeWithSecond(ds[1]);
		}

		return start_end;
	}

	public static int[] getStartEndTimeStampByLastMonth(String dateStr,
			String pattern) {
		int[] start_end = new int[2];
		if (dateStr != null && !dateStr.equals("")) {
			Calendar d = getCalendarByPattern(dateStr, pattern);
			Calendar[] ds = getLastMonth(d);
			start_end[0] = getTimeWithSecond(ds[0]);
			start_end[1] = getTimeWithSecond(ds[1]);
		}

		return start_end;
	}

	public static String formatTimestampToStr(long timestamp, String pattern) {
		SimpleDateFormat fm1 = new SimpleDateFormat(pattern);
		long unixLong = 0;
		String date = "";
		unixLong = timestamp * 1000;
		date = fm1.format(unixLong);
		return date;
	}

	// 获取时间间隔字符串
	public static String getMinuteDiffBetweenTwoDate(long time1, long time2) {
		long diff = Math.abs(time1 - time2);
		long minute = (int) diff / 60;
		long hour = 0;
		if (minute / 60 > 0) {
			hour = minute / 60;
			minute = minute % 60;
		}
		StringBuffer ret = new StringBuffer();
		if (hour > 0) {
			ret.append(hour).append("小时");
		}
		if (minute > 0) {
			ret.append(minute).append("分钟");
		} else {
			ret.append("小于1分钟");
		}
		ret.append("前");
		return ret.toString();
	}

	public static String getYesterdayForSec(long seconds) {
		seconds -= 24 * 60 * 60;
		return formatTimestampToStr(seconds, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * get Age By seconds
	 * 
	 * @param seconds
	 * @return
	 */
	public static int getAge(long seconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(seconds * 1000);
		Date date = c.getTime();
		String birthdayStr = getDateTime(date, "yyyy-MM-dd");
		birthdayStr = birthdayStr.substring(0, 4);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(System.currentTimeMillis());
		String curYearStr = getDateTime(c2.getTime(), "yyyy-MM-dd");
		curYearStr = curYearStr.substring(0, 4);
		int age = Integer.parseInt(curYearStr) - Integer.parseInt(birthdayStr);
		return age;
	}

	/**
	 * 获取开始和结束时间秒表示
	 * 
	 * @param pattern
	 * @return
	 */
	public static int[] parseStartToEnd(String pattern) {
		int[] result = new int[2];
		String patterns = "yyyy/MM/dd";
		if (pattern.contains("-")) {
			String[] str = pattern.split("-");
			for (int i = 0; i < str.length; i++) {
				Calendar c = getCalendarByPattern(str[i], patterns);
				result[i] = getTimeWithSecond(c);
			}
		}
		return result;
	}

	public static int getDays(int timestamp) {
		int a = formatDateToInt2("1990-03-31", "yyyy-MM-dd");
		int days = (timestamp - a) / 86400;
		return days;
	}

	public static int getDays() {
		Date date = Calendar.getInstance().getTime();
		int year = date.getYear();
		int days = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1;
		int res = year * 365 + days;
		return res;
	}

	public static int getDaysByTimestamp(int stamp) {
		int now = getDays();
		int stamp_now = DateUtil.getNowTimeWithSecond();
		int left = stamp - stamp_now;
		int b = getMd(Math.abs(left));
		System.out.print(stamp_now + "==" + stamp + "==" + left + "==bbb==" + b
				+ "==\n");
		int res = 0;
		if (left < 0) {
			res = now - b + 1;
		} else if (left > 0) {
			res = now + b;
		} else {
			res = now;
		}
		return res;
	}

	private static int getMd(int left) {
		int a = left / 86400;
		int b = left % 86400;
		if (b > 0) {
			a = a + 1;
		}
		return a;
	}

	public static String formatDays(int days) {
		int years_int = days / 365;
		int day_int = days % 365;
		String start_str = String.valueOf(1900 + years_int) + "-1-1";
		int all = DateUtil.formatDateToInt(start_str) + day_int * 86400;
		String timestr = DateUtil.formatTimestampToStr(all, "yyyy-MM-dd");
		return timestr;
	}

	public static Date getDate(long timestamp) {
		String datestr = DateUtil.formatTimestampToStr(timestamp,
				"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = format.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return d;
	}

	// public static String[] getStr(){
	// String[] str = new String[2];
	// Calendar calendar = Calendar.getInstance();
	// int year = calendar.get(Calendar.YEAR);
	// int month = calendar.get(Calendar.MONTH) + 1;
	// int day = calendar.get(Calendar.DAY_OF_MONTH) - 1;
	//
	// String monthStr = String.valueOf(month);
	// String dayStr = String.valueOf(day);
	//
	// if(monthStr.length()==1){
	// monthStr = 0 + monthStr;
	// }
	// if(dayStr.length() == 1){
	// dayStr = 0 + dayStr;
	// }
	//
	// str[0] = String.valueOf(10000*year + 100* month + day); //20140703
	// str[1] = year+"-"+ monthStr +"-"+dayStr ; //2014-07-03
	// return str;
	// }

	public static String getTimeStr(Calendar calendar) {
		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH) + 1;
		int t = calendar.get(Calendar.DAY_OF_MONTH);

		String ms = String.valueOf(m);
		String ts = String.valueOf(t);

		if (ms.length() == 1) {
			ms = 0 + ms;
		}
		if (ts.length() == 1) {
			ts = 0 + ts;
		}
		String str = y + "-" + ms + "-" + ts; // 2014-06-01
		return str;
	}

	public static int getToddayWithSecond() {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		int time = (int) (cale.getTimeInMillis() / 1000);
		return time;
	}

	public static int getNextYesterdayTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 2);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		int timestr = DateUtil.getTimeWithSecond(calendar); // 获取前天的零点毫秒数
		return timestr;
	}

	public static long toUnixTimestamp(Date date) {
		return date.getTime() / 1000;
	}

	public static Date parseDate(String date, String format) {
		Date ret = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			ret = dateFormat.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ret;
	}
	
	public static Timestamp parseStrToTimestamp(String datestr,String pattern){
		Timestamp tm = null;
		try{
			tm = new Timestamp(new SimpleDateFormat(pattern).parse(datestr).getTime());
//			tm = new Timestamp(dateFormat.parse(datestr).getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return tm;
	}
	
	 public static List<String> getTimeRange(Timestamp start1,Timestamp  end1){
		 Timestamp start = new Timestamp(start1.getTime());
		 Timestamp  end = new Timestamp(end1.getTime());
		 List<String> retList = new ArrayList<String>();
		 	if(start == null || end == null)
		 		return retList;
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(start.compareTo(end)  <= 0){
				retList.add(sFormat.format(start));
				start.setTime(start.getTime() + 24*60*60*1000);
			}
			return retList;
	 }
	
	public static Calendar parseTimestamp2Calendar(Timestamp timestamp){
		Calendar instance = Calendar.getInstance();
		instance.setTime(DateUtil.getDate(timestamp.getTime()/1000));
		return instance;
	}
	
	public static String getDateWithAddSeconds(String beginDate,int sec){
		return DateUtil.formatTimestampToStr(DateUtil.formatDateToInt2(beginDate,DATE_PATTERN1)+sec,DATE_PATTERN1);
	}
	
	 public static String formatTimestampToStr(Timestamp tm,String pattern){
		 return new SimpleDateFormat(pattern).format(tm);
	 }
	
}
