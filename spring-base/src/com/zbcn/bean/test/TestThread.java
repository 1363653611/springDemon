package com.zbcn.bean.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestThread {
	
	@Autowired
	private TestA testA;
	
	
	public void testBB() {
		ExecutorService executer = Executors.newSingleThreadExecutor();
		executer.execute(new Runner());
	}
	
	
	class Runner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			testA.testSS();
		}
		
	}
}
