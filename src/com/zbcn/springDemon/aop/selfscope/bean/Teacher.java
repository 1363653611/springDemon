package com.zbcn.springDemon.aop.selfscope.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Scope(value="myScope")
@Component("myteacher")
@Data
public class Teacher {
	private String name;
	private int age;
}
