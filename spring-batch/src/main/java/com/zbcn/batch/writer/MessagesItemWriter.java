
package com.zbcn.batch.writer;

import com.zbcn.batch.pojo.Message;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

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
