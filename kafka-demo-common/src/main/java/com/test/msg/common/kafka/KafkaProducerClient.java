package com.test.msg.common.kafka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.log4j.Logger;


/**
 * 
 * kafka消息生产者客户端
 * @author vincent
 *
 * 2016-4-15 下午3:48:33
 * 
 */
public class KafkaProducerClient {
	
	private static final Logger logger=Logger.getLogger(KafkaProducerClient.class);

	private Producer<String, String> inner;
	
	private String brokerList;//for metadata discovery,spring setter
	
	private String location = "kafka-producer.properties";//spring setter
	
	private String defaultTopic;//spring setter

	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDefaultTopic(String defaultTopic) {
		this.defaultTopic = defaultTopic;
	}
	
	public KafkaProducerClient(String brokerList) throws Exception{
		this.brokerList=brokerList;
	}

	public KafkaProducerClient() throws Exception{
	}
	
	public void init() throws Exception {
		Properties properties = new Properties();
		
		properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(location));
		
		if(brokerList != null) {
			properties.put("metadata.broker.list", brokerList);
		}
		ProducerConfig config = new ProducerConfig(properties);
		inner = new Producer<String, String>(config);
	}

	public void send(String message){
		send(defaultTopic,message);
	}
	
	public void send(Collection<String> messages){
		send(defaultTopic,messages);
	}
	
	public void send(String topicName, String message) {
		if (topicName == null || message == null) {
			return;
		}
		KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName,message);
		inner.send(km);
	}

	public void send(String topicName, Collection<String> messages) {
		if (topicName == null || messages == null) {
			return;
		}
		if (messages.isEmpty()) {
			return;
		}
		logger.info("send messages "+messages);
		
		List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
		int i= 0;
		for (String entry : messages) {
			KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName,entry);
			kms.add(km);
			i++;
			if(i % 20 == 0){
				inner.send(kms);
				kms.clear();
			}
		}
		
		if(!kms.isEmpty()){
			inner.send(kms);
		}
	}

	public void close() {
		inner.close();
	}


}
