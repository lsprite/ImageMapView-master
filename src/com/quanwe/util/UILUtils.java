package com.quanwe.util;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public final class UILUtils {
	private static DisplayImageOptions options;
	private static DisplayImageOptions optionsWithDefault;

	public static void displayImageEqualWindows(final Context context, String imgurl, ImageView imageView) {
		try {
			initOptionsWithDefault();
			ImageLoader.getInstance().displayImage(imgurl, imageView, options, new ImageLoadingListener() {

				@Override
				public void onLoadingStarted(String imageUri, View view) {
					// TODO Auto-generated method stub
					System.out.println("onLoadingStarted");
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					// TODO Auto-generated method stub
					System.out.println("onLoadingFailed");
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					// TODO Auto-generated method stub
					System.out.println("onLoadingComplete");
					try {
						int width = DisplayMetricsUtil.getDisplayWidthMetrics(context);
						int height = loadedImage.getHeight() * width / loadedImage.getWidth();
						LayoutParams layoutParams = view.getLayoutParams();
						layoutParams.width = width;
						layoutParams.height = height;
						System.out.println("图片" + imageUri + "的正式长宽:" + width + "," + height);
						view.setLayoutParams(layoutParams);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				@Override
				public void onLoadingCancelled(String imageUri, View view) {
					// TODO Auto-generated method stub
					System.out.println("onLoadingComplete");
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void displayImageEqualWindows(final Context context, String imgurl, ImageView imageView,
			ImageLoadingListener imageLoadingListener) {
		try {
			initOptionsWithDefault();
			ImageLoader.getInstance().displayImage(imgurl, imageView, options, imageLoadingListener);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void initOptionsWithDefault() {
		if (optionsWithDefault == null) {
			optionsWithDefault = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					// 设置图片的解码类型
					.imageScaleType(ImageScaleType.EXACTLY).delayBeforeLoading(100).build();
		}
	}
}
