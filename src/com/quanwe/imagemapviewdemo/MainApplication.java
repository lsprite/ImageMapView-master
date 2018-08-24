package com.quanwe.imagemapviewdemo;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
	private static MainApplication instance;

	//
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("MyApplication.onCreate()");
		initImageLoader(getApplicationContext());
		instance = this;
	}

	public static MainApplication getInstance() {
		if (instance == null) {
			instance = new MainApplication();
		}
		return instance;
	}

	// 使用imageloader加載圖片
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		System.out.println("=========初始化ImageLoader");
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 4).memoryCache(new WeakMemoryCache())
				.denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}