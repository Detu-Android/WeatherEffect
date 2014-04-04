package co.liuwei.weathereffect.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import co.liuwei.weathereffect.R;

public class SunSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {

	private SurfaceHolder holder;

	private boolean isRunning = true;

	// private int statusBarHeight;

	private int screenWidth;// ��Ļ���
	private int screenHeiht;// ��Ļ�߶�

	private Bitmap sunBitmap;// ̫��ͼƬ
	private Bitmap sunshineBitmap;// ̫������ͼƬ

	private Bitmap viewBg;// ����ͼƬ

	public SunSurfaceView(Context context) {
		super(context);

		//��ȡ��Ļ���
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		this.screenHeiht = metrics.heightPixels;
		this.screenWidth = metrics.widthPixels;

		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		
		holder = getHolder();
		holder.addCallback(this);
		holder.setFormat(PixelFormat.RGBA_8888); // �������SurfaceView���͸��
		
		loadSunImage();

	}

	private void loadSunImage() {
		viewBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg0_fine_day);
		sunshineBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.sunshine);
		sunBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.sun_small);
		
		float percent = percentum();

		int _witdh = (int) ((screenWidth) / percent);
		
		sunshineBitmap = Bitmap.createScaledBitmap(sunshineBitmap, _witdh * 2,
				_witdh * 2, true);

	}

	// /**
	// * ���ڻ�ȡ״̬���ĸ߶ȡ�
	// *
	// * @return ����״̬���߶ȵ�����ֵ��
	// */
	// private int getStatusBarHeight() {
	// if (statusBarHeight == 0) {
	// try {
	// Class<?> c = Class.forName("com.android.internal.R$dimen");
	// Object o = c.newInstance();
	// Field field = c.getField("status_bar_height");
	// int x = (Integer) field.get(o);
	// statusBarHeight = getResources().getDimensionPixelSize(x);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// return statusBarHeight;
	// }

	/**
	 * ��ȡ����ͼ�ͷ糵�ı��� ���Ӷ�������������ı�����ֻ�����ķ糵ͼƬ��С
	 * 
	 * @return
	 */
	private float percentum() {
		float bg_height = screenWidth;//viewBg.getWidth();
		// float wind_width=Windmill.getHeight();
		return bg_height / 250;
	}
	//float aaa;
	@Override
	public void run() {

		float rotate = 0;// ��ת�Ƕȱ���

		while (isRunning) {
			Log.i("icer", "Running");
			Canvas canvas = null;
			synchronized (this) {
				try {
					canvas = holder.lockCanvas();
					if (canvas != null) {
						Paint paint = new Paint();
						paint.setAntiAlias(true);

						// ��ͼƬ�����
						paint.setFilterBitmap(true);
						RectF rect = new RectF(0, 0, screenWidth, screenHeiht);
						canvas.drawBitmap(viewBg, null, rect, paint);
						rotate = (rotate + 2) % 360f;
						System.out.println("===========>"+rotate);
						if((rotate >=0 && rotate<=50) || (rotate>=270  ) ){
							Matrix matrix = new Matrix();
							matrix.postRotate(rotate,
									sunshineBitmap.getWidth() / 2,
									sunshineBitmap.getHeight() / 2);
							//matrix.postTranslate(screenWidth-sunshineBitmap.getWidth(), 0);
							 matrix.postTranslate(screenWidth*0.2f,0);
							// matrix.setScale(10, 10);
							canvas.drawBitmap(sunshineBitmap, matrix, paint);
						}
						
						canvas.drawBitmap(sunBitmap,sunshineBitmap.getWidth() / 2- sunBitmap.getWidth()/2+(screenWidth*0.2f) ,sunshineBitmap.getHeight() / 2 - sunBitmap.getWidth() / 2, paint);
						Thread.sleep(50);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (canvas != null) {
						holder.unlockCanvasAndPost(canvas);
					}
				}

			}

		}

	}

	public void setRunning(boolean state) {
		isRunning = state;

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {

		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		isRunning = false;

	}

}
