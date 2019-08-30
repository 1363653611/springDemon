package com.zbcn.annotation.postconstruct;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AbstractService {

	
	@PostConstruct
	public void init() {
		System.out.println("抽象类执行：@PostConstruct");
	}
}
