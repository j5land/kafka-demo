package com.test.msg.common.kafka.bean;

/**
 * 
 *
 * TopicInfo 
 * 
 * 2015年7月22日 下午1:57:28
 * 
 * @version 1.0.0
 *
 */
public class TopicInfo {

	// topic名字
	private String topicName;

	// 分块数量
	private Integer partitionsCount;

	public TopicInfo(String topicName, Integer partitionsCount) {
		this.topicName = topicName;
		this.partitionsCount = partitionsCount;
	}

	public String getTopicName() {
		return topicName;
	}

	public Integer getPartitionsCount() {
		return partitionsCount;
	}
}
