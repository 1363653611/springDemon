package com.zbcn.factorybean.entity;

import java.util.Base64;

/**
 * @ClassName: Base64Entity
 * @Description: base64 加密
 * @author Administrator
 * @date 2019-06-17 14:35
 *
 */
public class Base64Entity implements Text {

	private String cipher;
	
	public Base64Entity(String text) {
		super();
		this.cipher = Base64.getEncoder().encodeToString(text.getBytes());
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Text.Type.Base64;
	}

	@Override
	public String getCipher() {
		// TODO Auto-generated method stub
		return this.cipher;
	}

}
