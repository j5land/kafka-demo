package com.test.msg.common.kafka.listenerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.msg.common.kafka.KafkaConsumerClientFactory;
import com.test.msg.common.kafka.KafkaMessageListener;
import com.test.msg.common.kafka.constants.KafkaConstants;
import com.test.msg.common.kafka.handle.CinemaMessageHandle;


/**
 * 
 * 场次信息更新监听
 * @author j5land
 *
 * 2016-4-18 下午12:00:11
 * 
 */
@Service
public class CinemaKafkaMessageListener implements KafkaMessageListener{
	
	@Autowired
	private KafkaConsumerClientFactory kafkaConsumerClientFactory;
	
	@Autowired
	private CinemaMessageHandle cinemaMessageHandle;

	@Override
	public void listener() {
		//group不同保证都监控
		String consumerGroup = KafkaConstants.cinema_sync_group;
		String consumerTopic = "test";//FilmKafkaConstants.cinema_sync_topic;
		kafkaConsumerClientFactory.createKafkaConsumerClient(
				consumerGroup,
				consumerTopic,
				cinemaMessageHandle);
	}
	
}
