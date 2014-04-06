package co.liuwei.weathereffect.particle;

import java.util.ArrayList;

import android.content.Context;

/*
 * �����������demo������Ҫ���࣬һ�����������࣬��һ������
 * ��1���ı�ÿ�����ӵ�����켣
 * ��������Ҫ������
 */
public class ParticleThread extends Thread {
	
	boolean flag;	//���չ��ʹ����������߳�ִ�еı�־λ
	ParticleView particleView;	//����һ��particelView��
	int sleepSpan = 30;	//���չ��ʹ����������߳�����ʱ��
	double time = 0;	//���������ʱ����
	double span = 0.15;	//ÿ�μ������ӵ�λ��ʱ���õ�ʱ����
	Context context;
	
	public ParticleThread(ParticleView particleView,Context ctx){
		this.particleView = particleView;
		this.flag = true;	
		this.context = ctx;
	}
	
	//�߳�������Ҫ�ķ���
	public void run(){
		while(flag){
			particleView.ps.add(context,5, time);	//ÿ�����5������
			ArrayList<Particle> tempSet = particleView.ps.particleSet;
			int count = tempSet.size();	//ȡ������������ϵĴ�С
			for(int i = 0; i < count; i++)
			 {	//�����������Ӽ��ϣ��޸�ÿһ���Ĺ켣
				Particle particle = tempSet.get(i);		//ȡ��Ҫ�޸ĵ����Ӷ�������ȡ��
				
				int tempx =particle.currentX;
				//particle.startY+random.nextInt(100);
				int tempy = (int) (particle.currentY+Math.round(Math.random() * 40));
				if(tempy > ParticleView.DIE_OUT_LINE-140){	//������ӳ�����Ļ����
					tempSet.remove(particle);	//�ʹӼ������Ƴ���particle����
					count = tempSet.size();	//?
					System.out.println("====>");
				}
				particle.currentX = tempx;	
				particle.currentY = tempy;	//�޸����ӵ�����
			}
			time += span;	
			try{
				Thread.sleep(sleepSpan);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
