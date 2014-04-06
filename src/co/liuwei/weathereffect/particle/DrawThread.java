package co.liuwei.weathereffect.particle;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/*
 * ��1���������һ�����Ƶ��߳��࣬�̲߳���ִ�У����ϵĻ�������
 * ͨ��surfaceHolderȡ�û�������surfaceView�಻�ϵĸı�
 * ��2����һ�������ǻ���FPS���ڳ���ĵ���
 */
public class DrawThread extends Thread{
	ParticleView particleView;	//Ҫ�����ӻ��Ƶ����������ʾ������Ҫ������
	SurfaceHolder holder;	//��Ҫ����surfaceHolder�Ļ�����������particleView��������Ҫ������
	boolean flag = false;	//���겻����߳�ִ�б�־λ
	int sleepSpan = 30;	//���겻����߳�����ʱ��
	long start = System.nanoTime();	//ȡ�õ�ǰϵͳ��ʱ�䣬���ڼ���ʱ���
//	int count = 0;	//��¼֡�������ڼ���֡����
	
	public DrawThread(ParticleView particleView, SurfaceHolder holder){
		this.particleView = particleView;
		this.holder = holder;
		this.flag = true;	//�߳�ִ���ˣ���־λ�ĳ�true
	}
	
	@SuppressLint("WrongCall")
	public void run(){
		Canvas canvas = null;
		while(flag){
		try{
			canvas = holder.lockCanvas();	//��particleView��������
			synchronized (holder) {	//������
				particleView.onDraw(canvas);	//��ʼ���ϵĻ���
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);	//���������ػ�������
			}
		}
//		this.count++;	//ÿִ��һ�μ�һ֡
//		if(count == 20){
//			count = 0;	//������ռ�����
//			long tempStamp = System.nanoTime();	//��¼����20֡��ʱ��
//			long Span = tempStamp - start;	//��ȡʱ����
//			start = tempStamp;	//Ҫ���¼������㣬�����������start���¸�ֵ
//			double fps = Math.round(100000000000.0 / Span * 20) / 100.0;	//����fps�Ĺ�ʽ
//		}
		try{
			Thread.sleep(sleepSpan);	//�߳�������~~~
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	}
}