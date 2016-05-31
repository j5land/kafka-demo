package com.test.msg.common.kafka.log4j;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaBuilder {

	Producer<String, String> producer = null;
	
    private String topic;
    
    public KafkaBuilder(String topic, String brokerList) {
        this.topic = topic;
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", brokerList);
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<String, String>(config);
    }

    public void produce(String message) {
        String partitionKey = System.currentTimeMillis() % 5 == 0 ? "1" : "2";
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, partitionKey, message);
        producer.send(data);
    }

    public void close() {
        if(producer != null)
            producer.close();
    }
}
