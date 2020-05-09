package com.zbcn.base.task.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.Data;
/**
 * 
 * @ClassName: Job1
 * @Description: 由于Spring提供对Quartz的支持，所以直接使用Spring提供的API
 * @author Administrator
 * @date 2019-08-15 11:27
 *
 */
@Data
public class Job1 extends QuartzJobBean{

	private int timeout;
	
	private static int i = 0;  
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("定时任务执行。。。。。：" + new Date());
	}

}
