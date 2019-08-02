package com.zbcn.factorybean.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

import com.zbcn.factorybean.adapter.DecodeAdapter;
import com.zbcn.factorybean.adapter.UrlBase64Adapter;
import com.zbcn.factorybean.entity.Text;
import com.zbcn.factorybean.entity.UrlBase64Entity;

public class UrlBase64AdapterFactory implements FactoryBean<DecodeAdapter>,BeanFactoryAware  {

	private Text text;
	@Override
	public DecodeAdapter getObject() throws Exception {
		// TODO Auto-generated method stub
		return new UrlBase64Adapter(text.getCipher());
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		text = beanFactory.getBean(UrlBase64Entity.class);
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
