package com.test.msg.common.kafka;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class TestProducer {

	public static void main(String[] args) {
		long events = Long.parseLong("10");  
        Random rnd = new Random();  
   
        Properties props = new Properties();  
        props.put("metadata.broker.list", "192.168.3.203:9092");  
        props.put("serializer.class", "kafka.serializer.StringEncoder"); //默认字符串编码消息  
        props.put("partitioner.class", "com.spider.msg.common.kafka.SimplePartitioner");  
        props.put("request.required.acks", "1");  
   
        ProducerConfig config = new ProducerConfig(props);  
   
        Producer<String, String> producer = new Producer<String, String>(config);  
   
        for (long nEvents = 0; nEvents < events; nEvents++) {   
               long runtime = new Date().getTime();    
               String ip = "192.168.2." + rnd.nextInt(255);   
               String msg = runtime + "|www.yeshucheng.com|" + ip;   
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("ttt", ip, msg);  
               producer.send(data);  
        }  
        producer.close();
	}

}
