package com.quanwe.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @COMPANY:sunnyTech
 * @CLASS:CommonUI
 * @DESCRIPTION:与Activity(界面)有关的公共处理函数,必须传Activity进来.
 * @AUTHOR:Sunny
 * @VERSION:v1.0
 * @DATE:2014-10-21 下午4:56:48
 */
public class DisplayMetricsUtil {

	// ----------------------屏幕-----------------------------

	/**
	 * 获取屏幕宽
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayWidthMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayHeightMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

}
