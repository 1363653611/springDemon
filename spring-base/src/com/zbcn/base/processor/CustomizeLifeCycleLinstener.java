package com.zbcn.base.processor;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * @ClassName: CustomizeLifeCycleLinstener
 * @Description: SmartLifecycle的实现类，在spring容器初始化完毕和关闭的时候被spring容器回调，完成特定的业务需求
 * @author Administrator
 * @date 2019-08-30 15:01
 *
 */
@Component
@Data
public class CustomizeLifeCycleLinstener implements SmartLifecycle {

	private boolean runningFlag = false;

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("do start");
		 //设置为false，表示正在执行中
        setRunningFlag(true);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("do stop");
		 //设置为false，表示已经不在执行中了
        setRunningFlag(false);
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPhase() {
		// TODO Auto-generated method stub
		return 666;
	}

	@Override
	public boolean isAutoStartup() {
		 //只有设置为true，start方法才会被回调
        return true;
	}

	@Override
	public void stop(Runnable callback) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("do stop with callback param");
				setRunningFlag(false);
				  //callback中有个CountDownLatch实例，总数是SmartLifecycle对象的数量，
                //此方法被回调时CountDownLatch实例才会减一，初始化容器的线程一直在wait中；
                callback.run();
			}
		}).start();
		
	}

}
