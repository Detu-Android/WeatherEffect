package co.liuwei.weathereffect.view;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import co.liuwei.weathereffect.R;

public class RainView extends View{
	
	int MAX_SNOW_COUNT = 40;
	// ѩ��ͼƬ
	Bitmap bitmap_snows = null;
	// ����
	private final Paint mPaint = new Paint();
	// �漴������
	private static final Random random = new Random();
	// ����λ��
	private Rain[] rains = new Rain[MAX_SNOW_COUNT];
	// ��Ļ�ĸ߶ȺͿ��
	int view_height = 0;
	int view_width = 0;
	int MAX_SPEED = 55;

//	Bitmap bitmap_bg;
	
	/**
	 * ������
	 * 
	 * 
	 */
	public RainView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RainView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	/**
	 * ������Ůɢ���Ļ�ͼƬ���ڴ���
	 * 
	 */
	public void LoadSnowImage() {
		Resources r = this.getContext().getResources();
		bitmap_snows = ((BitmapDrawable) r.getDrawable(R.drawable.raindrop_l))
				.getBitmap();
		
//		bitmap_bg = BitmapFactory.decodeResource(getResources(),
//				R.drawable.shouye);
	}

	/**
	 * ���õ�ǰ�����ʵ�ʸ߶ȺͿ��
	 * 
	 */
	public void SetView(int height, int width) {
		view_height = height ;//- 100;
		view_width = width ;//- 50;
	}

	/**
	 * ��������ɻ����λ��
	 * 
	 */
	public void addRandomSnow() {
		for(int i =0; i< MAX_SNOW_COUNT;i++){
			rains[i] = new Rain(random.nextInt(view_width), 0,random.nextInt(MAX_SPEED));
		}
	}


	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
//		canvas.drawBitmap(bitmap_bg, null, new Rect(0, 0, view_width, view_height), mPaint);
		
		for (int i = 0; i < MAX_SNOW_COUNT; i += 1) {
			if (rains[i].coordinate.x >= view_width || rains[i].coordinate.y >= view_height) {
				rains[i].coordinate.y = 0;
				rains[i].coordinate.x = random.nextInt(view_width);
			}
			// ѩ��������ٶ�
			rains[i].coordinate.y += rains[i].speed + 15;
			//ѩ��Ʈ����Ч��

//			// �������һ�����֣���ѩ����ˮƽ�ƶ���Ч��
//			int tmp = MAX_SPEED/2 - random.nextInt(MAX_SPEED);
//			//Ϊ�˶�������Ȼ�ԣ����ˮƽ���ٶȴ���ѩ���������ٶȣ���ôˮƽ���ٶ�����ȡ������ٶȡ�
//			snows[i].coordinate.x += snows[i].speed < tmp ? snows[i].speed : tmp;
			canvas.drawBitmap(bitmap_snows, rains[i].coordinate.x,//((float) snows[i].coordinate.x)
					((float) rains[i].coordinate.y) - 140, mPaint);
		}

	}
	
	/*
	 * ������������¹��� ��ʵ����ѩ
	 */
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			//snow.addRandomSnow();
			RainView.this.invalidate();
			sleep(100);
		}
		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

	/**
	 * Handles the basic update loop, checking to see if we are in the running
	 * state, determining if a move should be made, updating the snake's
	 * location.
	 */
	public void update() {
		this.addRandomSnow();
		mRedrawHandler.sleep(600);
	}

}
