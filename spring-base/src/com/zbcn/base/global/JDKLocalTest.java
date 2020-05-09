package com.zbcn.base.global;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @ClassName: JDKLocalTest
 * @Description: 测试jdk自带的国际化适配功能
 * @author Administrator
 * @date 2019-08-29 18:59
 *
 */
public class JDKLocalTest {
	
	public static void main(String[] args) {
		//信息格式化串
		String pattern1 = "{0},你好，你于{1}在工商银行存入{2}元钱。";
		String pattern2 = "At {1,time,short} On{1,date,long},{0} paid {2,number,currency}.";
		//动态替换的占位符的参数
		Object[] params = {"Johon",new GregorianCalendar().getTime(),1.0E3};
		
		//默认对象的信息格式化:jdk自带：MessageFormat，NumberFormat\DateFormat
		String msg1 = MessageFormat.format(pattern1, params);
		System.out.println(msg1);

		//使用指定本地对象信息格式化
		MessageFormat messageFormat = new MessageFormat(pattern2, Locale.US);
		
		String msg2 = messageFormat.format(params);
		
		System.out.println(msg2);
	}

}
