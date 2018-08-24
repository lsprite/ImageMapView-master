package com.quanwe.imagemapviewdemo;

import static com.quanwe.ImageMark.createTestMark;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.quanwe.ImageMapView;
import com.quanwe.ScaleViewAttacher.OnViewTapListener;
import com.quanwe.util.UILUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 使用示例
 */
public class MainActivity extends Activity {
	ImageMapView imgMapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgMapView = (ImageMapView) findViewById(R.id.imgMapView);
		UILUtils.displayImageEqualWindows(this,
				"http://dl2.iteye.com/upload/attachment/0074/8459/3db78772-fce6-3617-89d4-5c38190accbe.jpg", imgMapView,
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						// TODO Auto-generated method stub
						try {
							// int width =
							// DisplayMetricsUtil.getDisplayWidthMetrics(MainActivity.this);
							// int height = loadedImage.getHeight() * width /
							// loadedImage.getWidth();
							// LayoutParams layoutParams =
							// view.getLayoutParams();
							// layoutParams.width = width;
							// layoutParams.height = height;
							// System.out.println("图片" + imageUri + "的正式长宽:" +
							// width + "," + height);
							// view.setLayoutParams(layoutParams);
							System.out.println("---图片宽高:" + loadedImage.getWidth() + "," + loadedImage.getHeight());
							initTestData(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						// TODO Auto-generated method stub

					}
				});
		imgMapView.setOnViewTapListener(new OnViewTapListener() {

			@Override
			public void onViewTap(View view, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("----选中:" + x + "," + y);
				AnimationSet set = new AnimationSet(true);
				ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.RELATIVE_TO_SELF,
						0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				scaleAnimation.setDuration(1000);
				set.addAnimation(scaleAnimation);
				//
				TranslateAnimation translateAnimation = new TranslateAnimation(0.0f,
						(imgMapView.getWidth() / 2 - x) * 2, 0.0f, (imgMapView.getHeight() / 2 - y) * 2);// 上面放大两倍，平移也得跟着放大两倍
				translateAnimation.setStartOffset(100);
				translateAnimation.setDuration(1000);
				set.addAnimation(translateAnimation);
				imgMapView.startAnimation(set);
			}
		});
		// 生成一些测试点
		// initTestData();
	}

	/**
	 * 测试数据
	 */
	void initTestData(float latStart, float lngStart, float latEnd, float lngEnd) {
		// 测试用地图区域
		// float latStart = 30.5378686253f;
		// float lngStart = 114.3372917175f;
		// float latEnd = 30.4739760743f;
		// float lngEnd = 114.4658660889f;
		// float latStart = 0f;
		// float lngStart = 0f;
		// float latEnd = 861f;
		// float lngEnd = 1500f;
		imgMapView.setMapRange(latStart, lngStart, latEnd, lngEnd);// 设置地图图片对应的边界
		Bitmap markeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flowsuper_error);
		// for (int i = 0; i < 2; i++) {
		//// float lat = (float) (latStart + Math.random() * (latEnd -
		// latStart));// 随机一些坐标
		//// float lng = (float) (lngStart + Math.random() * (lngEnd -
		// lngStart));
		// imgMapView.addImageMark(createTestMark(lat, lng, markeBitmap));
		// }
		for (int i = 0; i < 9; i++) {
			imgMapView.addImageMark(createTestMark(i * 100f, i * 100f, markeBitmap));
		}
		imgMapView.addImageMark(createTestMark(1071f, 1532f, markeBitmap));

	}

	/**
	 * 修改位图颜色
	 * 
	 * @param mBitmap
	 * @param mColor
	 * @return
	 */
	public static Bitmap getAlphaBitmap(Bitmap mBitmap, int mColor) {
		Bitmap mAlphaBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.RGB_565);
		Canvas mCanvas = new Canvas(mAlphaBitmap);
		Paint mPaint = new Paint();
		mPaint.setColor(mColor);
		// 从原位图中提取只包含alpha的位图
		Bitmap alphaBitmap = mBitmap.extractAlpha();
		// 在画布上（mAlphaBitmap）绘制alpha位图
		mCanvas.drawBitmap(alphaBitmap, 0, 0, mPaint);
		return mAlphaBitmap;
	}
}
