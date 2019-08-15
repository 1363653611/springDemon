package com.zbcn.springDemon.propertyeditor;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import lombok.Data;

@Data
public class DatePropertyRegistrar implements PropertyEditorRegistrar{

	private PropertyEditor propertyEditor;
	
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// TODO Auto-generated method stub
		registry.registerCustomEditor(Date.class,propertyEditor);
		
	}

}
