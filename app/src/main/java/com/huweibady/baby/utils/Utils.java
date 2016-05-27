package com.huweibady.baby.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.huweibady.baby.R;

/**
 * 工具类
 * 
 * @author Administrator
 * 
 */
public class Utils {

	/***
	 * 设置登录界面的背景
	 */
	public static void setBackground(Resources res, int id, View v) {
		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;

		Bitmap bitmap = BitmapFactory.decodeResource(res, id, options);

		options.inJustDecodeBounds = false;

		int heightPixels = res.getDisplayMetrics().heightPixels;

		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

		int be = (int) (options.outHeight / (float) heightPixels);

		if (be <= 0)

			be = 1;

		options.inSampleSize = be;

		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

		bitmap = BitmapFactory.decodeResource(res, R.mipmap.loginbackground,
				options);

		int w = bitmap.getWidth();

		int h = bitmap.getHeight();

		// llRoot.setBackground(new BitmapDrawable(bitmap));
//		v.setBackground(new BitmapState(bitmap));
		v.setBackground(new BitmapDrawable(Resources.getSystem(),bitmap));
	}

}
