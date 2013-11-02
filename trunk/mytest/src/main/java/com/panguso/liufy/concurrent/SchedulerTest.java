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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
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
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
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
	}

}
