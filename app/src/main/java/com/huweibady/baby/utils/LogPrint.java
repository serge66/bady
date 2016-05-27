package com.huweibady.baby.utils;

import android.util.Log;

/**
 * 打印日志工具类
 * 
 * @author Administrator
 * 
 */
public class LogPrint {

	/**
	 * 日志开关
	 */
	private static boolean isBug = true;

	/**
	 * 公共日志打印方法
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void logI(String tag, String msg) {
		if (isBug) {
			Log.i(tag, msg);
		}

	}

	/**
	 * lsj自己打印工具方法
	 * 
	 * @param msg
	 */
	public static void logILsj(String msg) {
		if (isBug) {
			Log.i("lsj", msg);
		}

	}
}
