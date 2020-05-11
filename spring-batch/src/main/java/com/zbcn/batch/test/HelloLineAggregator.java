package com.zbcn.batch.test;

import com.zbcn.batch.pojo.DeviceCommand;
import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * @ClassName: HelloLineAggregator
 * @Description: 自定义LineAggregator
 * @author Administrator
 * @date 2019-08-08 17:07
 *
 */
public class HelloLineAggregator implements LineAggregator<DeviceCommand>{

	@Override
	public String aggregate(DeviceCommand deviceCommand) {
		StringBuffer sb = new StringBuffer();
        sb.append(deviceCommand.getId());
        sb.append(",");
        sb.append(deviceCommand.getStatus());
        return sb.toString();
	}

}
