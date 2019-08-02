package com.zbcn.factorybean.adapter;

import java.util.Base64;

public class UrlBase64Adapter implements DecodeAdapter {
	
	/**
	 * 加密后的语句
	 */
	private String cipher;
	
	
	
	public UrlBase64Adapter(String cipher) {
		super();
		this.cipher = cipher;
	}



	@Override
	public String getPlain() {
		// TODO Auto-generated method stub
		return new String(Base64.getUrlDecoder().decode(this.cipher.getBytes()));
	}

}
