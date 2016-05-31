package com.test.msg.common.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerSample {

	public final static String topicName = "topic_name";
	 public static void main(String[] args) {
		 	Properties props = new Properties();
//	        props.put("auto.offset.reset", "smallest"); //必须要加，如果要读旧数据
	        props.put("auto.offset.reset", "smallest"); //必须要加，如果要读最新数据
	        props.put("zookeeper.connect", "192.168.3.203:2181");
	        props.put("group.id", "bb");
	        props.put("zookeeper.session.timeout.ms", "400");
	        props.put("zookeeper.sync.time.ms", "200");
	        props.put("auto.commit.interval.ms", "1000");
	        
	        ConsumerConfig conf = new ConsumerConfig(props);
	        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(conf);
	        String topic = "cinemaSync_topic";
	        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
	        topicCountMap.put(topic, new Integer(5));
	        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
	        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
	        
	        KafkaStream<byte[], byte[]> stream = streams.get(0); 
	        ConsumerIterator<byte[], byte[]> it = stream.iterator();
	        while (it.hasNext()){
	            System.out.println(">>>>>==========message: " + new String(it.next().message()));
	        }
	        
	        if (consumer != null) consumer.shutdown();
	 }
}
