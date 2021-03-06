package com.zbcn.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://www.ibm.com/developerworks/cn/java/j-lo-springbatch1/index.html?ca=drs-
 * @ClassName: MessageMain
 * @Description: 简单测试，spring-batch 的一个任务。
 * @author Administrator
 * @date 2019-08-08 20:08
 *
 */
public class MessageMain {
	
	public static final String RUN_MONTH_KEY = "run.month";
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("batch/message_job.xml");
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));
		launcher.setTaskExecutor(new SyncTaskExecutor());
		try {
			Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
			parameters.put(RUN_MONTH_KEY,new JobParameter("2011-10"));
			JobExecution je = launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));
			System.out.println(je);
			System.out.println(je.getJobInstance());
			System.out.println(je.getStepExecutions());
			Thread.sleep(20000);
			parameters.put(RUN_MONTH_KEY,new JobParameter("2011-11"));
			launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
