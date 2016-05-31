package com.test.kafka;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTestCaseProducer;
import com.test.msg.common.kafka.KafkaProducerClient;


public class KafkaProducerTestCase extends BaseSpringTestCaseProducer{
	
	
	@Autowired
	private KafkaProducerClient kafkaProducerClient;
	
	@Test
	public void test() throws InterruptedException{
		System.out.println("BaseSpringTestCaseProducer started!");
		int i = 0;
		while (true) {
			kafkaProducerClient.send("test", "J5LAND send this is a message " + i);
			Thread.sleep(10000L);
			i++;
		}
	}
	
}
