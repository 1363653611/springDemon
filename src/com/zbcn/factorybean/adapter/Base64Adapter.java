package com.zbcn.factorybean.adapter;

import java.util.Base64;

public class Base64Adapter implements DecodeAdapter {
	
	/**
	 *	加密语句
	 */
	private String cipher;
	
	public Base64Adapter(String cipher) {
		super();
		this.cipher = cipher;
	}

	@Override
	public String getPlain() {
		return new String(Base64.getDecoder().decode(this.cipher));
	}

}
