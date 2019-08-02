package com.zbcn.factorybean;

import org.springframework.beans.factory.FactoryBean;

import com.zbcn.bean.Person;

public class PersonFactoryBean implements FactoryBean<Person> {
	
	private String personInfo;
	

	public String getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}

	@Override
	public Person getObject() {
		Person person = new Person();
		String[] infos = personInfo.split(",");
		 person.setName(infos[0]);
	     person.setAddress(infos[1]);
	     person.setAge(Integer.parseInt(infos[2]));
		return person;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Person.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}


}
