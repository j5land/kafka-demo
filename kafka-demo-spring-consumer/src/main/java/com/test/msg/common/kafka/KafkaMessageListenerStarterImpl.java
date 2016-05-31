package com.test.msg.common.kafka;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 *
 * kafka消息监听器
 *
 * 2016-4-18 上午11:54:46
 * 
 */
public class KafkaMessageListenerStarterImpl implements KafkaMessageHandlerListener {
	
	private static Logger logger=Logger.getLogger(KafkaMessageListenerStarterImpl.class);
	
	@Autowired
	private List<KafkaMessageListener> kafkaMessageListeners=new ArrayList<KafkaMessageListener>();

	@Override
	public void startListener() {
		logger.info("starter KafkaMessageListenerStarterImpl……");
		for (KafkaMessageListener kafkaMessageListener : kafkaMessageListeners) {
			kafkaMessageListener.listener();
		}
	}

}
