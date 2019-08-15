/**
 * 
 */
package com.zbcn.module.batch.function;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import com.zbcn.module.batch.pojo.Message;
import com.zbcn.module.batch.pojo.User;

public class MessagesItemProcessor implements ItemProcessor<User, Message> {
	
	@Override
	public Message process(User user) throws Exception {
		if(!StringUtils.hasText(user.getName())){
			throw new RuntimeException("The user name is required!");
		}
		Message m = new Message();
		m.setUser(user);
		m.setContent("Hello " + user.getName()
				+ ",please pay promptly at end of this month.");
		return m;
	}

}
