package com.zbcn.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: StackUtils
 * @Description: 堆栈工具类
 * @author Administrator
 * @date 2019-08-30 10:53
 *
 */
public class StackUtils {

	private static final Logger logger = LoggerFactory.getLogger(StackUtils.class);

	/**
	 * @Title: printStack
	 * @Description: 打印当前的堆栈信息
	 * @param prefix
	 */
	public static void printStack(String prefix) {
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		if (null == st) {
			logger.info("invalid stack");
			return;
		}

		StringBuffer sbf = new StringBuffer();

		for (StackTraceElement e : st) {
			if (sbf.length() > 0) {
				sbf.append(" <- ");
				sbf.append(System.getProperty("line.separator"));
			}
			sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}", e.getClassName(), e.getMethodName(),
					e.getLineNumber()));

		}
		logger.info(prefix + "\n************************************************************\n" + sbf.toString()
				+ "\n************************************************************");
	}
}
