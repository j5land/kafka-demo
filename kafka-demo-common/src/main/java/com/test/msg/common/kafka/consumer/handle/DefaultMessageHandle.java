package com.test.msg.common.kafka.consumer.handle;

import org.apache.log4j.Logger;

/**
 * 
 *
 *默认消息处理
 *
 * 2016-4-18 下午2:00:10
 * 
 */
public class DefaultMessageHandle implements MessageHandler{
	private static final Logger logger=Logger.getLogger(DefaultMessageHandle.class);

	@Override
	public void handle(String message) {
		//nothing to do
		logger.info("message="+message);
	}

}
