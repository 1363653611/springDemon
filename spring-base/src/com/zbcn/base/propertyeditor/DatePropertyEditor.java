package com.zbcn.base.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @title DatePropertyEditor
 *  @Description 类型转化器
 *  @author zbcn8
 *  @Date 2020/4/6 11:44
 */
public class DatePropertyEditor extends PropertyEditorSupport {
	
	private String dataPattern;
	
	
	@Override
	 public void setAsText(String text) {
		 SimpleDateFormat format = new SimpleDateFormat(getDataPattern());
		 try {
			Date dateValue = format.parse(text);
			setValue(dateValue);
			 System.out.println("调用了自定义的类型转换器" + dateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }


	public String getDataPattern() {
		return dataPattern;
	}


	public void setDataPattern(String dataPattern) {
		this.dataPattern = dataPattern;
	}

}
	
