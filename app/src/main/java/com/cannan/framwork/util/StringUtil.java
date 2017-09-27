/**
 *      
 */
package com.cannan.framwork.util;

import android.annotation.SuppressLint;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 */
@SuppressLint("SimpleDateFormat")
public class StringUtil {


	public static boolean isEmpty(String msg){
		if(msg == null || msg.length()==0 || msg.trim().length()==0){
			return true;
		}
		return false;
	}

	/**
	 * 获取当前系统时间字符串，格式化形式：“yyyy-MM-dd_HH:mm:ss”
	 * @return 当前时间字符串
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	public static String getCurrentTimeStr(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return formatter.format(date);
	}

	/**
	 * 获取指定时间字符串，格式化形式：“yyyy-MM-dd”
	 * @return 获取指定时间字符串
	 */
	public static String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日", Locale.CHINA);
		return formatter.format(date);
	}

	/**
	 * 获取指定时间字符串，格式化形式：“yyyy-MM-dd”
	 * @return 获取指定时间字符串
	 */
	public static String getDateSplit(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy.MM.dd", Locale.CHINA);
		return formatter.format(date);
	}

	/**
	 * 转换网页内容
	 * 
	 * @return
	 */
	public static String transfWebContent(String content) {
		if (content == null) {
			return "";
		}
		Spanned contentTemp = Html.fromHtml(content);
		String contentStr = contentTemp.toString();
		return contentStr;
	}


	/**
	 * 判断是否为图片资源连接
	 **/
	public static boolean getImageLink(String mString) {
		if (mString.lastIndexOf("jpg") > 0 || mString.lastIndexOf("png") > 0
				|| mString.lastIndexOf("jpeg") > 0) {
			return true;
		}
		return false;
	}


	/**
	 * 隐藏手机号中间四位数
	 * @param mobile
	 *            11位手机号码
	 * @return 中间四位使用"****"表示的手机号
	 */
	public static String getHidenMobile(String mobile) {
		if (mobile.length() < 8) {
			throw new RuntimeException("传入的手机号长度不正确");
		}
		return mobile.replace(mobile.subSequence(3, 7), "****");
	}

	/**
	 * 匹配邮箱
	 * @param mailStr
	 * @return
	 */
	public static boolean matchMail(String mailStr) {
		String regEx = "^(\\[a-zA-Z0-9_-\\])+@(\\[a-zA-Z0-9_-\\])+((\\.\\[a-zA-Z0-9_-\\]{2,3}){1,2})$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(mailStr);
		// boolean result = m.find();
		return m.matches();
	}

	/**
	 * 返回出生日期数组
	 * @param identity
	 * @return String[]
	 **/
	public static String[] getIdentityDate(String identity) {
		String[] mStrings = new String[3];
		mStrings[0] = identity.substring(6, 10);
		mStrings[1] = identity.substring(10, 12);
		mStrings[2] = identity.substring(12, 14);
		return mStrings;
	}

	/**
	 * 判断一个字符串是否是手机号
	 * @param mobileStr
	 * @return true if it is a Mobile number or false.
	 */
	public static boolean matchMobile(String mobileStr) {
		// 正则匹配手机号码
		Pattern p = Pattern.compile("^(1)\\d{10}$");
		Matcher m = p.matcher(mobileStr);
		return m.matches();
	}

	/**
	 * 判断一个字符串是否是psw
	 * @param mobileStr
	 * @return true  or false.
	 */
	public static boolean matchPassword(String mobileStr) {
		if (!TextUtils.isEmpty(mobileStr) && mobileStr.length() > 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个字符串是否是psw
	 * @param code
	 * @return true  or false.
	 */
	public static boolean matchPhoneCode(String code) {
		// 正则匹配手机号码
		Pattern p = Pattern.compile("^[0-9]{6}");
		Matcher m = p.matcher(code);
		return m.matches();
	}

	/**
	 * 判断一个字符串是否是正确的身份证号码
	 * @param idNoStr
	 * @return
	 */
	public static boolean matchIdCardNo(String idNoStr) {
		// 正则匹配身份证号码
		Pattern p = Pattern.compile("^\\d{15}|^\\d{17}([0-9]|X|x)$");
		Matcher m = p.matcher(idNoStr);
		return m.matches();
	}

	/**
	 * 获取提交到服务器需要的日期类型
	 **/
	@SuppressLint("SimpleDateFormat")
	public static Date getSendDate(String mString, String formatString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		Date date = new Date();
		try {
			date = dateFormat.parse(mString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
