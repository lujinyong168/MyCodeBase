package com.lujinyong.thread.multipleThread.countdownlatch.copy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @Description:两个线程合作模拟运动员百米赛跑
 * @Author: lujinyong168
 * @Date: 2016年2月6日 上午10:26:54
 */
public class CountDownLatchTest2 {
	public static void main(String[] args) {
		int count = 8;//运动员数量
		CountDownLatch begin = new CountDownLatch(1);//对于每个运动员减一后就说明准备完成
		CountDownLatch end = new CountDownLatch(count);//对于整个比赛,所有运动员跑完比赛才算结束
		Player[] players = new Player[count];
		for(int i =0;i<count;i++){
			players[i] = new Player("运动员"+(i+1),begin,end);
		}
		//设置线程池,大小为运动员的数量
		ExecutorService service = Executors.newFixedThreadPool(count);
		for (Player player : players) {
			service.execute(player);//分配线程(给运动员分配跑道)
		}
		begin.countDown();
		System.out.println("所有运动员准备完毕,比赛开始...go");
		try {
			end.await();//等待end状态为0,比赛结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			System.out.println("所有运动员跑到了终点,比赛结束...");
			service.shutdown();
		}
	}
	
	
}
//定义运动员
class Player implements Runnable{
	private String name;
	private CountDownLatch begin;
	private CountDownLatch end;
	public Player (String name,CountDownLatch begin,CountDownLatch end){
		this.name = name;
		this.begin = begin;
		this.end = end;
	}
	@Override
	public void run() {
		try {
			begin.await();
			long time = 0;
			do{
				time = (long) (Math.random()*20000);
			}while(time/1000<9);
			Thread.sleep(time);
			String tookTime = time/1000+"."+time%1000+"s";
			System.out.println(name+" 到达终点!!!" +" took:"+tookTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			end.countDown();
		}
	}
}
