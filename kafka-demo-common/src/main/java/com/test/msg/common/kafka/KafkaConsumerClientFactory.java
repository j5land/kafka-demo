package com.test.msg.common.kafka;

import org.apache.log4j.Logger;

import com.test.msg.common.kafka.consumer.handle.MessageHandler;



/**
 * 
 * kafka消息消费者客户端工厂,获取客户端实例
 * @author vincent
 *
 * 2016-4-15 下午3:45:59
 * 
 */
public class KafkaConsumerClientFactory {
	
	protected static final Logger logger=Logger.getLogger(KafkaConsumerClientFactory.class);

	private String location = "kafka-consumer.properties";//配置文件位置
	
	private int partitionsNum;
	
	public KafkaConsumerClientFactory() {}
	
	/**
	 * 获取客户端实例
	 * @param topic    	                    主题id
	 *
	 */
	public KafkaConsumerClient createKafkaConsumerClient(String topic,MessageHandler messageHandler){
		KafkaConsumerClient client=new KafkaConsumerClient();
		client.setLocation(location);
		client.setTopic(topic);
		client.setPartitionsNum(partitionsNum);
		client.setMessageHandler(messageHandler);
		client.init();
		return client;
	}
	
	/**
	 * 获取客户端实例
	 * @param customerGroupId 所属群组id
	 * @param topic    	                    主题id
	 *
	*/
	public KafkaConsumerClient createKafkaConsumerClient(String customerGroupId,
			String topic,MessageHandler messageHandler){
		KafkaConsumerClient client=new KafkaConsumerClient();
		client.setLocation(location);
		client.setGroupid(customerGroupId);
		client.setTopic(topic);
		client.setPartitionsNum(partitionsNum);
		client.setMessageHandler(messageHandler);
		client.init();
		return client;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public void setPartitionsNum(int partitionsNum) {
		this.partitionsNum = partitionsNum;
	}

}
