package co.liuwei.weathereffect.particle;

import android.graphics.Bitmap;

/*
 * ���������þ��Ƕ������ӣ���д���캯�������ӳ�ʼ��
 */
public class Particle {
//	int color;	//�������ӵ���ɫ
//	int r;	//���Ӱ뾶
//	double vertical_v;	//���ӵ������ٶ�
//	double horizontal_v;	//ˮƽ�ٶ�
//	int startX;	//��ʼXλ��
//	int startY;	//��ʼYλ��
	int currentX;	//ʵʱXλ��
	int currentY;	//ʵʱYλ��
//	double startTime;	//��ʼʱ��
	Bitmap bitmap;
	
	public Particle(Bitmap bitmap, int currentX, int currentY){
		this.bitmap = bitmap;
//		this.color = color;
//		this.r = r;
//		this.vertical_v = vertical_v;
//		this.horizontal_v = horizontal_v;
//		this.startX = currentX;
//		this.startY = currentY;
		this.currentX = currentX;
		this.currentY = currentY;
//		this.startTime = startTime;
	}
//	public Particle(Bitmap bitmap,int color, int r, double vertical_v, double horizontal_v,
//			int currentX, int currentY, double startTime){
//		this.bitmap = bitmap;
//		this.color = color;
//		this.r = r;
//		this.vertical_v = vertical_v;
//		this.horizontal_v = horizontal_v;
//		this.startX = currentX;
//		this.startY = currentY;
//		this.currentX = currentX;
//		this.currentY = currentY;
//		this.startTime = startTime;
//	}
}
