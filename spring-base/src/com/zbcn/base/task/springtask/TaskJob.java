package com.zbcn.base.task.springtask;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {
	
	@Scheduled(cron = "0 0 3 * * ?") 
	public void job1() {  
        System.out.println("Spring 任务进行中。。。: "+ new Date());  
    }  
}
