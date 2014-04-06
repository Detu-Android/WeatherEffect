package co.liuwei.weathereffect.particle;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import co.liuwei.weathereffect.R;

/*
 * ����������������
 * ��1���������ӵ����������ӣ�����һ����Χ������ٶȺ�һ����Χ�ڵ�������������ʼ��                                      
 * ��2����һ���Ĺ��ɸ����Ӹ��費ͬ����ɫ
 */
public class ParticleSet {

	ArrayList<Particle> particleSet;	//����һ�����particle������
	
	public ParticleSet(){
		particleSet = new ArrayList<Particle>();
	}
	
	//������ӵ�����particleSet���ָ�����������Ӳ���ÿ�����Ӵ���һ����ʼʱ��
	//��ӽ����������� �Ѿ���������ˮ���뾶�����ǵ��ٶ� �Լ������ĳ�ʼ������
	public void add(Context context,int count, double startTime){
		
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		
		Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.raindrop_l))
				.getBitmap();
		Random random = new Random();
		
		for(int i = 0; i < count; i++){	
			int tempX =  random.nextInt(metrics.widthPixels); //���ӵ�X����;
			int tempY = 0;//���ӵ�Y����
			Particle particle = new Particle(bitmap,tempX, tempY);	//�������Ӷ���
			particleSet.add(particle);
		}
	}
	
	//��������Ǹ���i�õ�һ����ɫ��iѭ������
	//����ܼ򵥣��������switch~case~�Ļ���������Ҳ��������ƪ����
	public int getColor(int i){
		int color = Color.RED;
		switch(i % 4){	//���κ�һ����ȡ4�������Ļ���ֻ�ܵõ�0,1,2,3�ĸ���
			case 0:
				color = Color.RED;
				break;
			case 1:
				color = Color.BLUE;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.YELLOW;
		}
		return color;	
	}
}
