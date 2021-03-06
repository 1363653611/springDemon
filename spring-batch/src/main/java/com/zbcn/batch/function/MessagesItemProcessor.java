/**
 * 
 */
package com.zbcn.batch.function;

import com.zbcn.batch.pojo.Message;
import com.zbcn.batch.pojo.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

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
