package com.test.msg.common.kafka.consumer;

import java.nio.charset.Charset;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.msg.common.kafka.consumer.handle.DefaultMessageHandle;
import com.test.msg.common.kafka.consumer.handle.MessageHandler;


/**
 * 
 *
 * kafka客户端消息处理
 *
 * 2016-4-18 下午1:42:02
 * 
 */
public class ConsumerRunner implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsumerRunner.class);
	
	private KafkaStream<byte[], byte[]> kafkaStream;  
	
	private Charset charset = Charset.forName("utf8");
	
	private MessageHandler messageHandler=new DefaultMessageHandle() ;
   
    public ConsumerRunner() { 
    	
    }  
   
    public void run() {
    	ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
		while(iterator.hasNext()){
			MessageAndMetadata<byte[], byte[]> item = iterator.next();
			//System.out.println("receiver message=["+new String(item.message(),charset)+"]");
			String message = new String(item.message(),charset);
			//logger.info("receiver message=["+message+"]");
			messageHandler.handle(message);
		}
    }

	public void setKafkaStream(KafkaStream<byte[], byte[]> kafkaStream) {
		this.kafkaStream = kafkaStream;
	}

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}

	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
    
}
