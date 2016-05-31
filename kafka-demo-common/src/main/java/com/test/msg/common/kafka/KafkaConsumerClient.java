package com.test.msg.common.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.apache.log4j.Logger;

import com.test.msg.common.kafka.consumer.ConsumerRunner;
import com.test.msg.common.kafka.consumer.handle.DefaultMessageHandle;
import com.test.msg.common.kafka.consumer.handle.MessageHandler;



/**
 * 
 * kafka消息消费者客户端
 * @author vincent
 *
 * 2016-4-15 下午3:45:59
 * 
 */
public class KafkaConsumerClient {
	
	private static final Logger logger=Logger.getLogger(KafkaConsumerClient.class);

	private String groupId; //can be setting by spring
	
	private String zkConnect;//can be setting by spring
	
	private String location = "kafka-consumer.properties";//配置文件位置
	
	private String topic;
	
	private int partitionsNum;
	
	private ConsumerConnector connector;
	
	private ExecutorService customerRunnerPool;//消费者线程池,针对每一个KafkaStream创建线程
	
	private MessageHandler messageHandler=new DefaultMessageHandle();

	public KafkaConsumerClient() {}
	
	//init consumer,and start connection and listener
	public void init() {
		try {
			Properties properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(location));
			
			if(groupId != null){
				properties.put("group.id", groupId);
			}
			if(zkConnect != null){
				properties.put("zookeeper.connect", zkConnect);
			}
			ConsumerConfig config = new ConsumerConfig(properties);
			
			//建立连接
			connector = Consumer.createJavaConsumerConnector(config);
			//创建线程池
			customerRunnerPool=Executors.newFixedThreadPool(partitionsNum);
			//监听消息
			listenerMessage();
		} catch (IOException e) {
			logger.error("init exception:",e);
		}
	}
	

	
	/**
	 * 接收消息
	 *
	 */
	private void listenerMessage(){
		Map<String, Integer> topics = new HashMap<String, Integer>();
		topics.put(topic, partitionsNum);
		Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topics);
		List<KafkaStream<byte[], byte[]>> partitions = streams.get(topic);
		for (KafkaStream<byte[], byte[]> kafkaStream : partitions) {
			ConsumerRunner task = new ConsumerRunner();
			task.setMessageHandler(messageHandler);
			task.setKafkaStream(kafkaStream);
			customerRunnerPool.submit(task);
		}
	}

	public void setGroupid(String groupid) {
		this.groupId = groupid;
	}

	public void setZkConnect(String zkConnect) {
		this.zkConnect = zkConnect;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setPartitionsNum(int partitionsNum) {
		this.partitionsNum = partitionsNum;
	}

	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

}
