package com.zbcn.springDemon.propertyeditor.bean;

import lombok.Data;

@Data
public class Boss {
	private String name;  
    private Car car = new Car(); 
}
