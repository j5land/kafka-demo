package com.test.kafka;

import org.junit.Test;

import com.test.BaseSpringTestCase;


public class KafkaConsumerTestCase extends BaseSpringTestCase{
	
	
	@Test
	public void test(){
		System.out.println("KafkaConsumerTestCase started!");
		try {
			Thread.sleep(1000L*600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
