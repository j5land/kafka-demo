package com.test.kafka;

import org.junit.Test;

import com.test.msg.common.kafka.KafkaProducerClient;

public class KafkaProducerClientTestCase {

	@Test
	public void testSendMessage() {
		KafkaProducerClient producer = null;
		try {
			producer = new KafkaProducerClient();
			producer.setLocation("kafka/kafka-producer.properties");
			producer.init();
			int i = 0;
			while (true) {
				producer.send("test", "send this is a message " + i);
				Thread.sleep(2000L);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (producer != null) {
				producer.close();
			}
		}

	}
}
