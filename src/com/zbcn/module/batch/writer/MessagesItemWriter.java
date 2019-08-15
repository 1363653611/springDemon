
package com.zbcn.module.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.zbcn.module.batch.pojo.Message;

public class MessagesItemWriter implements ItemWriter<Message>{

	@Override
	public void write(List<? extends Message> messages) throws Exception {
		System.out.println("write results");
		for (Message m : messages) {
			//将消息写到控制台：
			System.out.println(m.getContent());
		}
	}
}
