package com.imf.multipleThread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: Callable 和 Future接口 测试
 * Callable是类似于Runnable的接口， 实现Callable接口的类和实现Runnable的类都是可被其他线程执行的任务；
 *                       Callable和Runnable有几点不同：
 *                       （1）Callable规定的方法是call()，而Runnable规定的方法是run().
 *                       （2）Callable的任务执行后可返回值，而Runnable的任务是不能返回值的。
 *                       （3）call()方法可抛出异常，而run()方法是不能抛出异常的。
 *                       （4）运行Callable任务可拿到一个Future对象， Future表示异步计算的结果。
 *                       它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。
 *                       通过Future对象可了解任务执行情况，可取消任务的执行，还可获取任务执行的结果。
 * @Author: lujinyong168
 * @Date: 2016年2月5日 下午6:01:01
 */
public class CallableAndFutureTest {
	public static void main(String[] args) {
		// 定义3个Callable类型的任务
		CallableTest task1 = new CallableTest(0);
		CallableTest task2 = new CallableTest(1);
		CallableTest task3 = new CallableTest(2);
		// 创建一个执行任务的服务
		ExecutorService exec = Executors.newFixedThreadPool(3);
		try {
			// 提交并执行任务，任务启动时返回了一个Future对象，
			// 如果想得到任务执行的结果或者是异常可对这个Future对象进行操作
			Future<String> future1 = exec.submit(task1);
			// 获得第一个任务的结果，如果调用get方法，当前线程会等待任务执行完毕后才往下执行
			System.out.println("task1:" + future1.get());

			Future<String> future2 = exec.submit(task2);
			System.out.println("task2 cancel:" + future2.cancel(true));
			// 等待5秒后，再停止第二个任务。因为第二个任务进行的是无限循环
			Thread.sleep(5000);
			// 获取第三个任务的输出，因为执行第三个任务会引起异常
			// 所以下面的语句将引起异常的抛出
			Future<String> future3 = exec.submit(task3);
			System.out.println("task3:" + future3.get());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			exec.shutdown();
		}
	}

	// 自定义一个任务类，实现Callable接口
	public static class CallableTest implements Callable<String> {
		private int flag = 0;// 标志位

		public CallableTest(int flag) {
			this.flag = flag;
		}

		public String call() throws Exception {
			if (this.flag == 0) {
				return "flag = 0";
			}
			if (this.flag == 1) {
				try {
					while (true) {
						System.out.println("looping......");
						Thread.sleep((long) (Math.random() * 1000));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "false";
			} else {
				throw new Exception("Bad flag value!");
			}
		}
	}
}
