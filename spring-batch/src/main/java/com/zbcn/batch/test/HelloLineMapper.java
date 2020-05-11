package com.zbcn.batch.test;

import com.zbcn.batch.pojo.DeviceCommand;
import org.springframework.batch.item.file.LineMapper;

/**
 * 
 * @ClassName: HelloLineMapper
 * @Description: 自定义LineMapper :用于将batch-data-source.csv文件的每行数据，转成程序方便处理的DeviceCommand对象
 * @author Administrator
 * @date 2019-08-08 16:41
 *
 */
public class HelloLineMapper implements LineMapper<DeviceCommand> {

	@Override
	public DeviceCommand mapLine(String line, int lineNumber) throws Exception {
		// TODO Auto-generated method stub
		// 逗号分割每一行数据
        String[] args = line.split(",");
        // 创建DeviceCommand对象
        DeviceCommand deviceCommand = new DeviceCommand();
        
     // 设置id值到对象中
        deviceCommand.setId(args[0]);
        
        // 设置status值到对象中
        deviceCommand.setStatus(args[1]);
        
        // 返回对象
        return deviceCommand;
	}

}
