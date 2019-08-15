package com.zbcn.springDemon.task.scheduled;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TestScheduledExecutorService
 * @Description: ScheduledExecutorService：也jdk自带的一个类；
 * 是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响。
 * @author Administrator
 * @date 2019-08-15 10:00
 *
 */
public class TestScheduledExecutorService {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		
		 // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
		executor.scheduleAtFixedRate(() ->System.out.println("测试定时任务：" + new Date()), 0, 3, TimeUnit.SECONDS);
	}
}
