package com.test.msg.common.kafka.consumer.handle;

public interface MessageHandler {

	/**
	 * 处理消息
	 * 
	 * @param message
	 */
	void handle(String message);
}
