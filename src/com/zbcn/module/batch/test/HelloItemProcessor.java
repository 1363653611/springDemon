package com.zbcn.module.batch.test;

import org.springframework.batch.item.ItemProcessor;

import com.zbcn.module.batch.pojo.DeviceCommand;

/**
 * 
 * @ClassName: HelloItemProcessor
 * @Description: 创建Processor:读完数据后，我们就需要处理数据了。既然我们前面从文件里读取了待下发的命令，那么在这里下发命令给设备是最好的时机
 * @author Administrator
 * @date 2019-08-08 17:04
 *
 */
public class HelloItemProcessor implements ItemProcessor<DeviceCommand, DeviceCommand> {

	@Override
	public DeviceCommand process(DeviceCommand deviceCommand) throws Exception {
		// 模拟下发命令给设备
	    System.out.println("send command to device, id=" + deviceCommand.getId());

	    // 更新命令状态
	    deviceCommand.setStatus("SENT");

	    // 返回命令对象
	    return deviceCommand;
	} 

}
