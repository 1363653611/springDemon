package com.zbcn.factorybean.entity;

/**
 * @ClassName: Text
 * @Description: 文本资源
 * @author zbcn
 * @date 2019-06-17 14:31
 *
 */
public interface Text {

	
	public static enum Type{
		Base64,
		UrlBase64
	}
	
	//获取资源编码类型
	Type getType();
	
	//获取编码的密文
	String getCipher();
}
