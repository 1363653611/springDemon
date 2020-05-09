package com.zbcn.annotation.postconstruct;

import org.springframework.stereotype.Service;

@Service
public class TestService extends AbstractService {

	
	public void doSomeThing() {
		System.out.println("执行方法");
	}

}
