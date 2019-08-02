package com.zbcn.factorybean.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

import com.zbcn.factorybean.adapter.Base64Adapter;
import com.zbcn.factorybean.adapter.DecodeAdapter;
import com.zbcn.factorybean.entity.Base64Entity;
import com.zbcn.factorybean.entity.Text;

public class Base64AdapterFacory implements FactoryBean<DecodeAdapter>,BeanFactoryAware {

	private Text text;
	
	@Override
	public DecodeAdapter getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Base64Adapter(text.getCipher());
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		text = beanFactory.getBean(Base64Entity.class);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return DecodeAdapter.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
