/**
 *  Copyright (c)  2011-2020 Panguso, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Panguso, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Panguso.
 */
package com.panguso.liufy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ScheduledExecutorService当一个任务抛出异常，后面的任务就不执行了；对于ExecutorService，如果一个任务线程抛出异常，
 * 则会new新的线程来处理后面的任务。
 * 
 * @author panguso
 * @date 2013年11月2日
 */
public class SchedulerTest {

	/**
	 * @param args
	 * @author panguso
	 * @date 2013年11月2日
	 */
	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors
		        .newSingleThreadScheduledExecutor(new ThreadFactory() {
			        AtomicInteger i = new AtomicInteger(0);

			        @Override
			        public Thread newThread(Runnable r) {
				        Thread thread = new Thread(r);
				        thread.setName("t-" + i.incrementAndGet());
				        return thread;
			        }
		        });

		executorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("线程进入");
				throw new RuntimeException("线程出错了");
			}
		}, 1, 2, TimeUnit.SECONDS);

		ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(2);

		executorService1.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("线程进入2");
				throw new RuntimeException("");
			}
		}, 1, 2, TimeUnit.SECONDS);

		ExecutorService executorService2 = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			executorService2.submit(new Runnable() {

				@Override
				public void run() {
					System.out.println("任务3开始");
					throw new RuntimeException("");
				}
			});
		}
	}

}
