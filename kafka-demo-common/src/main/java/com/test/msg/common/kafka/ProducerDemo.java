package com.test.msg.common.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
	
	Properties props = new Properties();

	public ProducerDemo(){
		init();
	}
	
	public void init(){
		props.put("metadata.broker.list", "192.168.3.203:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "com.spider.msg.common.kafka.SimplePartitioner");
		props.put("request.required.acks", "1");
	}
	
	public void sendMessage(){
		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer(config);
		KeyedMessage<String, String> message = new KeyedMessage<String, String>("test", "key", "message");
		producer.send(message);
		// 关闭producer实例
		producer.close();
	}

}
