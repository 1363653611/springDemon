package com.zbcn.annotation.aop.selfscope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * @ClassName: MessageServiceImpl
 * @Description: scope 应用
 * @author Administrator
 * @date 2019-08-21 17:14
 *
 */
@Scope("threadScope")
@Component
public class MessageServiceImpl implements MessageService {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

}
