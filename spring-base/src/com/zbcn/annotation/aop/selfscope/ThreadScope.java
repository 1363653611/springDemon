package com.zbcn.annotation.aop.selfscope;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 
 * @ClassName: ThreadScope
 * @Description: 自定义线程范围内的scope
 * @author Administrator
 * @date 2019-08-21 16:59
 *
 */
public class ThreadScope implements Scope {

	private static final ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<Map<String,Object>>() {
		public Map<String,Object> initialValue() {
			return new HashMap<String, Object>();
		}
	}; 
	
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		// TODO Auto-generated method stub
		Map<String, Object> scope = threadLocal.get();
		Object obj = scope.get(name);
		if(Objects.isNull(obj)) {
			obj = objectFactory.getObject();
			scope.put(name, obj);
			System.out.println("Not exists " + name + "; hashCode: " + obj.hashCode() +"; threadName:" + Thread.currentThread().getName() );
		}else {
			System.out.println("Exists " + name + "; hashCode: " + obj.hashCode());
		}
		return obj;
		
	}

	@Override
	public Object remove(String name) {
		// TODO Auto-generated method stub
		Map<String, Object> scope = threadLocal.get();
		return scope.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

}
