package com.test.kafka;

import org.junit.Test;

import com.test.msg.common.kafka.KafkaConsumerClient;

public class KafkaConsumerClientTestCase {
	
	@Test
	public void testConsumerReceiver1(){
		KafkaConsumerClient consumer = null;
		try {
			consumer = new KafkaConsumerClient();
			consumer.setLocation("kafka/kafka-consumer.properties");
			consumer.setGroupid("cinema_sync_group11");
			consumer.setTopic("test");
			consumer.setPartitionsNum(2);
			consumer.init();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		try {
			Thread.sleep(100000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConsumerReceiver2(){
		KafkaConsumerClient consumer = null;
		try {
			consumer = new KafkaConsumerClient();
			consumer.setGroupid("test2");
			consumer.setTopic("test");
			consumer.setPartitionsNum(2);
			consumer.init();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		try {
			Thread.sleep(100000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
