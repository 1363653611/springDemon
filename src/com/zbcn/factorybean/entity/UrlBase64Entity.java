package com.zbcn.factorybean.entity;

import java.util.Base64;

public class UrlBase64Entity implements Text {

	private String cipher;
	
	
	public UrlBase64Entity(String text) {
		super();
		this.cipher = Base64.getUrlEncoder().encodeToString(text.getBytes());
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Type.UrlBase64;
	}

	@Override
	public String getCipher() {
		// TODO Auto-generated method stub
		return this.cipher;
	}

}
